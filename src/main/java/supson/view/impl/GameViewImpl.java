package supson.view.impl;

import java.util.List;

import supson.model.block.api.BlockEntity;
import supson.model.entity.impl.Enemy;
import supson.model.entity.impl.Player;
import supson.view.api.BlockView;
import supson.view.api.EnemyView;
import supson.view.api.PlayerView;

public class GameViewImpl {

    private final BlockView BlockView = new BlockViewImpl();
    private final EnemyView EnemyView = new EnemyViewImpl();
    private final PlayerView PlayerView = new PlayerViewImpl();

    private void renderLevel(List<BlockEntity> blocks){
        for (BlockEntity block : blocks) {
            this.BlockView.renderBlock(block);
        }
    }

    private void renderEnemy(List<Enemy> enemies){
        for (Enemy enemy : enemies) {
            this.EnemyView.renderEnemy(enemy);
        }
    }

    private void renderPlayer(Player player){
        this.PlayerView.renderPlayer(player);
    }

    private void renderHud(Player player){
        this.PlayerView.renderHud(player);
    }

    public void renderGame(List<BlockEntity> blocks, List<Enemy> enemies, Player player){
        this.renderLevel(blocks);
        this.renderEnemy(enemies);
        this.renderPlayer(player);
        this.renderHud(player);
    }
}
