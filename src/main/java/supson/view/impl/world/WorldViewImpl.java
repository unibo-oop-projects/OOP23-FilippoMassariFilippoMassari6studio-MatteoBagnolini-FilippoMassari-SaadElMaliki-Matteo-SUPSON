package supson.view.impl.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import supson.model.entity.api.GameEntity;
import supson.model.entity.impl.moveable.player.Player;
import supson.view.api.WorldView;
import supson.view.api.WorldImageManager;
import supson.view.api.WorldPositionManager;

/**
 * Implementation of the {@link WorldView} interface.
 * This class manages the rendering of the game world onto a game panel.
 */
public final class WorldViewImpl implements WorldView {

    private static final int PIXELS_CORRECTOR = 3;

    private final WorldImageManager imageManager = new WorldImageManagerImpl();
    private final WorldPositionManager positionManager = new WorldPositionManagerImpl();
    private final List<GameEntity> cameraGameEntitiesList = new ArrayList<>();

    @Override
    public void renderWorld(final JPanel gamePanel, final List<GameEntity> gameEntitiesList, final Player player) {
        cameraGameEntitiesList.clear();
        final int mapWidth = getMapWidth(gameEntitiesList);
        selectGameEntity(gameEntitiesList, player, mapWidth);
        addToPanel(gamePanel, player, mapWidth);
    }

    /**
     * Calculates the width of the map based on the positions of the game entities.
     *
     * @param gameEntitiesList the list of game entities.
     * @return the width of the map.
     */
    private int getMapWidth(final List<GameEntity> gameEntitiesList) { 
        //TODO:  viene calcolato nella mappa ma non viene passato decidere se tenerlo li e passarlo e tenerlo qui
        int mapWidth = 0;
        for (final GameEntity gameEntity : gameEntitiesList) {
            if (gameEntity.getPosition().x() > mapWidth) {
                mapWidth = (int) gameEntity.getPosition().x();
            }
        }
        return mapWidth;
    }

    /**
     * Selects the game entities that should be displayed in the camera view.
     *
     * @param gameEntitiesList the list of game entities.
     * @param player the player.
     * @param mapWidth the width of the map.
     */
    private void selectGameEntity(final List<GameEntity> gameEntitiesList, final Player player, final int mapWidth) {
        cameraGameEntitiesList.clear();
        final int playerX = (int) player.getPosition().x();
        final int leftBoundary = positionManager.calculateLeftBoundary(playerX, mapWidth);
        final int rightBoundary = positionManager.calculateRightBoundary(playerX, mapWidth);

        gameEntitiesList.stream()
            .filter(gameEntity -> positionManager.isWithinCameraRange(gameEntity, leftBoundary, rightBoundary))
            .forEach(cameraGameEntitiesList::add);
    }

    /**
     * Adds the selected game entities to the game panel.
     *
     * @param gamePanel the game panel.
     * @param player the player.
     * @param mapWidth the width of the map.
     */
    private void addToPanel(final JPanel gamePanel, final Player player, final int mapWidth) {
        final int centerX = gamePanel.getWidth() / 2 + PIXELS_CORRECTOR;
        final int centerY = gamePanel.getHeight() / 2;
        final double playerX = player.getPosition().x();
        final double playerY = player.getPosition().y();

        for (final GameEntity gameEntity : cameraGameEntitiesList) {
            final Optional<ImageIcon> icon = imageManager.getImageIcon(gameEntity, player);
            icon.ifPresent(imageIcon -> {
                final JLabel label = createLabel(imageIcon);
                positionManager.setPosition(label, gameEntity, playerX, playerY, centerX, centerY, mapWidth);
                gamePanel.add(label);
            });
        }
    }

    /**
     * Creates a label for the image icon.
     *
     * @param icon the image icon.
     * @return the created label.
     */
    private JLabel createLabel(final ImageIcon icon) {
        final JLabel label = new JLabel(icon);
        label.setOpaque(false);
        return label;
    }
}
