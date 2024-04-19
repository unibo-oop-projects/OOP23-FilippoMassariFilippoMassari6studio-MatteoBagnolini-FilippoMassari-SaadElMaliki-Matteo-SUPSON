package supson.core.impl;

import supson.core.api.GameEngine;
import supson.controller.api.GameController;
import supson.controller.impl.GameControllerImpl;

public class GameEngineImpl implements GameEngine {

    private final static long REFRESH_RATE = 20;

    private final GameController controller;

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
		while(true){
			long currentCycleStartTime = System.currentTimeMillis();
			long elapsed = currentCycleStartTime - previousCycleStartTime;
			processInput();
			updateGame(elapsed);
			render();
			waitForNextFrame(currentCycleStartTime);
			previousCycleStartTime = currentCycleStartTime;
		}
    }

    @Override
    public void processInput() {
        System.out.println("processing input");
        this.controller.processInput();
    }

    @Override
    public void updateGame(long elapsed) {
        System.out.println("Updating game: " + elapsed);
        this.controller.update();
    }

    @Override
    public void render() {
        System.out.println("rendering");
        this.controller.render();
    }

    @Override
    public void waitForNextFrame(long cycleStartTime) {
		long dt = System.currentTimeMillis() - cycleStartTime;
		if (dt < REFRESH_RATE){
			try {
				Thread.sleep(REFRESH_RATE - dt);
			} catch (Exception ex){}
		}

    }
    
}
