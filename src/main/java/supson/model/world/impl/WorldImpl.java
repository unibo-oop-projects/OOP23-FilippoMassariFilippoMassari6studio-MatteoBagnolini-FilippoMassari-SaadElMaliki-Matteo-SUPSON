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
import supson.model.entity.api.MoveableEntity;
import supson.model.entity.impl.Player;
import supson.model.world.api.World;

/**
 * Implementation of the World interface.
 */
public class WorldImpl implements World {

    private final List<BlockEntity> rings;
    private final List<BlockEntity> blocks;
    private final List<MoveableEntity> enemies;
    private Player player; //forse final?

    /**
    * Constructs a new instance of the WorldImpl class.
    * Initializes the lists for rings, blocks, and enemies.
    */
    public WorldImpl() {
        this.rings = new ArrayList<>();
        this.blocks = new ArrayList<>();
        this.enemies = new ArrayList<>();
        //this.player = new Player(); //correggere javadoc
    }

    @Override
    public final void loadWorld(final String filePath) {
        Map<Integer, BlockType> blockTypeMap = new HashMap<>();
        blockTypeMap.put(1, BlockType.TERRAIN);
        blockTypeMap.put(2, BlockType.POWER_UP);
        blockTypeMap.put(3, BlockType.RING);
        blockTypeMap.put(4, BlockType.TRAP);  

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int y = 0;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(" ");
                for (int x = 0; x < tokens.length; x++) {
                    int blockType = Integer.parseInt(tokens[x]);
                    Pos2d pos = new Pos2dImpl(x, y);
                    Optional<BlockType> optionalType = Optional.ofNullable(blockTypeMap.get(blockType));
                    optionalType.ifPresent(type -> {
                        switch (type) {
                            case TERRAIN:
                                addTerrain(pos);
                                break;
                            case POWER_UP:
                                addPowerUp(pos);
                                break;
                            case RING:
                                addRing(pos);
                                break;
                            case TRAP:
                                addTrap(pos);
                                break;
                            default:
                                break;
                        }
                    });
                }
                y++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
}

    @Override
    public final void reset() {
        this.rings.removeAll(this.rings);
        this.blocks.removeAll(this.blocks);
        this.enemies.removeAll(this.enemies);
        //this.loadWorld(); //todo : add parameters to the method
    }

    @Override
    public final void addRing(final Pos2d pos) {
        this.rings.add(new BlockEntityImpl(pos, BlockType.RING));
    }

    @Override
    public final void removeRing(final BlockEntity ring) {
        this.rings.remove(ring);
    }

    @Override
    public final void addPowerUp(final Pos2d pos) {
        this.blocks.add(new BlockEntityImpl(pos, BlockType.POWER_UP));
    }

    @Override
    public final void removePowerUp(final BlockEntity powerUp) {
        this.blocks.remove(powerUp);
    }

    @Override
    public final void addTrap(final Pos2d pos) {
        this.blocks.add(new BlockEntityImpl(pos, BlockType.TRAP));
    }

    @Override
    public final void removeTrap(final BlockEntity trap) {
        this.blocks.remove(trap);
    }

    @Override
    public final void addTerrain(final Pos2d pos) {
        this.blocks.add(new BlockEntityImpl(pos, BlockType.TERRAIN));
    }

    @Override
    public final void removeTerrain(final BlockEntity terrain) {
        this.blocks.remove(terrain);
    }
    
    @Override
    public final void addEnemy(final Pos2d pos) {
        this.enemies.add(new Player(pos, 0, 0, null, 0)); //todo : add parameters to the constructor
    }

    @Override
    public final void removeEnemy(final MoveableEntity enemy) {
        this.enemies.remove(enemy);
    }

    @Override
    public final void addPlayer(final Pos2d pos) {
        this.player = new Player(pos, 0, 0, null, 0); //todo : add parameters to the constructor
    }

}
