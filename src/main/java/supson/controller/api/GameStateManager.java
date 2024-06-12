package supson.controller.api;

public interface GameStateManager {
    GameState getState();
    void setState(GameState state);
}
