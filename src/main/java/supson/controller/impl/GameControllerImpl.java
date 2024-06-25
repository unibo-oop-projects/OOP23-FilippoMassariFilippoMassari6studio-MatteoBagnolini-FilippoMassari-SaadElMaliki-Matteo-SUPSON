package supson.controller.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import supson.controller.api.GameController;
import supson.core.api.GameEngine;
import supson.core.impl.GameEngineImpl;
import supson.model.world.api.World;
import supson.model.world.impl.WorldImpl;
import supson.view.api.GameView;
import supson.view.impl.GameViewImpl;
import supson.view.impl.InputManager;

/**
 * This class, which implements the GameController interface, models the game controller.
 * It is the coordinator between the view and the model.
 */
public final class GameControllerImpl implements GameController, ActionListener {

    private static final int WIDTH = 948;
    private static final int HEIGHT = 720;

    private static final String WORLD_FILE_PATH = "/level/level_1.txt";

    private final World model;
    private final GameView gameView;
    private final InputManager input;
    private final JFrame mainFrame;
    private final GameEngine gameEngine;

    /**
     * This is the GameControllerImpl constructor.
     */
    public GameControllerImpl() {
        this.gameEngine = new GameEngineImpl(this);
        this.model = new WorldImpl();
        this.mainFrame = new JFrame("SUPER-SONIC");
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setSize(WIDTH, HEIGHT);
        this.gameView = new GameViewImpl(mainFrame);
        this.input = new InputManager();
        this.mainFrame.addKeyListener(input);
    }

    @Override
    public void processInput() {
        this.model.setPlayerFlags(input.isRight(), input.isLeft(), input.isJump());
    }

    @Override
    public void update(final long elapsed) {
        if(gameEngine.isGameRunning()) {
            if (model.isGameOver()) {
            gameView.renderEndGame(model.isWon(),this);
        } else {
            this.model.updateGame(elapsed);
        }
    }
    }

    @Override
    public void render() {
        this.gameView.renderView(this.model.getGameEntities(), this.model.getPlayer(), this.model.getHud());
    }

    @Override
    public void initGame() {
        this.model.loadWorld(WORLD_FILE_PATH);
        this.gameView.initView();
        //this.gameEngine.startGameLoop();
        this.gameView.renderMenu(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Play")){
            this.gameEngine.startGameLoop();
        }
        if(e.getActionCommand().equals("Quit")){
            System.exit(0);
        }
    }

    public void startGame() {
        //this.gameView.renderStartMenu();
    }

}
