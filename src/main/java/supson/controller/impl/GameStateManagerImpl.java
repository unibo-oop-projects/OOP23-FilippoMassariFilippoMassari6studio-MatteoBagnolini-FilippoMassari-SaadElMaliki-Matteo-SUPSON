package supson.controller.impl;

import supson.controller.api.GameState;
import supson.controller.api.GameStateManager;

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
