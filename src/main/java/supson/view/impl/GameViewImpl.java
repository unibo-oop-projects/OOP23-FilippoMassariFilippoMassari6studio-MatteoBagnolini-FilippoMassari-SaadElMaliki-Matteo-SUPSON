package supson.view.impl;

import java.util.List;

import javax.swing.JFrame;
import java.awt.Color;

import supson.model.entity.api.GameEntity;
import supson.model.entity.impl.player.Player;
import supson.model.hud.api.Hud;
import supson.view.api.GameView;
import supson.view.api.HudView;
import supson.view.api.WorldView;
import javax.swing.JLabel;

/**
 * Implementation of the {@link GameView} interface.
 * This class is responsible for initializing and rendering the game view.
 */
public final class GameViewImpl implements GameView {

    //private static final String BACKGROUND_STRING = "sprite/background.jpg";
    private static final int WHIDTH = 960;
    private static final int HEIGHT = 720;

    private final WorldView worldView;
    private final HudView hudView;
    private final JFrame gameFrame;

    /**
     * Constructs a new instance of {@code GameViewImpl}.
     * Initializes the world view, HUD view, and game frame.
     */
    public GameViewImpl() {
        this.worldView = new WorldViewImpl();
        this.hudView = new HudViewImpl();
        this.gameFrame = new JFrame("SUPREME SONIC");
    }

    @Override
    public void initView() { 
        //this.gameFrame.getContentPane().add(this.pan);
        this.gameFrame.pack();
        this.gameFrame.setSize(WHIDTH, HEIGHT);
        this.gameFrame.setResizable(false);
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setOpaque(true);
        backgroundLabel.setBackground(Color.BLUE);
        this.gameFrame.setContentPane(backgroundLabel);
        //this.gameFrame.setComponentZOrder(backgroundLabel, 0);
        this.gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.gameFrame.setVisible(true);
    }

    @Override
    public void renderView(final List<GameEntity> gameEntitiesList, final Player player, final Hud hud) {
        worldView.renderWorld(gameFrame, gameEntitiesList, player);
        hudView.renderHud(gameFrame, hud);
    }

}
