package supson.view.api;

import java.util.List;

import supson.model.entity.api.GameEntity;
import supson.model.entity.impl.Player;

/**
 * The GameView interface represents the view component of the game.
 * It provides methods for initializing and rendering the game.
 */
public interface GameView {
    
    /**
     * Initializes the view.
     */
    void initView();
    
    /**
     * Renders the view.
     */
    void renderView(List<GameEntity> gameEntitiesList, Player players);
}
