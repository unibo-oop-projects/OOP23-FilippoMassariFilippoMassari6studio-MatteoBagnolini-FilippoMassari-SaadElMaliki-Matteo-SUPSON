package supson.controller.impl;

import javax.swing.JFrame;

import supson.controller.api.GameController;
import supson.controller.api.GameState;
import supson.view.api.StartView;
import supson.view.impl.StartViewImpl;

public class StartState implements GameState {
    private StartView view;
    private JFrame gameFrame;
    private GameController controller;

    public StartState(GameController controller, JFrame gameFrame){
        this.gameFrame = gameFrame;
        this.controller = controller;
        view = new StartViewImpl(gameFrame, controller);
    } 

    @Override
    public void enter() {
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
