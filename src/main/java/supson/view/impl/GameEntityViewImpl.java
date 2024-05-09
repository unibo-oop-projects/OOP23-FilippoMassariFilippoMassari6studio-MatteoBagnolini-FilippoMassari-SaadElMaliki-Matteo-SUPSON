package supson.view.impl;

import supson.model.entity.api.GameEntity;
import supson.model.entity.impl.Player;
import supson.view.SpriteMap;
import supson.view.api.GameEntityView;

import java.io.File;

/**
 * Implementation of the GameEntityView interface.
 * This class is responsible for rendering game entities and the HUD (Heads-Up Display).
 */
public class GameEntityViewImpl implements GameEntityView{

    private static final SpriteMap spriteMap = new SpriteMap();

    @Override
    public void renderGameEntity(GameEntity gameEntity, GamePanel gamePanel) {
        File playerFile = new File(spriteMap.getSpritePath(gameEntity.getGameEntityType()));
        gamePanel.renderImage(playerFile.getAbsolutePath());
    }

    /**
     * Renders the HUD (Heads-Up Display) for the specified player on the game panel.
     * @param player The player for which to render the HUD.
     * @param gamePanel The game panel on which to render the HUD.
     */
    public void renderHud(Player player, GamePanel gamePanel) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'renderHud'");
    }

}
