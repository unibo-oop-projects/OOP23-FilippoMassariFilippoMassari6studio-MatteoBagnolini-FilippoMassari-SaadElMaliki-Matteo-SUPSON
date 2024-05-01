package supson.view.impl;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel {

    private BufferedImage backgroundImage;

    public GamePanel() {
        // Imposta le dimensioni del pannello canvas
        setPreferredSize(new Dimension(800, 600));

        // Carica l'immagine di sfondo da file locale
        try {
            File imageFile = new File("C:\\Users\\fmass\\OneDrive\\Immagini\\Rullino\\mike-yukhtenko-a2kD4b0KK4s-unsplash.jpg");
            backgroundImage = ImageIO.read(imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Disegna l'immagine di sfondo sul canvas
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
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

