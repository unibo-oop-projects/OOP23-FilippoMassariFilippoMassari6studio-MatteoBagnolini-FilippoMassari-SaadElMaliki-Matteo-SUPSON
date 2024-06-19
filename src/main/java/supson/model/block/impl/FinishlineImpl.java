package supson.model.block.impl;

import supson.common.GameEntityType;
import supson.common.api.Pos2d;
import supson.model.block.api.Finishline;

import supson.model.entity.impl.AbstractGameEntity;
import supson.model.world.api.World;

/**
 * Represents a finishline in the game.
 * This finishline triggers the end of the current game world.
 */
public class FinishlineImpl extends AbstractGameEntity implements Finishline {

    private static final int HEIGHT = 2;
    private static final int WIDTH = 1;

    private static final GameEntityType TYPE = GameEntityType.FINISHLINE;

    /**
     * Construcs a new FinishlineImpl object with the specified position.
     * 
     * @param pos the position of the finishline
     */
    public FinishlineImpl(final Pos2d pos) {
        super(pos, HEIGHT, WIDTH, TYPE);
    }

    @Override
    public void endGame(final World world) {
        world.setGameOver(true);
        System.out.println("GAME OVER");
    }

}
