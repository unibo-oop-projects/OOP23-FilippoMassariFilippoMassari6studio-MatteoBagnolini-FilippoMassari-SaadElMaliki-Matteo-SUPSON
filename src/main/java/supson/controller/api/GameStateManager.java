package supson.view.api;

public interface GameStateManager {
    GameState getState();
    void setState(GameState state);
}
