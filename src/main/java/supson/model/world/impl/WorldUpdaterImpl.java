package supson.model.world.impl;

import java.util.List;
import java.util.stream.Collectors;

import supson.model.collisions.api.CollisionObserver;
import supson.model.collisions.impl.CollisionResolver;
import supson.model.entity.api.block.collectible.Collectible;
import supson.model.entity.api.block.finishline.Finishline;
import supson.model.entity.api.block.trap.Trap;
import supson.model.entity.impl.moveable.enemy.Enemy;
import supson.model.entity.api.moveable.MoveableEntity;
import supson.model.entity.impl.moveable.player.Player;
import supson.model.entity.api.moveable.player.PlayerManager;
import supson.model.entity.impl.moveable.player.PlayerManagerImpl;
import supson.model.world.api.World;
import supson.model.world.api.WorldUpdater;

public final class WorldUpdaterImpl implements WorldUpdater {

    private final PlayerManager playerManager;
    private final CollisionResolver collisionResolver;

    /**
     * Constructs a new WorldUpdaterImpl instance.
     */
    public WorldUpdaterImpl(Player player) {
        this.playerManager = new PlayerManagerImpl(player);
        this.collisionResolver = new CollisionResolver();
        collisionResolver.register((CollisionObserver) playerManager);
    }

    @Override
    public void updateGame(World world, long elapsed) {
        if (!world.isGameOver()) {
            updateEntities(world, elapsed);
            handleCollisions(world);
            Player player = world.getPlayer();
            player.setState(playerManager.getUpdatedState());
            if (player.getLife() <= 0) {
                world.setGameOver(true);
            }
        }
    }

    /**
     * Updates the entities in the game world.
     * 
     * @param world the world to update
     * @param elapsed the time elapsed since the last update
     */
    private void updateEntities(World world, long elapsed) {
        List<MoveableEntity> movEntities = world.getEnemies().stream().collect(Collectors.toList());
        movEntities.add(world.getPlayer());

        movEntities.forEach(e -> {
            e.move(elapsed);
            collisionResolver.resolvePlatformCollisions(e, world.getBlocks(), e.getPosition());
        });
    }

    /**
     * Handles collisions between entities in the game world.
     * 
     * @param world the world to update
     */
    private void handleCollisions(World world) {
        Player player = world.getPlayer();
        List<Enemy> killed = collisionResolver.resolveEnemiesCollisions(player, world.getEnemies());
        killed.forEach(world::removeEnemy);

        collisionResolver.resolveTrapCollisions(player,
                world.getBlocks().stream().filter(b -> b instanceof Trap).map(Trap.class::cast).collect(Collectors.toList()));

        List<Collectible> activated = collisionResolver.resolveCollectibleCollisions(player,
                world.getBlocks().stream().filter(k -> k instanceof Collectible).map(Collectible.class::cast)
                        .collect(Collectors.toList()));
        activated.forEach(world::removeBlock);

        collisionResolver.resolveFinishlineCollision(player, 
                world.getBlocks().stream().filter(b -> b instanceof Finishline).map(Finishline.class::cast)
                        .collect(Collectors.toList()), world);
    }
    
    @Override
    public void setPlayerFlags(Player player, boolean right, boolean left, boolean jump) {
        System.out.println(player.getState()+"pre");
        playerManager.moveRight(player, right);
        playerManager.moveLeft(player, left);
        playerManager.jump(player, jump);
        System.out.println(player.getState()+"post");

    }
}
