package supson.view.impl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import supson.view.api.MenuView;

public class MenuViewImpl implements MenuView{

    private static final int WIDTH = 948;
    private static final int HEIGHT = 720;
    private static final String BG_PATH = "sprite/menubackground.jpg";

    private final JFrame frame;
    private final ImagePanel backgroundPanel;
    private final MenuButton startButton;
    private final MenuButton quitButton;

    public MenuViewImpl(JFrame frame){
        this.frame = frame;
        this.backgroundPanel = new ImagePanel(BG_PATH);
        startButton = new MenuButton("Play");
        startButton.setActionCommand("Play");
        quitButton = new MenuButton("Quit");
        quitButton.setActionCommand("Quit");
    }

    @Override
    public void initView() {
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
        frame.setContentPane(backgroundPanel);
        frame.revalidate();
    }

    @Override
    public void renderView() {
        frame.setVisible(true);
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
