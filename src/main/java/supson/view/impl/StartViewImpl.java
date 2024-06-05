package supson.view.impl;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import supson.controller.api.GameController;
import supson.model.world.api.World;
import supson.view.api.GameStateManager;
import supson.view.api.StartView;

public class StartViewImpl implements StartView {
    private static final int WIDTH = 948;
    private static final int HEIGHT = 720;

    private final JFrame gameFrame;
    private final JButton startButton;
    private final JButton scoreButton;
    private final JButton quitButton;

    private final GameController controller;

/*     private final GameStateManager stateManager;
    private final World world;
    private final InputManager inputManager; */


    public StartViewImpl(GameController controller){
        this.controller = controller;
        this.gameFrame = new JFrame("SUPER-SONIC");
        startButton = new JButton("Start");
        startButton.addActionListener(e -> {
            this.gameFrame.setVisible(false);
            this.gameFrame.dispose();
            controller.startGame();
        });
        scoreButton = new JButton("Scores");
        scoreButton.addActionListener(e -> {});
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
