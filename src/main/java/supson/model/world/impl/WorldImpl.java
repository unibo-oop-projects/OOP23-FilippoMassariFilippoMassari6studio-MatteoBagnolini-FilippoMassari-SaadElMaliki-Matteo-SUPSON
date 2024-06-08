package supson.model.world.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import supson.common.GameEntityType;
import supson.common.api.Pos2d;
import supson.common.impl.Pos2dImpl;
import supson.model.block.api.BlockEntity;
import supson.model.block.api.Collectible;
import supson.model.block.api.Trap;
import supson.model.block.impl.DamageTrapImpl;
import supson.model.block.impl.SubTerrainImpl;
import supson.model.block.impl.TerrainImpl;
import supson.model.collisions.api.CollisionObserver;
import supson.model.collisions.impl.CollisionResolver;
import supson.model.entity.api.GameEntity;
import supson.model.entity.api.MoveableEntity;
import supson.model.entity.api.PlayerManager;
import supson.model.entity.impl.Enemy;
import supson.model.entity.player.Player;
import supson.model.entity.player.PlayerManagerImpl;
import supson.model.hud.api.Hud;
import supson.model.hud.impl.HudImpl;
import supson.model.timer.api.GameTimer;
import supson.model.timer.impl.GameTimerImpl;
import supson.model.world.api.World;
import supson.model.world.api.WorldLoader;

public final class WorldImpl implements World {

    private static final Pos2d DEFAULT_PLAYER_POSITION = new Pos2dImpl(0, 7);

    private final List<BlockEntity> blocks;
    private final List<Enemy> enemies;
    private final Player player;
    private Optional<Integer> mapWidth;
    private final PlayerManager playerManager;
    private final GameTimer gameTimer;
    private final CollisionResolver collisionResolver;
    private boolean gameOver;

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
        this.gameTimer.start(); // For debug
        WorldLoader loader = new WorldLoaderImpl();
        loader.loadWorld(filePath, this);
    }

    @Override
    public void reset(final String filePath) {
        this.blocks.clear();
        this.enemies.clear();
        this.player.setPosition(DEFAULT_PLAYER_POSITION);
        this.loadWorld(filePath);
    }

    @Override
    public void updateGame(final long elapsed) {
        updateEntities(elapsed);
        handleCollisions();
        player.setState(playerManager.getUpdatedState());
        if (player.getLife() < 0) {
            this.gameOver = true;
        }
    }

    private void updateEntities(final long elapsed) {
        List<MoveableEntity> movEntities = new ArrayList<>(enemies);
        movEntities.add(player);

        movEntities.forEach(e -> {
            Pos2d oldPos = e.getPosition();
            e.move(elapsed);
            if (e.getGameEntityType().equals(GameEntityType.PLAYER)) {
                playerManager.setState(player.getState());
            }
            collisionResolver.resolvePlatformCollisions(e, List.copyOf(blocks), oldPos);
        });
    }

    private void handleCollisions() {
        List<Enemy> killed = collisionResolver.resolveEnemiesCollisions(player, List.copyOf(enemies));
        killed.forEach(this::removeEnemy);

        collisionResolver.resolveTrapCollisions(player,
                blocks.stream().filter(b -> b instanceof Trap).map(Trap.class::cast).collect(Collectors.toList()));

        List<Collectible> activated = collisionResolver.resolveCollectibleCollisions(player,
                blocks.stream().filter(k -> k instanceof Collectible).map(Collectible.class::cast)
                        .collect(Collectors.toList()));
        activated.forEach(this::removeBlock);
    }

    @Override
    public void removeBlock(final BlockEntity block) {
        this.blocks.remove(block);
    }

    @Override
    public void removeEnemy(final Enemy enemy) {
        this.enemies.remove(enemy);
    }

    @Override
    public void addBlock(final GameEntityType type, final Pos2d pos) {
        BlockEntity block;
        switch (type) {
            case DAMAGE_TRAP:
                block = new DamageTrapImpl(pos);
                break;
            case SUBTERRAIN:
                block = new SubTerrainImpl(pos);
                break;
            default:
                block = new TerrainImpl(pos);
                break;
        }
        this.blocks.add(block);
    }

    @Override
    public void addEnemy(final Enemy enemy) {
        this.enemies.add(enemy);
    }

    @Override
    public void addCollectible(final Collectible collectible) {
        this.blocks.add(collectible);
    }

    @Override
    public List<BlockEntity> getBlocks() {
        return new ArrayList<>(this.blocks);
    }

    @Override
    public List<Enemy> getEnemies() {
        return new ArrayList<>(this.enemies);
    }

    @Override
    public List<GameEntity> getGameEntities() {
        List<GameEntity> entities = new ArrayList<>();
        entities.addAll(this.blocks);
        entities.addAll(this.enemies);
        entities.add(this.player);
        return entities;
    }

    @Override
    public Player getPlayer() {
        return this.player;
    }

    @Override
    public Hud getHud() {
        return new HudImpl(this.player.getScore(), this.player.getLife(), this.gameTimer.getElapsedTimeInSeconds());
    }

    @Override
    public Integer getMapWidth() {
        return mapWidth.orElse(0);
    }

    @Override
    public void setMapWidth(Optional<Integer> mapWidth) {
        this.mapWidth = mapWidth;
    }

    @Override
    public boolean isGameOver() {
        return gameOver;
    }
}
