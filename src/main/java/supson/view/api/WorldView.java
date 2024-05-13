package supson.view.api;

import java.util.List;

import javax.swing.JPanel;

import supson.model.entity.api.GameEntity;
import supson.model.entity.impl.Player;

/**
 * The WorldView interface represents the view of the game world.
 * It provides methods to render the game entities in the world.
 */
public interface WorldView {
    /**
     * Renders the game entities in the world.
     * @param gamePanel 
     * @param player 
     * @param gameEntitiesList 
    */
    void renderWorld(JPanel gamePanel, List<GameEntity> gameEntitiesList, Player player);
}
