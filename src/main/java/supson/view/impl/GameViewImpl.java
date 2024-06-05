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

public final class GameViewImpl implements GameView {

    private static final int WIDTH = 948;
    private static final int HEIGHT = 720;

    private final WorldView worldView;
    private final HudView hudView;
    private final JPanel mainPanel; // Aggiunto il JPanel

    public GameViewImpl() {
        this.worldView = new WorldViewImpl();
        this.hudView = new HudViewImpl();
        this.mainPanel = new JPanel(); // Inizializzato il JPanel
    }

    @Override
    public void initView() {
        mainPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT)); // Impostato le dimensioni del JPanel
        mainPanel.setBackground(Color.BLUE); // Impostato il colore di sfondo
        mainPanel.setLayout(new BorderLayout()); // Impostato il layout del JPanel

        // Aggiunto il JPanel al JFrame
        JFrame gameFrame = new JFrame("SUPER-SONIC");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setContentPane(mainPanel);
        gameFrame.pack();
        gameFrame.setResizable(false);
        gameFrame.setVisible(true);
    }

    @Override
    public void renderView(final List<GameEntity> gameEntitiesList, final Player player, final Hud hud) {
        mainPanel.removeAll(); // Rimossi i componenti dal JPanel
        worldView.renderWorld(mainPanel, gameEntitiesList, player);
        hudView.renderHud(mainPanel, hud);
        //mainPanel.revalidate(); // Aggiornato il JPanel
        mainPanel.repaint(); // Ridisegnato il JPanel
    }

    public Component getViewComponent() {
        return mainPanel; // Restituito il JPanel anziché il JFrame
    }
}
