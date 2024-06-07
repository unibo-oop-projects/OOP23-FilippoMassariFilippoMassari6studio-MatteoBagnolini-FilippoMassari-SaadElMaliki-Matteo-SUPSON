package supson.view.api;

import java.util.Optional;

import javax.swing.ImageIcon;

import supson.model.entity.api.GameEntity;
import supson.model.entity.player.Player;

/**
 * Manages image icons for game entities in the world.
 */
public interface WorldImageManager {

    /**
     * Gets the image icon for a game entity.
     *
     * @param gameEntity the game entity.
     * @param player the player.
     * @return an optional containing the image icon if present, otherwise an empty optional.
     */
    Optional<ImageIcon> getImageIcon(GameEntity gameEntity, Player player);

}
