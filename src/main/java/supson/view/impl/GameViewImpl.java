package supson.view.impl;

import java.util.List;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

import supson.model.block.api.BlockEntity;
import supson.model.entity.impl.Enemy;
import supson.model.entity.impl.Player;
import supson.view.api.BlockView;
import supson.view.api.EnemyView;
import supson.view.api.PlayerView;

public class GameViewImpl extends JPanel {

    private final BlockView BlockView = new BlockViewImpl();
    private final EnemyView EnemyView = new EnemyViewImpl();
    private final PlayerView PlayerView = new PlayerViewImpl();

    private Image backgroundImage;
    private Image playerImage;

    public GameViewImpl() {
        // Carica le immagini degli sprite
        backgroundImage = new ImageIcon("https://www.google.com/url?sa=i&url=https%3A%2F%2Fit.freepik.com%2Ffoto-premium%2Fscenario-per-uno-sfondo-di-gioco-platform-2d-per-giochi-retro-ai_66279596.htm&psig=AOvVaw1Hn_bi4q1E0LqQXtwY57lG&ust=1714496909224000&source=images&cd=vfe&opi=89978449&ved=0CBIQjRxqFwoTCPDg9ez054UDFQAAAAAdAAAAABAE").getImage();
        playerImage = new ImageIcon("https://www.google.com/url?sa=i&url=https%3A%2F%2Ftoppng.com%2Ffree-image%2Fsonic-mania-color-pallette-sonic-mania-plus-sprites-PNG-free-PNG-Images_200512&psig=AOvVaw0TC9uoM1ygynTin2vAMqf6&ust=1714498430088000&source=images&cd=vfe&opi=89978449&ved=0CBIQjRxqFwoTCLDzpcL654UDFQAAAAAdAAAAABAJ").getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Disegna lo sfondo
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        // Disegna il giocatore
        g.drawImage(playerImage, 100, 100, this);
    }

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
