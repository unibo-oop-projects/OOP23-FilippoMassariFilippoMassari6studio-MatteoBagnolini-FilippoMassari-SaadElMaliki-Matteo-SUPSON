package supson.view.impl;

import javax.swing.*;

import supson.view.api.GameView;
import supson.model.world.impl.WorldImpl;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                GameView gameView = new GameViewImpl();
                WorldImpl world = new WorldImpl();

                gameView.initView();

                world.loadWorld("/world.txt");

                gameView.renderView(world.getGameEntities(), world.getPlayer(), world.getHud());
            }
        });
    }
}
