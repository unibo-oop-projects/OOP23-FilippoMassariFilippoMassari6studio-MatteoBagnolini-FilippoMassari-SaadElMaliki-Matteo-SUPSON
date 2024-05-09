package supson.view.impl;


import supson.model.entity.impl.Player;
import supson.view.api.GameEntityView;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PlayerViewImpl implements GameEntityView<Player>{

    private BufferedImage playerSprite;

    public PlayerViewImpl () {
        try {
            File playerFile = new File("src\\resources\\sprite\\player_sprite.jpg");
            this.playerSprite = ImageIO.read(playerFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void renderGameEntity(Player player, GamePanel gamePanel) {
        
    }

    public void renderHud(Player player, GamePanel gamePanel) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'renderHud'");
    }

}
