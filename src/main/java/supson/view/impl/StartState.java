package supson.view.impl;

import javax.swing.JFrame;

import supson.controller.api.GameController;
import supson.model.world.api.World;
import supson.view.api.GameState;
import supson.view.api.GameStateManager;
import supson.view.api.StartView;

public class StartState implements GameState {
    private StartView view;
    private JFrame gameFrame;
    private GameStateManager stateManager;
    private GameController controller;

    public StartState(GameController controller, JFrame gameFrame){
        this.gameFrame = gameFrame;
        this.controller = controller;
    } 

    @Override
    public void enter() {
        view = new StartViewImpl(gameFrame, controller);
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
