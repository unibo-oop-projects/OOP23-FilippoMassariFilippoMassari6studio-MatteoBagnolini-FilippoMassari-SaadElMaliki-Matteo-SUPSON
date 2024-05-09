package supson.view.api;

import java.util.List;

import supson.model.block.api.BlockEntity;
import supson.model.entity.impl.Enemy;
import supson.model.entity.impl.Player;

public interface GameView {

    /**
     * Renders the game panel and starts the game.
     */
    void renderStartGame();

    /**
     * Renders the game by rendering the level, enemies, player, and HUD on the game panel.
     * 
     * @param blocks the list of block entities to render
     * @param enemies the list of enemy entities to render
     * @param player the player entity to render
     */
    void renderGame(List<BlockEntity> blocks, List<Enemy> enemies, Player player);

}