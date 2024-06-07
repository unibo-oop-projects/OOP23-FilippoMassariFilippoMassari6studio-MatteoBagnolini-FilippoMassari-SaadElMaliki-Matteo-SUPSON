package supson.view.impl;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import javax.imageio.ImageIO;

public class ImagePanel extends JPanel {
    private Optional<BufferedImage> backgroundImage;

    public ImagePanel(String imagePath) {
        try {
            Optional<URL> imgURL = Optional.ofNullable(getClass().getClassLoader().getResource(imagePath));
            if (imgURL.isPresent()) {
                backgroundImage = Optional.of(ImageIO.read(imgURL.get()));
            } else {
                backgroundImage = Optional.empty();
            }
        } catch (IOException e) {
            e.printStackTrace();
            backgroundImage = Optional.empty();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        backgroundImage.ifPresent(img -> g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this));
    }
}
