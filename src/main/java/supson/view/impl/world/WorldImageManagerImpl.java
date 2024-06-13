package supson.view.impl.world;

import java.net.URL;
import java.util.Optional;

import javax.swing.ImageIcon;

import supson.common.GameEntityType;
import supson.model.entity.api.GameEntity;
import supson.model.entity.player.Player;
import supson.view.api.WorldImageManager;

/**
 * Manages image icons for game entities in the world.
 */
public final class WorldImageManagerImpl implements WorldImageManager {

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
        Optional<URL> imgURL = Optional.ofNullable(ClassLoader.getSystemResource(type.getSpritePath()));
        try {
            return Optional.of(new ImageIcon(imgURL.get()));
        } catch (Exception e) {
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
        PlayerPathSelector pps = new PlayerPathSelector();
        Optional<URL> imgURL = Optional.ofNullable(ClassLoader.getSystemResource(pps.selectPath(player)));
        try {
            return Optional.of(new ImageIcon(imgURL.get()));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}
