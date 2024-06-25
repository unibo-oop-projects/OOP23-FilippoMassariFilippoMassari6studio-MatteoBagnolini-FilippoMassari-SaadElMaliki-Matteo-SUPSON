package supson.model.world.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import supson.common.GameEntityType;
import supson.common.api.Pos2d;
import supson.model.collisions.impl.CollisionResolver;
import supson.model.entity.api.block.collectible.Collectible;
import supson.model.entity.api.block.finishline.Finishline;
import supson.model.entity.api.block.trap.Trap;
import supson.model.entity.api.moveable.MoveableEntity;
import supson.model.entity.api.moveable.player.PlayerManager;
import supson.model.entity.impl.moveable.enemy.Enemy;
import supson.model.world.api.World;
import supson.model.world.api.WorldUpdater;

/**
 * Implementation of the WorldUpdater interface.
 * Manages the update of the game world, including entity movements and collisions.
 */
public class WorldUpdaterImpl implements WorldUpdater {

    private final CollisionResolver collisionResolver;

    /**
     * Constructs a new WorldUpdaterImpl instance.
     */
    public WorldUpdaterImpl() {
        this.collisionResolver = new CollisionResolver();
    }

    @Override
    public void updateWorld(World world, long elapsed, PlayerManager playerManager) {
        if (!world.isGameOver()) {
            updateEntities(world, elapsed, playerManager);
            handleCollisions(world);
            world.getPlayer().setState(world.getPlayer().getState());
            if (world.getPlayer().getLife() <= 0) {
                world.setGameOver(true);
            }
        }
    }

    /**
     * Updates the entities in the game world.
     * 
     * @param world the world to be updated
     * @param elapsed the time elapsed since the last update
     */
    private void updateEntities(World world, long elapsed, PlayerManager playerManager) {
        final List<MoveableEntity> movEntities = new ArrayList<>(world.getEnemies());
        movEntities.add(world.getPlayer());

        movEntities.forEach(e -> {
            final Pos2d oldPos = e.getPosition();
            e.move(elapsed);
            if (e.getGameEntityType().equals(GameEntityType.PLAYER)) {
                playerManager.setState(world.getPlayer().getState());
            }
            collisionResolver.resolvePlatformCollisions(e, List.copyOf(world.getBlocks()), oldPos);
        });
    }

    /**
     * Handles collisions between entities in the game world.
     * 
     * @param world the world to handle collisions for
     */
    private void handleCollisions(World world) {
        final List<Enemy> killed = collisionResolver.resolveEnemiesCollisions(world.getPlayer(), List.copyOf(world.getEnemies()));
        killed.forEach(world::removeEnemy);

        collisionResolver.resolveTrapCollisions(world.getPlayer(),
                world.getBlocks().stream().filter(b -> b instanceof Trap).map(Trap.class::cast).collect(Collectors.toList()));

        final List<Collectible> activated = collisionResolver.resolveCollectibleCollisions(world.getPlayer(),
                world.getBlocks().stream().filter(k -> k instanceof Collectible).map(Collectible.class::cast)
                        .collect(Collectors.toList()));
        activated.forEach(world::removeBlock);

        collisionResolver.resolveFinishlineCollision(world.getPlayer(), 
                world.getBlocks().stream().filter(b -> b instanceof Finishline).map(Finishline.class::cast)
                        .collect(Collectors.toList()), world);
    }
}
