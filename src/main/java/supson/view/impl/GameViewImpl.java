package supson.view.impl;

import java.util.List;

import supson.model.block.api.BlockEntity;
import supson.model.entity.impl.Enemy;
import supson.model.entity.impl.Player;

public class GameViewImpl{

    private final GamePanel gamePanel = new GamePanel();
    private final GameEntityViewImpl gameEntityView = new GameEntityViewImpl();

    private void renderLevel(List<BlockEntity> blocks){
        for (BlockEntity block : blocks) {
            this.gameEntityView.renderGameEntity(block, this.gamePanel);
        }
    }

    private void renderEnemy(List<Enemy> enemies){
        for (Enemy enemy : enemies) {
            this.gameEntityView.renderGameEntity(enemy, this.gamePanel);
        }
    }

    private void renderPlayer(Player player){
        this.gameEntityView.renderGameEntity(player,this.gamePanel);
    }

    private void renderHud(Player player){
        this.gameEntityView.renderHud(player,this.gamePanel);
    }

    public void renderStartGame(){
        this.gamePanel.startGame();
    }

    public void renderGame(List<BlockEntity> blocks, List<Enemy> enemies, Player player){
        this.renderLevel(blocks);
        this.renderEnemy(enemies);
        this.renderPlayer(player);
        this.renderHud(player);
    }

}
