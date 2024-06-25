package supson.view.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import supson.view.api.MenuView;

public class MenuViewImpl implements MenuView {

    MenuButton startButton;
    MenuButton quitButton;

    public MenuViewImpl(){
        this.startButton = new MenuButton("Play");
        this.startButton.setActionCommand("Play");
        this.quitButton = new MenuButton("Quit");
        this.quitButton.setActionCommand("Quit");
    }


    @Override
    public void render(JPanel mainPanel, ActionListener listener){
        startButton.addActionListener(listener);
        quitButton.addActionListener(listener);
        mainPanel.add(startButton,BorderLayout.CENTER);
        mainPanel.add(quitButton,BorderLayout.SOUTH);
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
