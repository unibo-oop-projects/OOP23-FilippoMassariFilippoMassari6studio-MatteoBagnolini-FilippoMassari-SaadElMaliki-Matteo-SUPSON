package supson.core.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import supson.core.api.GameEngine;
import supson.model.world.api.World;
import supson.model.world.impl.WorldImpl;
import supson.view.api.GameView;
import supson.view.api.MenuView;
import supson.view.impl.GameViewImpl;
import supson.view.impl.InputManager;
import supson.view.impl.MenuViewImpl;

/**
 * This class represents the main engine of the game.
 */
public final class GameEngineImpl implements GameEngine {

    private static final String WORLD_FILE_PATH = "/level/level_1.txt";

    private static final long REFRESH_RATE = 20;

    private GameState state;

    private final World model;
    private final GameView view;
    private final MenuView menuview;
    private final InputManager input;

    /**
     * GameEngine constructor.
     */
    public GameEngineImpl() {
        this.model = new WorldImpl();
        this.view = new GameViewImpl();
        this.menuview = new MenuViewImpl(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Play")){
            initGame();
            state = GameState.RUNNING;
        }
        if(e.getActionCommand().equals("Quit")){
            System.exit(0);
        }
    }
        });
        this.input = new InputManager();
        this.view.addKeyListener(input);
        this.state = GameState.LAUNCHER;

        this.menuview.initView();
        this.menuview.renderView();
    }

    @Override
    public void initGame() {
        this.model.loadWorld(WORLD_FILE_PATH);
        this.view.initView();
    }

    public void mainLoop() {
        while (true) {
            switch (state) {
                case LAUNCHER -> { menuview.renderView(); }
                case RUNNING -> gameLoop();
                case GAMEOVER_WON -> {/*this.view.renderEndGameMenu()*/}
                case GAMEOVER_LOST -> {/*this.view.renderEndGameMenu()*/}
                default -> { }
            }
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
        this.model.updateGame(elapsed); //TODO: check endgame
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

    private enum GameState {
        
        LAUNCHER,
        GAMEOVER_WON,
        GAMEOVER_LOST,
        RUNNING

    }

}

