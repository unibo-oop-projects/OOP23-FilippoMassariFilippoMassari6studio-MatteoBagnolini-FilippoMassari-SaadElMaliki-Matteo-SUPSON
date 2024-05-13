package supson.view.impl;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import supson.model.entity.api.GameEntity;
import supson.model.entity.impl.Player;
import supson.view.api.GameView;
import supson.view.api.HudView;
import supson.view.api.WorldView;

public class GameViewImpl implements GameView {

    private final WorldView worldView;
    private final HudView hudView;
    private final JPanel gamePanel;
    private final JPanel hudPanel;

    public GameViewImpl() {
        this.worldView = new WorldViewImpl();
        this.hudView = new HudViewImpl();
        this.gamePanel = new JPanel();
        this.hudPanel = new JPanel();
    }

    @Override
    public void initView() { 
        JFrame frame = new JFrame("Game Panel");
        //frame.getContentPane().add(this.pan);
        frame.pack();
        frame.setSize(800, 600);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this.gamePanel);
        frame.add(this.hudPanel);
        frame.setVisible(true);
    }

    @Override
    public void renderView(List<GameEntity> gameEntitiesList, Player player) {
        worldView.renderWorld(gamePanel, gameEntitiesList, player);
        //hudView.renderHud(hudPanel, player);
    }

}
