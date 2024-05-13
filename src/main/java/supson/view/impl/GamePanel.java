package supson.view.impl;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GamePanel {
    
    private final JPanel pan = new JPanel();
    private final List<URL> camera;

    public GamePanel() {
        camera = new ArrayList<>();
    }

    /**
     * Starts the game by creating a window and adding the game panel to it.
     */
    public void startGame() {
        JFrame frame = new JFrame("Game Panel");
        frame.getContentPane().add(this.pan);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(800, 600);
        frame.setResizable(false);
        frame.setLocationByPlatform(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pan.setLayout(new GridLayout(4, 4));
    }

    /**
     * Renders an image on the panel.
     * 
     * @param imagePathsList the list of URL of the image files to render
     */
    public void renderCamera(List<URL> imagePathsList) {
        camera.clear();
        camera.addAll(imagePathsList);
        paint();
    }

    public void paint() {
        // Rimuovi tutte le label esistenti prima di aggiungerne di nuove
        pan.removeAll();
        
        // Itera attraverso la lista di URL delle immagini
        for (URL imageUrl : camera) {
            // Crea una nuova JLabel per ogni URL
            JLabel label = new JLabel();
            try {
                // Carica l'immagine dall'URL
                ImageIcon icon = new ImageIcon(imageUrl);
                label.setIcon(icon);
            } catch (Exception e) {
                e.printStackTrace();
                // Gestisci eventuali errori durante il caricamento dell'immagine
                label.setText("Error loading image");
            }
            // Aggiungi la JLabel al JPanel
            pan.add(label);
        }
        
        // Ridisegna il JPanel
        pan.revalidate();
        pan.repaint();
    }
}
