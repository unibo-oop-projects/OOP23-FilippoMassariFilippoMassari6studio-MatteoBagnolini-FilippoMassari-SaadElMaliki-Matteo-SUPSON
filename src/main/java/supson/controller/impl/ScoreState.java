package supson.controller.impl;

import javax.swing.JFrame;

import supson.controller.api.GameController;
import supson.controller.api.GameState;
import supson.view.api.ScoreView;
import supson.view.impl.ScoreViewImpl;

public class ScoreState implements GameState{
    private ScoreView view;
    private JFrame gameFrame;
    private GameController controller;

    public ScoreState(String score, JFrame gameFrame, GameController controller) {
        this.gameFrame = gameFrame;
        this.controller = controller;
        view = new ScoreViewImpl(gameFrame, controller,score);
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
