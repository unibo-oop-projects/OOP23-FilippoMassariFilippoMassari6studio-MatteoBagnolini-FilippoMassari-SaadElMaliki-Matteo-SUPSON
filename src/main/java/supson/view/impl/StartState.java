package supson.view.impl;

import supson.controller.api.GameController;
import supson.model.world.api.World;
import supson.view.api.GameState;
import supson.view.api.GameStateManager;
import supson.view.api.StartView;

public class StartState implements GameState {
    private StartView view;
    private GameStateManager stateManager;
    private GameController controller;

    public StartState(GameController controller){
        this.controller = controller;
    } 

    @Override
    public void enter() {
        view = new StartViewImpl(controller);
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
