package supson.view.impl;

import javax.swing.JFrame;
import javax.swing.JLabel;

import supson.view.api.HudView;
import supson.model.hud.api.Hud;

/**
 * Implementation of the HudView interface that renders the HUD (Heads-Up Display) for the game.
 */
public final class HudViewImpl implements HudView {

    private static final int SCORE_LABEL_X = 10;
    private static final int SCORE_LABEL_Y = 10;

    private static final int LIVES_LABEL_X = 10;
    private static final int LIVES_LABEL_Y = 40;

    private static final int LABEL_WIDTH = 100;
    private static final int LABEL_HEIGHT = 20;

    @Override
    public void renderHud(final JFrame gameFrame, final Hud hud) {
        JLabel scoreLabel = new JLabel("Score: " + hud.getScore());
        JLabel livesLabel = new JLabel("Lives: " + hud.getLives());
        double time = hud.getTime();
        String formattedTime = String.format("%.2f", time);
        JLabel timeLabel = new JLabel("Time: " + formattedTime);
        scoreLabel.setBounds(SCORE_LABEL_X, SCORE_LABEL_Y, LABEL_WIDTH, LABEL_HEIGHT);
        livesLabel.setBounds(LIVES_LABEL_X, LIVES_LABEL_Y, LABEL_WIDTH, LABEL_HEIGHT);
        timeLabel.setBounds(LIVES_LABEL_X, LIVES_LABEL_Y + 30, LABEL_WIDTH, LABEL_HEIGHT);
        gameFrame.add(scoreLabel);
        gameFrame.add(livesLabel);
        gameFrame.add(timeLabel);
        gameFrame.revalidate();
        gameFrame.repaint();
    }

}
