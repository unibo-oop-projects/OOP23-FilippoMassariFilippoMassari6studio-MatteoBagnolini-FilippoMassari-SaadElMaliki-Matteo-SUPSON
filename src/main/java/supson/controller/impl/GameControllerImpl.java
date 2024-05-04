package supson.controller.impl;

import supson.controller.api.GameController;
import supson.model.world.api.World;
import supson.model.world.impl.WorldImpl;
import supson.view.impl.GameViewImpl;

/**
 * This class, which implements the GameController interface, models the game controller.
 * It is the coordinator between the view and the model.
 */
public class GameControllerImpl implements GameController {

    private static final String WORLD_FILE_PATH = "src\\resources\\world.txt";
    
    private final World model;
    private final GameViewImpl view;

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
    public void update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void render() {
        this.view.renderGame(model.getBlocks(), model.getEnemies(), model.getPlayer());
    }

    @Override
    public void initGame() {
        this.model.loadWorld(WORLD_FILE_PATH);
        this.view.renderStartGame();
    }
    
}
