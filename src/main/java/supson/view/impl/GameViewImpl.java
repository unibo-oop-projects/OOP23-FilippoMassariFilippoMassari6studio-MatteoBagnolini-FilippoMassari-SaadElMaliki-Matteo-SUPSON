package supson.view.impl;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import supson.model.entity.api.GameEntity;
import supson.model.entity.player.Player;
import supson.model.hud.api.Hud;
import supson.view.api.GameView;
import supson.view.api.HudView;
import supson.view.api.WorldView;
import supson.view.impl.world.WorldViewImpl;

public final class GameViewImpl implements GameView {

    private static final String BG_PATH = "sprite/background.jpg";
    private static final int WIDTH = 948;
    private static final int HEIGHT = 720;

    private final WorldView worldView;
    private final HudView hudView;
    private final JFrame gameFrame;
    private final JPanel mainPanel;
    private final ImagePanel backgroundPanel;

    public GameViewImpl(JFrame frame) {
        this.gameFrame = frame;
        this.worldView = new WorldViewImpl();
        this.hudView = new HudViewImpl();
        this.mainPanel = new JPanel();
        this.backgroundPanel = new ImagePanel(BG_PATH);
    }

    @Override
    public void initView() {
        backgroundPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        backgroundPanel.setLayout(new BorderLayout());

        mainPanel.setOpaque(false);
        mainPanel.setLayout(new BorderLayout());

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        backgroundPanel.setBounds(0, 0, WIDTH, HEIGHT);
        mainPanel.setBounds(0, 0, WIDTH, HEIGHT);

        layeredPane.add(backgroundPanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(mainPanel, JLayeredPane.PALETTE_LAYER);

        gameFrame.setContentPane(layeredPane);
        gameFrame.pack();
        gameFrame.setResizable(false);
        gameFrame.setVisible(true);
    }

    @Override
    public void renderView(final List<GameEntity> gameEntitiesList, final Player player, final Hud hud) {
        mainPanel.removeAll();
        worldView.renderWorld(mainPanel, gameEntitiesList, player);
        hudView.renderHud(mainPanel, hud);
        mainPanel.repaint();
    }
}
