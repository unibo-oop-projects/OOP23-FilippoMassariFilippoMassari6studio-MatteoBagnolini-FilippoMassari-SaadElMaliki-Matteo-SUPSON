package supson.controller.impl;

import javax.swing.JFrame;

import supson.controller.api.GameController;
import supson.controller.api.GameStateManager;
import supson.model.world.api.World;
import supson.model.world.impl.WorldImpl;
import supson.view.impl.InputManager;

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
        if(true) {
            this.model.updateGame(elapsed);
            //if(this.model.isGameOver())
                //this.stateManager.setState(new ScoreState("ciao", mainFrame, this));
        }

    }

    @Override
    public void render() {
        stateManager.getState().render();
    }

    @Override
    public void initGame() {
        this.stateManager.setState(new StartState(this,mainFrame));
    }

    @Override
    public void startGame() {
        this.model.loadWorld(WORLD_FILE_PATH);
        this.mainFrame.addKeyListener(input);
        this.mainFrame.requestFocusInWindow();
        this.stateManager.setState(new PlayState(mainFrame,model));
    }

    @Override
    public void showScore() {
        this.stateManager.setState(new ScoreState("ciao", mainFrame, this));
    }

    @Override
    public void quitGame() {
    }

}
