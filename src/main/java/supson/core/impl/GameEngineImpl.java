package supson.core.impl;

import supson.core.api.GameEngine;
import supson.model.world.api.World;
import supson.model.world.impl.WorldImpl;
import supson.view.ViewEvent;
import supson.view.api.GameView;
import supson.view.impl.GameViewImpl;
import supson.view.impl.InputManager;

import javax.swing.JFrame;

/**
 * This class represents the main engine of the game.
 */
public final class GameEngineImpl implements GameEngine {

    private static final int WIDTH = 948;
    private static final int HEIGHT = 720;

    private static final String WORLD_FILE_PATH = "/level/level_1.txt";

    private static final long REFRESH_RATE = 20;

    private GameState state;

    private final World model;
    private final GameView view;
    private final InputManager input;
    private final JFrame mainFrame;

    /**
     * GameEngine constructor.
     */
    public GameEngineImpl() {
        this.model = new WorldImpl();
        this.mainFrame = new JFrame("SUPER-SONIC");
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setSize(WIDTH, HEIGHT);
        this.view = new GameViewImpl(mainFrame);
        this.input = new InputManager();
        this.mainFrame.addKeyListener(input);
        // this.view.addInputManager(input);
        this.state = GameState.LAUNCHER;
    }

    @Override
    public void initGame() {
        this.model.loadWorld(WORLD_FILE_PATH);
        this.view.initView();
    }

    @Override
    public void mainControl() {
        if (state.equals(GameState.LAUNCHER)) {
            //this.view.renderStartMenu();
        }
        if (state.equals(GameState.RUNNING)) {
            gameLoop();
        }
        if (state.equals(GameState.GAMEOVER_WON)) {
            //this.view.renderEndGameWon();
        } else if (state.equals(GameState.GAMEOVER_LOST)) {
            //this.view.renderEndGameLost();
        }
    }

    private void gameLoop() {
        long previousCycleStartTime = System.currentTimeMillis();
        while (!this.model.isGameOver()) {
            final long currentCycleStartTime = System.currentTimeMillis();
            final long elapsed = currentCycleStartTime - previousCycleStartTime;
            processInput();
            updateGame(elapsed);
            render();
            waitForNextFrame(currentCycleStartTime);
            previousCycleStartTime = currentCycleStartTime;
        }
        if (this.model.isWon()) {
            this.state = GameState.GAMEOVER_WON;
        } else {
            this.state = GameState.GAMEOVER_LOST;
        }
    }

    @Override
    public void processInput() {
        this.model.setPlayerFlags(input.isRight(), input.isLeft(), input.isJump());
    }

    @Override
    public void updateGame(final long elapsed) {
        this.model.updateGame(elapsed);
    }

    @Override
    public void render() {
        this.view.renderView(this.model.getGameEntities(), this.model.getPlayer(), this.model.getHud());
    }

    @Override
    public void waitForNextFrame(final long cycleStartTime) {
        final long dt = System.currentTimeMillis() - cycleStartTime;
        if (dt < REFRESH_RATE) {
            try {
                Thread.sleep(REFRESH_RATE - dt);
            } catch (InterruptedException ex) { }
        }
   }

    public void onNotifyFromView(ViewEvent event) {
        switch (event) {
            case START_GAME -> {
                //this.view.closeMenu();
                initGame();
                this.state = GameState.RUNNING;
                mainControl();
            }
            case CLOSE_GAME -> {
                //this.view.closeGameView();
                this.state = model.isWon() ? GameState.GAMEOVER_WON : GameState.GAMEOVER_LOST;
                mainControl();
            }
            case RESTART -> {
                //this.view.closeEndMenu();
                initGame();
                this.state = GameState.RUNNING;
                mainControl();
            }
        }
    }

    private enum GameState {
        
        LAUNCHER,
        GAMEOVER_WON,
        GAMEOVER_LOST,
        RUNNING

    }

}

