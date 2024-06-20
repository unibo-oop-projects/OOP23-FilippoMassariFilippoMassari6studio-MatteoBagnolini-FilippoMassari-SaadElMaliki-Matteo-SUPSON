package supson.common;

/**
 * Enum representing different types of blocks and entities in the game,
 * along with their associated sprite paths and indices.
 */
public enum GameEntityType {
    /**
     * Represents the player block.
     */
    PLAYER("sprite/player_sprite.jpg", 0),

    /**
     * Represents terrain blocks.
     */
    TERRAIN("sprite/terrain_sprite.jpg", 1),

    /**
     * Represents life boost power-up blocks.
     */
    LIFE_BOOST_POWER_UP("sprite/life_powerUp_sprite.png", 2),

    /**
     * Represents strength boost power-up blocks.
     */
    STRNGTH_BOOST_POWER_UP("sprite/strengt_powerUp_sprite.png", 3),

    /**
     * Represents ring blocks.
     */
    RING("sprite/ring_sprite.png", 4),

    /**
     * Represents damage trap blocks.
     */
    DAMAGE_TRAP("sprite/damage_trap_sprite.png", 5),

    /**
     * Represents the enemy block.
     */
    ENEMY("sprite/enemy_sprite.png", 6),

    /*
     * Represents sub terrain blocks.
     */
    SUBTERRAIN("sprite/subterrain_sprite.png", 7),

    /*
     * Represents the finihline blocks. 
     */
    FINISHLINE("sprite/finishline_sprite.png", 8);

    private final String spritePath;
    private final int index;

    /*
     * The costructor of GameEntityType enum
     */
    GameEntityType(final String spritePath, final int index) {
        this.spritePath = spritePath;
        this.index = index;
    }

    /**
     * Retrieves the sprite path for the game entity type.
     *
     * @return the sprite path
     */
    public String getSpritePath() {
        return spritePath;
    }

    /**
     * Retrieves the index associated with the game entity type.
     *
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     * Retrieves the GameEntityType associated with the given index.
     *
     * @param index the index of the GameEntityType to retrieve
     * @return the GameEntityType associated with the given index, or null if no mapping exists
     */
    public static GameEntityType getType(final int index) {
        for (GameEntityType type : values()) {
            if (type.getIndex() == index) {
                return type;
            }
        }
        return null;
    }
}
