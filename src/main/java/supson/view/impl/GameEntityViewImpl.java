package supson.view.impl;

import supson.model.entity.api.GameEntity;
import supson.model.entity.impl.Player;
import supson.view.SpriteMap;
import supson.view.api.GameEntityView;

import java.io.File;

public class GameEntityViewImpl implements GameEntityView{

    private static final SpriteMap spriteMap = new SpriteMap();

    @Override
    public void renderGameEntity(GameEntity gameEntity, GamePanel gamePanel) {
        File playerFile = new File(spriteMap.getSpritePath(gameEntity.getGameEntityType()));
        gamePanel.renderImage(playerFile.getAbsolutePath());
    }

    public void renderHud(Player player, GamePanel gamePanel) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'renderHud'");
    }

}
