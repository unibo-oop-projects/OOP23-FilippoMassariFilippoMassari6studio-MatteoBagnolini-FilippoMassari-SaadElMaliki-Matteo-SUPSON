package supson.model.world.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import supson.common.GameEntityType;
import supson.common.api.Pos2d;
import supson.common.api.Vect2d;
import supson.common.impl.Pos2dImpl;
import supson.common.impl.Vect2dImpl;
import supson.model.block.api.BlockEntity;
import supson.model.block.api.Collectible;
import supson.model.block.impl.TerrainImpl;
import supson.model.entity.api.MoveableEntity;
import supson.model.entity.impl.Enemy;
import supson.model.entity.impl.Player;
import supson.model.hitbox.impl.CollisionResolver;
import supson.model.world.api.World;

/**
 * Implementation of the World interface.
 */
public final class WorldImpl implements World { //todo : rivederre metodi con classi che ancora non esistono mene enemy e trap

    private static final int INT_OF_PLAYER = 6; //todo: soluzione potaenzialmente migliorabile
    private static final int INT_OF_ENEMY = 7;

    private static final Pos2d DEFAULT_PLAYER_POSITION = new Pos2dImpl(0, 0);
    private static final Vect2d DEFAULT_PLAYER_VELOCITY = new Vect2dImpl(0, 0);
    private static final int DEFAULT_PLAYER_LIFE = 3;

    private static final Vect2d DEFAULT_ENEMY_VELOCITY = new Vect2dImpl(0, 0);
    private static final int DEFAULT_ENEMY_LIFE = 1;
    private static final int DEFAUL_ENEMY_RANGE = 0;

    private final List<BlockEntity> blocks;
    private final List<Enemy> enemies;
    private final Player player;

    /**
     * Constructs a new instance of the WorldImpl class.
     * Initializes the lists for blocks, enemies and player.
     */
    public WorldImpl() {
        this.blocks = new ArrayList<BlockEntity>();
        this.enemies = new ArrayList<Enemy>();
        this.player = new Player(DEFAULT_PLAYER_POSITION, DEFAULT_PLAYER_VELOCITY, DEFAULT_PLAYER_LIFE);
    }

    @Override
    public void loadWorld(final String filePath) {
        final Map<Integer, GameEntityType> blocksMap = new HashMap<>();
        blocksMap.put(1, GameEntityType.TERRAIN);
        blocksMap.put(2, GameEntityType.LIFE_BOOST_POWER_UP);
        blocksMap.put(3, GameEntityType.STRNGTH_BOOST_POWER_UP);
        blocksMap.put(4, GameEntityType.RING);
        blocksMap.put(5, GameEntityType.DAMAGE_TRAP); //todo : me lo da magic number non so il perchè

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int y = 0;
            while ((line = reader.readLine()) != null) { //todo : cambiare readear per renderlo standard
                String[] tokens = line.split(" ");
                for (int x = 0; x < tokens.length; x++) {
                    int worldElement = Integer.parseInt(tokens[x]);
                    Pos2d pos = new Pos2dImpl(x, y);
                    if (worldElement == INT_OF_PLAYER) { //todo : graffe da verificare
                        this.player.setPosition(pos);
                    }
                    else if (worldElement == INT_OF_ENEMY) {
                        this.addEnemy(pos); //todo : sicuramente il costuttotr di enmy cambierà
                    }
                    else { 
                        Optional<GameEntityType> optionalType = Optional.ofNullable(blocksMap.get(worldElement));
                        optionalType.ifPresent(type -> {
                            this.addBlock(pos);
                        });
                    }
                }
                y++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a new block to the world at the specified position with the specified type.
     *
     * @param pos  The position where the block should be added.
     * @param type The type of the block to be added.
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

    @Override
    public void reset(final String filePath) {
        this.blocks.clear();
        this.enemies.clear();
        this.player.setPosition(DEFAULT_PLAYER_POSITION);
        this.loadWorld(filePath);
    }

    @Override
    public void updateGame(long elapsed) {
        final List<MoveableEntity> movEntities = new ArrayList<>(enemies);
        movEntities.add(player);

        movEntities.stream()
        .forEach(e -> {
            Pos2d oldPos = e.getPosition();
            e.move(elapsed);
            CollisionResolver.resolvePlatformCollisions(e, blocks, oldPos);
        });

        final List<Enemy> killed = CollisionResolver.resolveEnemiesCollisions(player, enemies);
        killed.forEach(k -> removeEnemy(k));

        final List<Collectible> activated = CollisionResolver.resolveCollectibleCollisions(player,
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
    public Player getPlayer() {
        return this.player; //todo : non passo copia difensiva verificare che sia giusto
    }

}
