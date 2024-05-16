package supson.core.impl;

import supson.core.api.GameEngine;
import supson.controller.api.GameController;
import supson.controller.impl.GameControllerImpl;
import java.util.logging.Logger;

/**
 * This class represents the main engine of the game.
 */
public final class GameEngineImpl implements GameEngine {

    private static final long REFRESH_RATE = 20;

    private final Logger logger = Logger.getLogger("Game Engine");

    private final GameController controller;

    /**
     * GameEngine constructor.
     */
    public GameEngineImpl() {
        this.controller = new GameControllerImpl();
    }

    @Override
    public void initGame() {
        this.controller.initGame();
    }

    @Override
    public void mainLoop() {
        long previousCycleStartTime = System.currentTimeMillis();
        while (true) {
            final long currentCycleStartTime = System.currentTimeMillis();
            final long elapsed = currentCycleStartTime - previousCycleStartTime;
            //processInput(); //TODO: uncomment this line when the method is implemented
            updateGame(elapsed);
            render();
            waitForNextFrame(currentCycleStartTime);
            previousCycleStartTime = currentCycleStartTime;
        }
    }

    @Override
    public void processInput() {
        this.controller.processInput();
    }

    @Override
    public void updateGame(final long elapsed) {
        this.controller.update(elapsed);
        logger.info("FPS: " + elapsed);
    }

    @Override
    public void render() {
        this.controller.render();
    }

    @Override
    public void waitForNextFrame(final long cycleStartTime) {
        final long dt = System.currentTimeMillis() - cycleStartTime;
        if (dt < REFRESH_RATE) {
            try {
                Thread.sleep(REFRESH_RATE - dt);
            } catch (InterruptedException ex) { 
                //TODO: handle the exception
            }
        }

    }

}
