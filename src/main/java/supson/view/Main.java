package supson.view;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import supson.view.api.GameView;
import supson.view.impl.GameViewImpl;
import supson.model.entity.impl.Enemy;
import supson.model.entity.impl.Player;
import supson.model.block.api.BlockEntity;
import supson.model.block.impl.TerrainImpl;
import supson.common.impl.Pos2dImpl;
import supson.common.impl.Vect2dImpl;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                GameView gameView = new GameViewImpl();
                gameView.renderStartGame();
                List<BlockEntity> blocks = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    BlockEntity block = new TerrainImpl(new Pos2dImpl(0,0));
                    blocks.add(block);
                }
                List<Enemy> enemies = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    Enemy enemy = new Enemy(new Pos2dImpl(0,0),new Vect2dImpl(0,0),3,5);
                    enemies.add(enemy);
                }
                Player player = new Player(new Pos2dImpl(0,0),new Vect2dImpl(0,0),3);
                gameView.renderGame(blocks, enemies, player);
            }
        });
    }
}

