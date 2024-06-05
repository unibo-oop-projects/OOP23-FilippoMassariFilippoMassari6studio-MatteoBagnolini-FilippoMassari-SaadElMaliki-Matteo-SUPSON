package supson.controller.impl;

import supson.controller.api.GameController;
import supson.model.world.api.World;
import supson.model.world.impl.WorldImpl;
import supson.view.api.GameStateManager;
import supson.view.api.GameView;
import supson.view.api.StartView;
import supson.view.impl.GameStateManagerImpl;
import supson.view.impl.GameViewImpl;
import supson.view.impl.InputManager;
import supson.view.impl.PlayState;
import supson.view.impl.StartState;

/**
 * This class, which implements the GameController interface, models the game controller.
 * It is the coordinator between the view and the model.
 */
public final class GameControllerImpl implements GameController {

    private static final String WORLD_FILE_PATH = "/level_1.txt";

    private final World model;
    private final GameStateManager stateManager;
    private final GameView view;
    private final InputManager input;

    /**
     * This is the GameControllerImpl constructor.
     */
    public GameControllerImpl() {
        this.model = new WorldImpl();
        this.view = new GameViewImpl();
        this.input = new InputManager();
        stateManager = new GameStateManagerImpl();
    }

    @Override
    public void processInput() {
        this.model.getPlayer().setMoveLeft(input.isLeft());
        this.model.getPlayer().setMoveRight(input.isRight());
        this.model.getPlayer().setJump(input.isJump());
    }

    @Override
    public void update(final long elapsed) {
        this.model.updateGame(elapsed);
    }

    @Override
    public void render() {
        stateManager.getState().render();
    }

    @Override
    public void initGame() {
        this.model.loadWorld(WORLD_FILE_PATH);
        this.stateManager.setState(new StartState(stateManager, model, input));
        //this.view2.initView();
        //this.view.initView();
        //this.view.getViewComponent().addKeyListener(input);
    }

}
