package supson.controller.api;

import supson.controller.api.GameStateManager.GameStateType;

public interface GameState {
    void enter();
    void exit();
    void render();
    GameStateType getType();
}
