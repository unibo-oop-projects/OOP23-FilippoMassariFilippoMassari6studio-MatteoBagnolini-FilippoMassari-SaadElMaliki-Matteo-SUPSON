package supson.model.world.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import supson.common.GameEntityType;
import supson.common.api.Pos2d;
import supson.common.api.Vect2d;
import supson.common.impl.Pos2dImpl;
import supson.common.impl.Vect2dImpl;
import supson.model.block.api.BlockEntity;
import supson.model.block.api.Collectible;
import supson.model.block.api.CollectibleFactory;
import supson.model.block.api.Trap;
import supson.model.block.impl.CollectibleFactoryImpl;
import supson.model.block.impl.TerrainImpl;
import supson.model.entity.api.GameEntity;
import supson.model.entity.api.MoveableEntity;
import supson.model.entity.api.PlayerManager;
import supson.model.entity.impl.Enemy;
import supson.model.entity.player.Player;
import supson.model.entity.player.PlayerManagerImpl;
import supson.model.hitbox.impl.CollisionResolver;
import supson.model.hud.api.Hud;
import supson.model.hud.impl.HudImpl;
import supson.model.timer.api.GameTimer;
import supson.model.timer.impl.GameTimerImpl;
import supson.model.world.api.World;

/**
 * Implementation of the World interface.
 */
public final class WorldImpl implements World {

    private final CollectibleFactory collectibleFactory;

    private static final Pos2d DEFAULT_PLAYER_POSITION = new Pos2dImpl(1, 7.5);
    private static final Vect2d DEFAULT_PLAYER_VELOCITY = new Vect2dImpl(0, 0);
    private static final int DEFAULT_PLAYER_LIFE = 3;

    private static final Vect2d DEFAULT_ENEMY_VELOCITY = new Vect2dImpl(0, 0);
    private static final int DEFAULT_ENEMY_LIFE = 1;
    private static final int DEFAUL_ENEMY_RANGE = 0;

    private final List<BlockEntity> blocks;
    private final List<Enemy> enemies;
    private final Player player;
    private final PlayerManagerImpl playerManager;
    private final GameTimer gameTimer;
    private final CollisionResolver collisionResolver;

    /**
     * Constructs a new instance of the WorldImpl class.
     * Initializes the lists for blocks, enemies and player.
     */
    public WorldImpl() {
        this.blocks = new ArrayList<BlockEntity>();
        this.enemies = new ArrayList<Enemy>();
        this.player = new Player(DEFAULT_PLAYER_POSITION, DEFAULT_PLAYER_VELOCITY, DEFAULT_PLAYER_LIFE);
        this.playerManager = new PlayerManagerImpl(player);
        this.collectibleFactory = new CollectibleFactoryImpl();
        this.gameTimer = new GameTimerImpl();
        this.collisionResolver = new CollisionResolver();
        collisionResolver.register(playerManager);
    }

    @Override
    public void loadWorld(final String filePath) {
        this.gameTimer.start(); //for debug
        final EntityMap entityMap = new EntityMap();
        try (InputStream inputStream = getClass().getResourceAsStream(filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            List<String> lines = reader.lines().collect(Collectors.toList());
            int maxY = lines.size() - 1;
            for (int y = maxY; y >= 0; y--) {
                String[] tokens = lines.get(y).split(" ");
                for (int x = 0; x < tokens.length; x++) {
                    if (!tokens[x].equals("0")) {
                        int worldElement = Integer.parseInt(tokens[x]);
                        Pos2d pos = new Pos2dImpl(x, maxY - y);
                        Optional<GameEntityType> optionalType = Optional.ofNullable(entityMap.getEntityType(worldElement));
                        optionalType.ifPresent(type -> {
                            if (type.equals(GameEntityType.ENEMY)) {
                                this.addEnemy(pos);
                            } else if (type.equals(GameEntityType.TERRAIN) || type.equals(GameEntityType.DAMAGE_TRAP)) {
                                this.addBlock(pos);
                            } else {
                                this.addCollectable(pos, type);
                            }
                        });
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a new block to the world at the specified position with the specified type.
     *
     * @param pos  The position where the block should be added.
     */
    private void addBlock(final Pos2d pos) {
        this.blocks.add(new TerrainImpl(pos));
    }

    /**
     * Adds a new enemy to the world at the specified position.
     *
     * @param pos The position where the enemy should be added.
     */
    private void addEnemy(final Pos2d pos) { //c'è un check stile da verificare qui
        this.enemies.add(new Enemy(pos, DEFAULT_ENEMY_VELOCITY, DEFAULT_ENEMY_LIFE, DEFAUL_ENEMY_RANGE));
    }

    /**
     * Adds a collectable to the world at the specified position.
     * 
     * @param pos  the position where the collectable should be added
     * @param type the type of collectable to add
     */
    private void addCollectable(final Pos2d pos, final GameEntityType type) {
        Collectible collectable = collectibleFactory.createCollectible(type, pos);
        this.blocks.add(collectable);
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
        final List<MoveableEntity> movEntities = new ArrayList<>(enemies);
        movEntities.add(player);

        movEntities.stream()
        .forEach(e -> {
            Pos2d oldPos = e.getPosition();
            e.move(elapsed);
            collisionResolver.resolvePlatformCollisions(e, blocks, oldPos);
        });

        final List<Enemy> killed = collisionResolver.resolveEnemiesCollisions(player, enemies);
        killed.forEach(k -> removeEnemy(k));

        collisionResolver.resolveTrapCollisions(player,
            blocks.stream().filter(b -> b instanceof Trap).map(Trap.class::cast).collect(Collectors.toList()));

        final List<Collectible> activated = collisionResolver.resolveCollectibleCollisions(player,
            blocks.stream().filter(k -> k instanceof Collectible).map(Collectible.class::cast)
            .collect(Collectors.toList()));
        activated.forEach(k -> removeBlock(k));
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
    public List<BlockEntity> getBlocks() {
        return new ArrayList<BlockEntity>(this.blocks);
    }

    @Override
    public List<Enemy> getEnemies() {
        return new ArrayList<Enemy>(this.enemies); 
    }

    @Override
    public List<GameEntity> getGameEntities() {
        final List<GameEntity> entities = new ArrayList<GameEntity>();
        entities.addAll(this.blocks);
        entities.addAll(this.enemies);
        entities.add(this.player);
        return entities;
    }

    @Override
    public Player getPlayer() {
        return this.player; //todo : non passo copia difensiva verificare che sia giusto
    }

    @Override
    public Hud getHud() {
        return new HudImpl(this.player.getScore(), this.player.getLife(), this.gameTimer.getElapsedTimeInSeconds());
    }

}
