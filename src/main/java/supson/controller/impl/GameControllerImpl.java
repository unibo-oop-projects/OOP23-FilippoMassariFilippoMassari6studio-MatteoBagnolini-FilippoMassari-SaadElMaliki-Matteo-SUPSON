package supson.controller.impl;

import supson.controller.api.GameController;
import supson.model.world.api.World;
import supson.model.world.impl.WorldImpl;
import supson.view.api.GameView;
import supson.view.impl.GameViewImpl;

/**
 * This class, which implements the GameController interface, models the game controller.
 * It is the coordinator between the view and the model.
 */
public final class GameControllerImpl implements GameController {

    private static final String WORLD_FILE_PATH = "src\\resources\\world.txt";

    private final World model;
    private final GameView view;

    /**
     * This is the GameControllerImpl constructor.
     */
    public GameControllerImpl() {
        this.model = new WorldImpl();
        this.view = new GameViewImpl();
    }


    @Override
    public void processInput() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'processInput'");
    }

    @Override
    public void update(final long elapsed) {
        this.model.updateGame(elapsed);
    }

    @Override
    public void render() {
        this.view.renderGame(model.getCameraBlocks(), model.getCameraEnemies(), model.getPlayer());
    }

    @Override
    public void initGame() {
        this.model.loadWorld(WORLD_FILE_PATH);
        this.view.renderStartGame();
    }

}
