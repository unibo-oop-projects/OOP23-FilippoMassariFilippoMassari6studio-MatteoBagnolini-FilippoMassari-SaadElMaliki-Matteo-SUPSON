package supson.view.impl;

import supson.view.api.GameState;
import supson.view.api.GameStateManager;

public class GameStateManagerImpl implements GameStateManager{
    private GameState currState;

    @Override
    public GameState getState() {
        return currState;
    }

    @Override
    public void setState(GameState state) {
        if(currState!=null){
            currState.exit();
        }
        currState = state;
        currState.enter();
    }

}
