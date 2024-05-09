package supson.view.impl;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.util.List;
import java.util.ArrayList;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel {
    
    private final List<BufferedImage> images;

    public GamePanel() {
        setPreferredSize(new Dimension(800, 600));
        this.images = new ArrayList<BufferedImage>();
    }

    public void renderImage(String imagePaths) {
        try {
            BufferedImage image = ImageIO.read(new File(imagePaths));
            images.add(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int y = 0;
        for (BufferedImage image : images) {
            g.drawImage(image, 0, y, getWidth(), image.getHeight(), this);
            y += image.getHeight();
        }
    }

    public void startGame() {
        // Crea una finestra e aggiungi il pannello canvas
        JFrame frame = new JFrame("Game Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new GamePanel());
        frame.pack();
        frame.setVisible(true);
    }

}
