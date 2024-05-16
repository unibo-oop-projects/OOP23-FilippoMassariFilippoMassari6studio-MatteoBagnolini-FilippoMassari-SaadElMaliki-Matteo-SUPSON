package supson.view.impl;

import javax.swing.JFrame;
import javax.swing.JLabel;

import supson.view.api.HudView;
import supson.model.hud.api.Hud;

public class HudViewImpl implements HudView {

    @Override
    public void renderHud(JFrame gameFrame, Hud hud) {
        JLabel scoreLabel = new JLabel("Score: " + hud.getScore());
        JLabel livesLabel = new JLabel("Lives: " + hud.getLives());
        scoreLabel.setBounds(10, 10, 100, 20);
        livesLabel.setBounds(10, 40, 100, 20);
        gameFrame.add(scoreLabel);
        gameFrame.add(livesLabel);
        // Make sure the labels are visible
        /*gameFrame.revalidate();
        gameFrame.repaint();*/
    }

}
