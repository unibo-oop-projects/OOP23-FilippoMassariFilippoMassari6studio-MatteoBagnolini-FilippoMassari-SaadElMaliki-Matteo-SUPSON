package supson.view;

import supson.common.GameEntityType;

import java.util.HashMap;
import java.util.Map;

/**
 * The SpriteMap class is responsible for mapping game entity types to their corresponding sprite paths.
 */
public class SpriteMap {
    private Map<GameEntityType, String> spritePaths;

    /**
     * Constructs a new SpriteMap object and initializes the spritePaths map.
     */
    public SpriteMap() {
        spritePaths = new HashMap<>();
        initializeSpritePaths();
    }

    /**
     * Initializes the spritePaths map with the corresponding sprite paths for each game entity type.
     */
    private void initializeSpritePaths() {
        spritePaths.put(GameEntityType.PLAYER, "sprite/player_sprite.jpg");
        spritePaths.put(GameEntityType.ENEMY, "sprite/enemy_sprite.jpg");
        spritePaths.put(GameEntityType.TERRAIN, "sprite/terrain_sprite.jpg");
        spritePaths.put(GameEntityType.LIFE_BOOST_POWER_UP, "sprite/life_powerUp_sprite.png");
        spritePaths.put(GameEntityType.STRNGTH_BOOST_POWER_UP, "sprite/strengt_powerUp_sprite.png");
        spritePaths.put(GameEntityType.RING, "sprite/ring_sprite.png");
        spritePaths.put(GameEntityType.DAMAGE_TRAP, "sprite/damage_trap_sprite_g.png");
    }

    /**
     * Retrieves the sprite path for the given game entity type.
     *
     * @param gameObjectType the game entity type
     * @return the sprite path for the given game entity type
     */
    public String getSpritePath(final GameEntityType gameObjectType) {
        return spritePaths.get(gameObjectType);
    }
}
