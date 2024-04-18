package supson.model.world.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import supson.common.api.Pos2d;
import supson.common.impl.Pos2dImpl;
import supson.model.block.BlockType;
import supson.model.block.api.BlockEntity;
import supson.model.block.impl.BlockEntityImpl;
import supson.model.entity.impl.Enemy;
import supson.model.entity.impl.Player;
import supson.model.world.api.World;

/**
 * Implementation of the World interface.
 */
public final class WorldImpl implements World { //todo : rivederre metodi con classi che ancora non esistono mene enemy e trap

    private final int INT_OF_PLAYER = 6;
    private final int INT_OF_ENEMY = 7;
    private final Pos2d DEFAULT_PLAYER_POSITION = new Pos2dImpl(0, 0);

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
        this.player = new Player(DEFAULT_PLAYER_POSITION, null, 0); //todo : add parameters to the constructor
    }

    @Override
    public void loadWorld(final String filePath) {
        final Map<Integer, BlockType> blocksMap = new HashMap<>();
        blocksMap.put(1, BlockType.TERRAIN);
        blocksMap.put(2, BlockType.LIFE_BOOST_POWER_UP);
        blocksMap.put(3, BlockType.STRNGTH_BOOST_POWER_UP);
        blocksMap.put(4, BlockType.RING);
        blocksMap.put(5, BlockType.TRAP);

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int y = 0;
            while ((line = reader.readLine()) != null) { //todo : cambiare readear per renderlo standard
                String[] tokens = line.split(" ");
                for (int x = 0; x < tokens.length; x++) {
                    int worldElement = Integer.parseInt(tokens[x]);
                    Pos2d pos = new Pos2dImpl(x, y);
                    if (worldElement==INT_OF_PLAYER) {
                        this.player.setPosition(pos);
                    }
                    else if (worldElement== INT_OF_ENEMY) {
                        this.addEnemy(pos); //todo : sicuramente il costuttotr di enmy cambierà
                    }
                    else { 
                        Optional<BlockType> optionalType = Optional.ofNullable(blocksMap.get(worldElement));
                        optionalType.ifPresent(type -> {
                            this.addBlock(pos, type);
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
    private void addBlock(final Pos2d pos, final BlockType type) {
        this.blocks.add(new BlockEntityImpl(pos, type));
    }

    /**
     * Adds a new enemy to the world at the specified position.
     *
     * @param pos The position where the enemy should be added.
     */
    private void addEnemy(final Pos2d pos) { //c'è un check stile da verificare qui
        this.enemies.add(new Enemy(pos, null, 0, 0)); //todo : add parameters to the constructor
    }

    @Override
    public void reset(final String filePath) {
        this.blocks.clear();
        this.enemies.clear();
        this.player.setPosition(null); //todo : add parameters to the constructor
        this.loadWorld(filePath);
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
        return new ArrayList<BlockEntity>(this.blocks); //verificare che sia giusto
    }

    @Override
    public List<Enemy> getEnemies() {
        return new ArrayList<Enemy>(this.enemies); //verificare che sia giusto 
    }

    @Override
    public Pos2d getPlayerPosition() {
        return player.getPosition();
    }

}
