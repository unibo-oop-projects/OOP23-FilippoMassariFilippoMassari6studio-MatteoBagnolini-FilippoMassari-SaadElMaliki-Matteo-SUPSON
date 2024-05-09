package supson.view.impl;

import java.util.List;

import supson.model.block.api.BlockEntity;
import supson.model.entity.impl.Enemy;
import supson.model.entity.impl.Player;

/**
 * The GameViewImpl class is responsible for rendering the game entities and HUD on the game panel.
 */
public class GameViewImpl{

    private final GamePanel gamePanel = new GamePanel();
    private final GameEntityViewImpl gameEntityView = new GameEntityViewImpl();

    /**
     * Renders the level by rendering each block entity on the game panel.
     * 
     * @param blocks the list of block entities to render
     */
    private void renderLevel(List<BlockEntity> blocks){
        for (BlockEntity block : blocks) {
            this.gameEntityView.renderGameEntity(block, this.gamePanel);
        }
    }

    /**
     * Renders the enemies by rendering each enemy entity on the game panel.
     * 
     * @param enemies the list of enemy entities to render
     */
    private void renderEnemy(List<Enemy> enemies){
        for (Enemy enemy : enemies) {
            this.gameEntityView.renderGameEntity(enemy, this.gamePanel);
        }
    }

    /**
     * Renders the player entity on the game panel.
     * 
     * @param player the player entity to render
     */
    private void renderPlayer(Player player){
        this.gameEntityView.renderGameEntity(player,this.gamePanel);
    }

    /**
     * Renders the HUD (Heads-Up Display) for the player on the game panel.
     * 
     * @param player the player entity for which to render the HUD
     */
    private void renderHud(Player player){
        this.gameEntityView.renderHud(player,this.gamePanel);
    }

    /**
     * Renders the game panel and starts the game.
     */
    public void renderStartGame(){
        this.gamePanel.startGame();
    }

    /**
     * Renders the game by rendering the level, enemies, player, and HUD on the game panel.
     * 
     * @param blocks the list of block entities to render
     * @param enemies the list of enemy entities to render
     * @param player the player entity to render
     */
    public void renderGame(List<BlockEntity> blocks, List<Enemy> enemies, Player player){
        this.renderLevel(blocks);
        this.renderEnemy(enemies);
        this.renderPlayer(player);
        this.renderHud(player);
    }

}
