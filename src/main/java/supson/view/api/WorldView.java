package supson.view.api;

import java.util.List;

import javax.swing.JFrame;

import supson.model.entity.api.GameEntity;
import supson.model.entity.player.Player;

/**
 * The WorldView interface represents the view of the game world.
 * It provides methods to render the game entities in the world.
 */
public interface WorldView {
    /**
    * Renders the game entities in the world.
    * @param gameFrame the game frame to render the world on 
    * @param gameEntitiesList the list of game entities to render in the world
    * @param player the player entity to render the world around
    */
    void renderWorld(JFrame gameFrame, List<GameEntity> gameEntitiesList, Player player);
}
