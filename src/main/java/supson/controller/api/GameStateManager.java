package supson.controller.api;

public interface GameStateManager {
    GameState getState();
    void setState(GameState state);

    public static enum GameStateType {
        MENU,
        GAME,
        SCORE;
    }
}
