package supson.model.world.impl;

import supson.common.GameEntityType;

import java.util.HashMap;
import java.util.Map;


public class EntityMap {

    private Map<Integer, GameEntityType> entityMap;

    public EntityMap() {
        entityMap= new HashMap<>();
        initializeMap();
    }

    private void initializeMap() {
        entityMap.put(1, GameEntityType.TERRAIN);
        entityMap.put(2, GameEntityType.LIFE_BOOST_POWER_UP);
        entityMap.put(3, GameEntityType.STRNGTH_BOOST_POWER_UP);
        entityMap.put(4, GameEntityType.RING);
        entityMap.put(5, GameEntityType.DAMAGE_TRAP);
        entityMap.put(6, GameEntityType.ENEMY);

    }

    public GameEntityType getEntityType(int index) {
        return this.entityMap.get(index);
    }

}
