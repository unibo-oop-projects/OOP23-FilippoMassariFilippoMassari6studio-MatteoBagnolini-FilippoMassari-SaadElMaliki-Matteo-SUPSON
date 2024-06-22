package supson.view.impl;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import javax.imageio.ImageIO;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents a custom JPanel with an optional background image.
 */
public final class ImagePanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(ImagePanel.class.getName());

    private transient Optional<BufferedImage> backgroundImage;

    /**
     * Constructs a new `ImagePanel` with the specified background image path.
     *
     * @param imagePath The path to the background image file.
     */
    public ImagePanel(final String imagePath) {
        try {
            final Optional<URL> imgURL = Optional.ofNullable(getClass().getClassLoader().getResource(imagePath));
            if (imgURL.isPresent()) {
                backgroundImage = Optional.of(ImageIO.read(imgURL.get()));
            } else {
                backgroundImage = Optional.empty();
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error loading the background image file from: " + imagePath, e);
            backgroundImage = Optional.empty();
        }
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        backgroundImage.ifPresent(img -> g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this));
    }
}
