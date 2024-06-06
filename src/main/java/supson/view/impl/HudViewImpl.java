package supson.view.impl;

import javax.swing.*;
import supson.model.hud.api.Hud;
import supson.view.api.HudView;

public final class HudViewImpl implements HudView {

    private static final int LABEL_WIDTH = 100;
    private static final int LABEL_HEIGHT = 20;
    private static final int LABEL_X = 10;
    private static final int SCORE_LABEL_Y = 10;
    private static final int LIVES_LABEL_Y = 40;
    private static final int TIME_LABEL_Y = 70;

    @Override
    public void renderHud(final JPanel gamePanel, final Hud hud) {
        JLabel scoreLabel = new JLabel("Score: " + hud.getScore());
        JLabel livesLabel = new JLabel("Lives: " + hud.getLives());
        double time = hud.getTime();
        String formattedTime = String.format("%.2f", time);
        JLabel timeLabel = new JLabel("Time: " + formattedTime);
        scoreLabel.setBounds(LABEL_X, SCORE_LABEL_Y, LABEL_WIDTH, LABEL_HEIGHT);
        livesLabel.setBounds(LABEL_X, LIVES_LABEL_Y, LABEL_WIDTH, LABEL_HEIGHT);
        timeLabel.setBounds(LABEL_X, TIME_LABEL_Y, LABEL_WIDTH, LABEL_HEIGHT);
        gamePanel.add(scoreLabel);
        gamePanel.add(livesLabel);
        gamePanel.add(timeLabel);
    }
}
