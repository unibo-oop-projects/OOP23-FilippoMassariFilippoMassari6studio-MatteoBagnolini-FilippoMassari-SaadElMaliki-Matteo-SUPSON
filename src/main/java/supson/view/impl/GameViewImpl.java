package supson.view.impl;

import java.util.ArrayList;
import java.util.List;

import supson.model.block.api.BlockEntity;
import supson.model.entity.api.GameEntity;
import supson.model.entity.impl.Enemy;
import supson.model.entity.impl.Player;
import supson.view.api.GameView;

/**
 * The GameViewImpl class is responsible for rendering the game entities and HUD on the game panel.
 */
public class GameViewImpl implements GameView{

    private final GamePanel gamePanel = new GamePanel();
    private final GameEntityViewImpl gameEntityView = new GameEntityViewImpl();

    /**
     * Renders the HUD (Heads-Up Display) for the player on the game panel.
     * 
     * @param player the player entity for which to render the HUD
     */
    private void renderHud(Player player){
        this.gameEntityView.renderHud(player,this.gamePanel);
    }

    @Override
    public void renderStartGame(){
        this.gamePanel.startGame();
    }

    @Override
    public void renderGame(List<BlockEntity> blocks, List<Enemy> enemies, Player player){
        final List<GameEntity> camera = new ArrayList<>();
        camera.addAll(blocks);
        camera.addAll(enemies);
        camera.add(player);
        this.gameEntityView.renderGameEntity(camera,this.gamePanel);
        this.renderHud(player);
    }
}
