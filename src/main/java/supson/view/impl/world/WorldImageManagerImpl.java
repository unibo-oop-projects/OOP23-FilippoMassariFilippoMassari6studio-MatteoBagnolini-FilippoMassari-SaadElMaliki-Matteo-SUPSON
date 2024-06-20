package supson.view.impl.world;

import java.net.URL;
import java.util.Optional;

import javax.swing.ImageIcon;

import supson.common.GameEntityType;
import supson.model.entity.api.GameEntity;
import supson.model.entity.impl.moveable.player.Player;
import supson.view.api.WorldImageManager;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Manages image icons for game entities in the world.
 */
public final class WorldImageManagerImpl implements WorldImageManager {

    private static final Logger LOGGER = Logger.getLogger(WorldImageManager.class.getName());

    @Override
    public Optional<ImageIcon> getImageIcon(final GameEntity gameEntity, final Player player) {
        if (gameEntity.getGameEntityType().equals(GameEntityType.PLAYER)) {
            return getPlayerImage(player);
        } else {
            return getEntityImage(gameEntity);
        }
    }

    /**
     * Gets the image icon for a game entity.
     *
     * @param gameEntity the game entity.
     * @return an optional containing the image icon if present, otherwise an empty optional.
     */
    private Optional<ImageIcon> getEntityImage(final GameEntity gameEntity) {
        final GameEntityType type = gameEntity.getGameEntityType();
        final Optional<URL> imgURL = Optional.ofNullable(ClassLoader.getSystemResource(type.getSpritePath()));
        if (imgURL.isPresent()) {
            try {
                return Optional.of(new ImageIcon(imgURL.get()));
            } catch (NullPointerException | IllegalArgumentException e) {
                LOGGER.log(Level.SEVERE, "Error loading image icon form: " + gameEntity, e);
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    /**
     * Gets the image icon for the player.
     *
     * @param player the player.
     * @return an optional containing the image icon if present, otherwise an empty optional.
     */
    private Optional<ImageIcon> getPlayerImage(final Player player) {
        final PlayerPathSelector pps = new PlayerPathSelector();
        final Optional<URL> imgURL = Optional.ofNullable(ClassLoader.getSystemResource(pps.selectPath(player)));
        if (imgURL.isPresent()) {
            try {
                return Optional.of(new ImageIcon(imgURL.get()));
            } catch (NullPointerException | IllegalArgumentException e) {
                LOGGER.log(Level.SEVERE, "Error loading image icon form: " + player, e);
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

}
