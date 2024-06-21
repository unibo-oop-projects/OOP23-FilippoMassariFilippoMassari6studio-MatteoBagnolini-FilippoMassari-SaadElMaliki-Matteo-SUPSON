package supson.view.impl;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import supson.controller.api.GameController;
import supson.view.api.ScoreView;

public class ScoreViewImpl implements ScoreView{
    private static final int WIDTH = 948;
    private static final int HEIGHT = 720;

    private final JFrame gameFrame;
    //private final JTextField Score;
    //private final JButton StartMenuButton;
    //private final JButton quitButton;
    private final GameController controller;

    public ScoreViewImpl(JFrame gameFrame, GameController controller, String score) {
        this.gameFrame = gameFrame;
        this.controller = controller;
    }

    @Override
    public void initView() {
        
    }

    @Override
    public void renderView() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'renderView'");
    }

}
