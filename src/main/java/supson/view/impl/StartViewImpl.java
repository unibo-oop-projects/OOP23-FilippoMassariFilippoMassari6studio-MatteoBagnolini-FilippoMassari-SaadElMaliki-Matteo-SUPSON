package supson.view.impl;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

import supson.controller.api.GameController;
import supson.view.api.StartView;

public class StartViewImpl implements StartView {
    private static final int WIDTH = 948;
    private static final int HEIGHT = 720;
    private static final String BG_PATH = "sprite/menubackground.jpg";

    private final JFrame gameFrame;
    private final ImagePanel backgroundPanel;
    private final MenuButton startButton;
    private final MenuButton quitButton;

    private final GameController controller;

    public StartViewImpl(JFrame gameFrame, GameController controller){
        this.gameFrame = gameFrame;
        this.backgroundPanel = new ImagePanel(BG_PATH);
        this.controller = controller;
        startButton = new MenuButton("Play");
        startButton.addActionListener(e -> {
            this.controller.startGame();
        });
        quitButton = new MenuButton("Quit");
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
        backgroundPanel.setOpaque(false);
        backgroundPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets.set(30, 30, 30, 30);
        backgroundPanel.add(this.startButton, gbc);
        gbc.gridy++;
        backgroundPanel.add(quitButton, gbc);
        gameFrame.setContentPane(backgroundPanel);
        gameFrame.revalidate(); 
    }


    public static class MenuButton extends JButton {

        private static final int WIDTH = 200;
        private static final int HEIGHT = 100;

        public MenuButton(String text) {
            super(text);
            init();
        }

        private void init() {
            this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
            this.setFont(new Font("Verdana", Font.BOLD, 50));
            this.setForeground(Color.WHITE);
            this.setBorderPainted(false);
            this.setFocusPainted(false);
        }
    }

}


