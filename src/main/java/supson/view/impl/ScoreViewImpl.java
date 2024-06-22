package supson.view.impl;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import supson.controller.api.GameController;
import supson.model.hud.api.Hud;
import supson.view.api.ScoreView;

public class ScoreViewImpl implements ScoreView{
    private static final int WIDTH = 948;
    private static final int HEIGHT = 720;

    private final JFrame gameFrame;
    private final JLabel Score;
    private final JLabel Time;
    private final JButton StartMenuButton;
    private final JButton quitButton;
    private final GameController controller;
    private final JPanel mainPanel;

    public ScoreViewImpl(JFrame gameFrame, GameController controller, Hud hud) {
        this.gameFrame = gameFrame;
        this.controller = controller;
        this.mainPanel = new JPanel();
        this.Score = new JLabel("Score: " + hud.getScore());
        this.Time = new JLabel("Time: " + hud.getTime());
        this.StartMenuButton = new JButton("Menu");
        this.StartMenuButton.addActionListener(e -> {
            controller.initGame();
        });
        this.quitButton = new JButton("Quit");
        this.quitButton.addActionListener(e -> {
            System.exit(0);
        });
    }

    @Override
    public void initView() {
        this.gameFrame.pack();
        this.gameFrame.setSize(WIDTH, HEIGHT);
        this.gameFrame.setResizable(false);
        this.gameFrame.setVisible(true);


        mainPanel.setOpaque(false);
        mainPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets.set(10,10,10,10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(Score, gbc);

        gbc.gridy = 1;
        mainPanel.add(Time, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 2;
        mainPanel.add(StartMenuButton, gbc);

        gbc.gridx = 1;
        mainPanel.add(quitButton, gbc);

        gameFrame.add(mainPanel, new GridBagConstraints());

        gameFrame.setContentPane(mainPanel);
        gameFrame.revalidate();

    }

    @Override
    public void renderView() {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'renderView'");
    }

}
