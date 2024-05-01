package supson.view.impl;

import java.util.List;

import supson.model.block.api.BlockEntity;
import supson.model.entity.impl.Enemy;
import supson.model.entity.impl.Player;

public class GameViewImpl {

    private final BlockViewImpl BlockView = new BlockViewImpl();
    private final EnemyViewImpl EnemyView = new EnemyViewImpl();
    private final PlayerViewImpl PlayerView = new PlayerViewImpl();

    private void renderLevel(List<BlockEntity> blocks){
        for (BlockEntity block : blocks) {
            this.BlockView.renderGameEntity(block);
        }
    }

    private void renderEnemy(List<Enemy> enemies){
        for (Enemy enemy : enemies) {
            this.EnemyView.renderGameEntity(enemy);;
        }
    }

    private void renderPlayer(Player player){
        this.PlayerView.renderGameEntity(player);;
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
