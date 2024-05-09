package supson.view;

import supson.common.GameEntityType;

import java.util.HashMap;
import java.util.Map;

public class SpriteMap {
    private Map<GameEntityType, String> spritePaths;

    public SpriteMap() {
        spritePaths = new HashMap<>();
        initializeSpritePaths();
    }

    private void initializeSpritePaths() {
        spritePaths.put(GameEntityType.PLAYER, "src\\resources\\sprite\\player_sprite.jpg");
        spritePaths.put(GameEntityType.ENEMY, "src\\resources\\sprite\\enemy_sprite.jpg");
        spritePaths.put(GameEntityType.TERRAIN, "path_al_sprite_del_blocco.png");
        spritePaths.put(GameEntityType.LIFE_BOOST_POWER_UP, "path_al_sprite_del_blocco.png");
        spritePaths.put(GameEntityType.STRNGTH_BOOST_POWER_UP, "path_al_sprite_del_blocco.png");
        spritePaths.put(GameEntityType.RING, "path_al_sprite_del_blocco.png");
        spritePaths.put(GameEntityType.DAMAGE_TRAP, "path_al_sprite_del_blocco.png");
        
    }

    public String getSpritePath(GameEntityType gameObjectType) {
        return spritePaths.get(gameObjectType);
    }
}
