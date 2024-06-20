package supson.view.impl;


import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import supson.model.hud.api.Hud;
import supson.view.api.HudView;

/**
 * Represents the main hud view that displays the HUD.
 */
public final class HudViewImpl implements HudView {

    private static final int LABEL_WIDTH = 150;
    private static final int LABEL_HEIGHT = 30;
    private static final int LABEL_X = 10;
    private static final int SCORE_LABEL_Y = 10;
    private static final int LIVES_LABEL_Y = 50;
    private static final int TIME_LABEL_Y = 90;
    private static final int BG_COLOR = 150;

    @Override
    public void renderHud(final JPanel gamePanel, final Hud hud) {
        JLabel scoreLabel = createLabel("Score: " + hud.getScore(), LABEL_X, SCORE_LABEL_Y);
        JLabel livesLabel = createLabel("Lives: " + hud.getLives(), LABEL_X, LIVES_LABEL_Y);
        JLabel timeLabel = createLabel("Time: " + String.format("%.2f", hud.getTime()), LABEL_X, TIME_LABEL_Y);

        gamePanel.add(scoreLabel);
        gamePanel.add(livesLabel);
        gamePanel.add(timeLabel);
    }

    /**
     * Creates a JLabel with the specified text and position.
     *
     * @param text the text to be displayed by the label
     * @param x the x-coordinate of the label's top-left corner
     * @param y the y-coordinate of the label's top-left corner
     * @return the created JLabel
     */
    private JLabel createLabel(final String text, final int x, final int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, LABEL_WIDTH, LABEL_HEIGHT);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(Color.WHITE);
        label.setBackground(new Color(0, 0, 0, BG_COLOR));
        label.setOpaque(true);
        label.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        return label;
    }
}
