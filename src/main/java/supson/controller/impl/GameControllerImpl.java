package supson.controller.impl;

import javax.swing.JFrame;

import supson.controller.api.GameController;
import supson.model.world.api.World;
import supson.model.world.impl.WorldImpl;
import supson.view.api.GameStateManager;
import supson.view.impl.GameStateManagerImpl;
import supson.view.impl.InputManager;
import supson.view.impl.PlayState;
import supson.view.impl.ScoreState;
import supson.view.impl.StartState;

/**
 * This class, which implements the GameController interface, models the game controller.
 * It is the coordinator between the view and the model.
 */
public final class GameControllerImpl implements GameController {

    private static final int WHIDTH = 948;
    private static final int HEIGHT = 720;

    private static final String WORLD_FILE_PATH = "/level_1.txt";

    private final World model;
    private final GameStateManager stateManager;
    private final InputManager input;
    private final JFrame mainFrame;

    /**
     * This is the GameControllerImpl constructor.
     */
    public GameControllerImpl() {
        this.model = new WorldImpl();
        this.mainFrame = new JFrame("SUPER-SONIC");
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setSize(WHIDTH, HEIGHT);
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
        this.stateManager.setState(new StartState(this,mainFrame));
    }

    @Override
    public void startGame() {
        this.mainFrame.addKeyListener(input);
        this.mainFrame.requestFocusInWindow();
        this.stateManager.setState(new PlayState(mainFrame,model));
    }

    @Override
    public void showScores() {
        this.stateManager.setState(new ScoreState());
    }

    @Override
    public void quitGame() {
    }

}
