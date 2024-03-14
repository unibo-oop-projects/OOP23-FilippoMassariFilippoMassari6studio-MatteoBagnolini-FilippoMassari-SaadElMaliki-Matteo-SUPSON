package supson.core.api;
/**
 * 
 */
public interface GameEngine {

    void initGame();

    void mainLoop();

    void processInput();

    void updateGame(long elapsed);

    void render();

    void waitForNextFrame(long cycleStartTime);

}
