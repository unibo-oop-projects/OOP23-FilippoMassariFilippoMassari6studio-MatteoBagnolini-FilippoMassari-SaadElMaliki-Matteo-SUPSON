package supson.model.world.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import supson.common.GameEntityType;
import supson.common.api.Pos2d;
import supson.common.impl.Pos2dImpl;
import supson.model.collisions.api.CollisionObserver;
import supson.model.collisions.impl.CollisionResolver;
import supson.model.entity.api.GameEntity;
import supson.model.entity.api.block.TagBlockEntity;
import supson.model.entity.api.block.collectible.Collectible;
import supson.model.entity.api.block.finishline.Finishline;
import supson.model.entity.api.block.trap.Trap;
import supson.model.entity.api.moveable.MoveableEntity;
import supson.model.entity.api.moveable.player.PlayerManager;
import supson.model.entity.impl.moveable.enemy.Enemy;
import supson.model.entity.impl.moveable.player.Player;
import supson.model.entity.impl.moveable.player.PlayerManagerImpl;
import supson.model.hud.api.Hud;
import supson.model.hud.impl.HudImpl;
import supson.model.timer.api.GameTimer;
import supson.model.timer.impl.GameTimerImpl;
import supson.model.world.api.World;
import supson.model.world.api.WorldLoader;

/**
 * Implementation of the World interface.
 * Manages the game world, including entities, collisions, and game state.
 */
public final class WorldImpl implements World {

    private static final Pos2d DEFAULT_PLAYER_POSITION = new Pos2dImpl(0, 7);

    private final List<TagBlockEntity> blocks;
    private final List<Enemy> enemies;
    private final PlayerManager playerManager;
    private final GameTimer gameTimer;
    private final CollisionResolver collisionResolver;
    private Player player;
    private Optional<Integer> mapWidth;
    private boolean gameOver;

    /**
     * Constructs a new WorldImpl instance.
     */
    public WorldImpl() {
        this.gameOver = false;
        this.blocks = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.player = new Player(DEFAULT_PLAYER_POSITION);
        this.mapWidth = Optional.empty();
        this.playerManager = new PlayerManagerImpl(player);
        this.gameTimer = new GameTimerImpl();
        this.collisionResolver = new CollisionResolver();
        collisionResolver.register((CollisionObserver) playerManager);
    }

    @Override
    public void loadWorld(final String filePath) {
        this.gameTimer.start();
        final WorldLoader loader = new WorldLoaderImpl();
        loader.loadWorld(filePath, this);
    }

    @Override
    public void updateGame(final long elapsed) {
        if (!this.isGameOver()) {
            updateEntities(elapsed);
            handleCollisions();
            player.setState(playerManager.getUpdatedState());
            System.out.println("sono in update");
            if (player.getLife() <= 0) {
                System.out.println("sono in modifica");
                this.setGameOver(true);
            }
        }
    }

    /**
     * Updates the entities in the game world.
     * 
     * @param elapsed the time elapsed since the last update
     */
    private void updateEntities(final long elapsed) {
        final List<MoveableEntity> movEntities = new ArrayList<>(enemies);
        movEntities.add(player);

        movEntities.forEach(e -> {
            final Pos2d oldPos = e.getPosition();
            e.move(elapsed);
            if (e.getGameEntityType().equals(GameEntityType.PLAYER)) {
                playerManager.setState(player.getState());
            }
            collisionResolver.resolvePlatformCollisions(e, List.copyOf(blocks), oldPos);
        });
    }

    /**
     * Handles collisions between entities in the game world.
     */
    private void handleCollisions() {
        final List<Enemy> killed = collisionResolver.resolveEnemiesCollisions(player, List.copyOf(enemies));
        killed.forEach(this::removeEnemy);

        collisionResolver.resolveTrapCollisions(player,
                blocks.stream().filter(b -> b instanceof Trap).map(Trap.class::cast).collect(Collectors.toList()));

        final List<Collectible> activated = collisionResolver.resolveCollectibleCollisions(player,
                blocks.stream().filter(k -> k instanceof Collectible).map(Collectible.class::cast)
                        .collect(Collectors.toList()));
        activated.forEach(this::removeBlock);

        collisionResolver.resolveFinishlineCollision(player, 
                blocks.stream().filter(b -> b instanceof Finishline).map(Finishline.class::cast)
                        .collect(Collectors.toList()), this);
    }

    @Override
    public void reset(final String filePath) {
        this.blocks.clear();
        this.enemies.clear();
        this.player = new Player(DEFAULT_PLAYER_POSITION);
        this.mapWidth = Optional.empty();
        this.gameOver = false;
        this.gameTimer.reset();
        this.loadWorld(filePath);
    }
    
    @Override
    public void addBlock(final TagBlockEntity block) {
        this.blocks.add(block);
    }

    @Override
    public void addEnemy(final Enemy enemy) {
        this.enemies.add(enemy);
    }

    @Override
    public void setPlayerFlags(final boolean right, final boolean left, final boolean jump) {
        playerManager.moveRight(player, right);
        playerManager.moveLeft(player, left);
        playerManager.jump(player, jump);
    }

    @Override
    public void removeBlock(final TagBlockEntity block) {
        this.blocks.remove(block);
    }

    @Override
    public void removeEnemy(final Enemy enemy) {
        this.enemies.remove(enemy);
    }

    @Override
    public List<TagBlockEntity> getBlocks() {
        return new ArrayList<>(this.blocks);
    }

    @Override
    public List<Enemy> getEnemies() {
        return new ArrayList<>(this.enemies);
    }

    @Override
    public Player getPlayer() {
        return new Player(player);
    }

    @Override
    public List<GameEntity> getGameEntities() {
        final List<GameEntity> entities = new ArrayList<>();
        entities.addAll(this.blocks);
        entities.addAll(this.enemies);
        entities.add(this.player);
        return entities;
    }

    @Override
    public Hud getHud() {
        return new HudImpl(this.player.getScore(), this.player.getLife(), this.gameTimer.getElapsedTimeInSeconds());
    }
    
    @Override
    public void setMapWidth(final Optional<Integer> mapWidth) {
        this.mapWidth = mapWidth;
    }

    @Override
    public Integer getMapWidth() {
        return mapWidth.orElse(0);
    }

    @Override
    public void setGameOver(final boolean gameOver) {
        this.gameOver = gameOver;
        if (this.isGameOver()) {
            this.gameTimer.stop();
        }
    }
    
    @Override
    public Boolean isWon() {
        if (player.getLife() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isGameOver() {
        return gameOver;
    }

}