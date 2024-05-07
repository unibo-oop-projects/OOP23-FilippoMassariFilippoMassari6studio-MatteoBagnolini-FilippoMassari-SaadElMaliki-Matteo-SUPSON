package supson.model.block.impl;

import java.io.File;

import supson.common.api.Pos2d;
import supson.model.block.BlockType;
import supson.model.entity.impl.Player;

/**
 * Represents a trap block in the game.
 * Inherits from the TerrainImpl class.
 */
public final class TrapImpl extends TerrainImpl {

    private static final File SPRITE = new File("src/main/resources/trap.png");

    private static final int DAMAGE = 2;

    /**
     * Constructs a TrapImpl object with the specified position and block type.
     * @param pos The position of the trap block.
     * @param blockType The type of the trap block.
     */
    public TrapImpl(final Pos2d pos, final BlockType blockType) {
        super(pos, blockType);
        this.sprite = SPRITE;
    }

    /**
     * Activates the trap and applies damage to the player.
     * @param player The player object to be affected by the trap.
     */
    public void activate(final Player player) {
        player.setLife(player.getLife() - DAMAGE);
    }

}
