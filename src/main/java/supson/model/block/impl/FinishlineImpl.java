package supson.model.block.impl;

import supson.common.GameEntityType;
import supson.common.api.Pos2d;
import supson.model.block.api.Finishline;

import supson.model.entity.impl.AbstractGameEntity;
import supson.model.world.api.World;

public class FinishlineImpl extends AbstractGameEntity implements Finishline {

    private static final int HEIGHT = 4;
    private static final int WIDTH = 1;

    private static final GameEntityType TYPE = GameEntityType.FINISHLINE;


    public FinishlineImpl(Pos2d pos) {
        super(pos, HEIGHT, WIDTH, TYPE);
    }

    @Override
    public void endGame(World world) {
        world.setGameOver(true);
        System.out.println("GAME OVER");
    }


}
