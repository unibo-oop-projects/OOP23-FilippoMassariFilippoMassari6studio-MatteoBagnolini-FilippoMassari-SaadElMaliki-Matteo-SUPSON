package supson.view.api;

import java.util.List;

import supson.model.entity.api.GameEntity;

/**
 * The WorldView interface represents the view of the game world.
 * It provides methods to render the game entities in the world.
 */
public interface WorldView {
    /**
     * Renders the game entities in the world.
     * 
     * @param gameEntityList a list of game entities to be rendered
     */
    void renderWorld(List<GameEntity> gameEntityList);
}
