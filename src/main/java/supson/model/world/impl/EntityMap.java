package supson.model.world.impl;

import supson.common.GameEntityType;

import java.util.HashMap;
import java.util.Map;

/**
 * The EntityMap class represents a map that associates integer indices with GameEntityTypes.
 */
public final class EntityMap {

    private static final int TERRAIN_INDEX = 1;
    private static final int LIFE_BOOST_POWER_UP_INDEX = 2;
    private static final int STRNGTH_BOOST_POWER_UP_INDEX = 3;
    private static final int RING_INDEX = 4;
    private static final int DAMAGE_TRAP_INDEX = 5;
    private static final int ENEMY_INDEX = 6;

    private final Map<Integer, GameEntityType> entityMap;

    /**
     * Constructs a new EntityMap object and initializes the entity map.
     */
    public EntityMap() {
        entityMap = new HashMap<>();
        initializeMap();
    }

    /**
     * Initializes the entity map with predefined mappings of indices to GameEntityTypes.
     */
    private void initializeMap() {
        entityMap.put(TERRAIN_INDEX, GameEntityType.TERRAIN);
        entityMap.put(LIFE_BOOST_POWER_UP_INDEX, GameEntityType.LIFE_BOOST_POWER_UP);
        entityMap.put(STRNGTH_BOOST_POWER_UP_INDEX, GameEntityType.STRNGTH_BOOST_POWER_UP);
        entityMap.put(RING_INDEX, GameEntityType.RING);
        entityMap.put(DAMAGE_TRAP_INDEX, GameEntityType.DAMAGE_TRAP);
        entityMap.put(ENEMY_INDEX, GameEntityType.ENEMY);
    }

    /**
     * Retrieves the GameEntityType associated with the given index.
     *
     * @param index the index of the GameEntityType to retrieve
     * @return the GameEntityType associated with the given index, or null if no mapping exists
     */
    public GameEntityType getEntityType(final int index) {
        return this.entityMap.get(index);
    }

}
