package supson.controller.impl;

import javax.swing.JFrame;

import supson.controller.api.GameController;
import supson.controller.api.GameState;
import supson.controller.api.GameStateManager.GameStateType;
import supson.model.hud.api.Hud;
import supson.view.api.ScoreView;
import supson.view.impl.ScoreViewImpl;

public class ScoreState implements GameState{
    private ScoreView view;
    private JFrame gameFrame;
    private GameController controller;

    public ScoreState(JFrame gameFrame, GameController controller, Hud hud) {
        this.gameFrame = gameFrame;
        this.controller = controller;
        view = new ScoreViewImpl(gameFrame, controller,hud);
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

    @Override
    public GameStateType getType() {
        return GameStateType.SCORE;
    }

}
