package supson.view.impl;

import supson.model.world.api.World;
import supson.view.api.GameState;
import supson.view.api.GameStateManager;
import supson.view.api.StartView;

public class StartState implements GameState {
    private StartView view;
    private GameStateManager stateManager;
    private World world;
    private InputManager inputManager;

    public StartState(GameStateManager s, World w, InputManager i){
        stateManager = s;
        world = w;
        inputManager = i;
    } 

    @Override
    public void enter() {
        view = new StartViewImpl(stateManager,world,inputManager);
        view.initView();
    }

    @Override
    public void exit() {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'exit'");
    }

    @Override
    public void render() {
        view.renderView();
    }

}
