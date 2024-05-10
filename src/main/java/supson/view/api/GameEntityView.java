package supson.view.api;

import java.util.List;

import supson.model.entity.api.GameEntity;
import supson.view.impl.GamePanel;

/**
 * The GameEntityView interface represents the view for a game entity in the game panel.
 * It provides a method to render the game entity on the game panel.
 */
public interface GameEntityView {
    
    /**
     * Renders the specified game entity on the game panel.
     * 
     * @param gameEntity the game entity to render
     * @param gamePanel the game panel on which to render the game entity
     */
    void renderGameEntity(List<GameEntity> gameEntities, GamePanel gamePanel);
}
