package supson.view.impl;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import supson.controller.api.GameController;
import supson.view.api.StartView;

public class StartViewImpl implements StartView {
    private static final int WIDTH = 948;
    private static final int HEIGHT = 720;
    private static final String BG_PATH = "sprite/menubackground.jpg";

    private final JFrame gameFrame;
    private final JButton startButton;
    private final JButton scoreButton;
    private final JButton quitButton;

    private final GameController controller;

    public StartViewImpl(JFrame gameFrame, GameController controller){
        this.gameFrame = gameFrame;
        this.controller = controller;
        startButton = new JButton("Start");
        startButton.addActionListener(e -> {
            this.controller.startGame();
        });
        scoreButton = new JButton("Scores");
        scoreButton.addActionListener(e -> {
            this.controller.showScores();
        });
        quitButton = new JButton("Quit");
        quitButton.addActionListener(e->{
            System.exit(0);
        });

    }
    
    @Override
    public void initView() {
        this.gameFrame.pack();
        this.gameFrame.setSize(WIDTH, HEIGHT);
        this.gameFrame.setResizable(false);
        this.gameFrame.setVisible(true);
    }

    @Override
    public void renderView() {
        gameFrame.getContentPane().removeAll();
        gameFrame.repaint();
        JPanel panel = new JPanel();
        panel.setOpaque(true);
        panel.setBackground(Color.BLUE);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets.set(10, 10, 10, 10);
        panel.add(this.startButton, gbc);
        gbc.gridy++;
        panel.add(scoreButton, gbc);
        gbc.gridy++;
        panel.add(quitButton, gbc);
        gameFrame.setContentPane(panel);
        gameFrame.revalidate(); 
    }

}
