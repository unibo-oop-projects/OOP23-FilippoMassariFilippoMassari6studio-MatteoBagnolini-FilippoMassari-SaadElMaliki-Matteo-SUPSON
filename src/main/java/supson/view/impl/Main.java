package supson.view.impl;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import supson.view.api.GameView;
import supson.model.entity.api.GameEntity;
import supson.model.entity.impl.Enemy;
import supson.model.entity.impl.Player;
import supson.model.world.impl.WorldImpl;
import supson.model.block.api.BlockEntity;
import supson.model.block.impl.TerrainImpl;
import supson.common.impl.Pos2dImpl;
import supson.common.impl.Vect2dImpl;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                GameView gameView = new GameViewImpl();
                WorldImpl world = new WorldImpl();

                gameView.initView();
                List<GameEntity> gameEntities = new ArrayList<>();

                world.loadWorld("src/main/resources/world.txt");

                gameEntities.addAll(world.getBlocks());
                gameEntities.addAll(world.getEnemies());
                gameEntities.add(world.getPlayer());
                Player player = world.getPlayer();

                //System.out.println("gameEntities list : "+gameEntities);
                gameView.renderView(gameEntities, player);

                /*for (int i = 0; i < 1000; i++) {
                    BlockEntity block = new TerrainImpl(new Pos2dImpl(10*i,500));
                    gameEntities.add(block);
                }
                Player player = new Player(new Pos2dImpl(0,0),new Vect2dImpl(0,0),3);
                gameView.renderView(gameEntities, player);
                System.out.println("gameEntities list : "+gameEntities);*/
            }
        });
    }
}
