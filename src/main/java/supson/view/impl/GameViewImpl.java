package supson.view.impl;

import java.util.List;

import javax.swing.JFrame;

import supson.model.entity.api.GameEntity;
import supson.model.entity.impl.Player;
import supson.view.api.GameView;
import supson.view.api.HudView;
import supson.view.api.WorldView;

public class GameViewImpl implements GameView {

    private final WorldView worldView;
    private final HudView hudView;
    private final JFrame gameFrame;

    public GameViewImpl() {
        this.worldView = new WorldViewImpl();
        this.hudView = new HudViewImpl();
        this.gameFrame = new JFrame("Game");

    }

    @Override
    public void initView() { 
        //this.gameFrame.getContentPane().add(this.pan);
        this.gameFrame.pack();
        this.gameFrame.setSize(800, 600);
        this.gameFrame.setResizable(false);
        this.gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.gameFrame.setVisible(true);
    }

    @Override
    public void renderView(List<GameEntity> gameEntitiesList, Player player) {
        worldView.renderWorld(gameFrame, gameEntitiesList, player);
        //hudView.renderHud(hudPanel, player);
    }

}
