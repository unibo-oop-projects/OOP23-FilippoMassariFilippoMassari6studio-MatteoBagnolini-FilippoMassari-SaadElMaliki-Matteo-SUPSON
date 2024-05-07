package supson.view.impl;

import java.util.List;

import supson.model.entity.api.GameEntity;
import supson.model.entity.impl.Enemy;
import supson.model.entity.impl.Player;

public class GameViewImpl{

    private final GamePanel gamePanel = new GamePanel();
    private final BlockViewImpl blockView = new BlockViewImpl();
    private final EnemyViewImpl enemyView = new EnemyViewImpl();
    private final PlayerViewImpl playerView = new PlayerViewImpl();

    private void renderLevel(List<GameEntity> blocks){
        for (GameEntity block : blocks) {
            this.blockView.renderGameEntity(block, this.gamePanel);
        }
    }

    private void renderEnemy(List<Enemy> enemies){
        for (Enemy enemy : enemies) {
            this.enemyView.renderGameEntity(enemy, this.gamePanel);
        }
    }

    private void renderPlayer(Player player){
        this.playerView.renderGameEntity(player,this.gamePanel);
    }

    private void renderHud(Player player){
        this.playerView.renderHud(player,this.gamePanel);
    }

    public void renderStartGame(){
        this.gamePanel.startGame();
    }

    public void renderGame(List<GameEntity> blocks, List<Enemy> enemies, Player player){
        this.renderLevel(blocks);
        this.renderEnemy(enemies);
        this.renderPlayer(player);
        this.renderHud(player);
    }

}
