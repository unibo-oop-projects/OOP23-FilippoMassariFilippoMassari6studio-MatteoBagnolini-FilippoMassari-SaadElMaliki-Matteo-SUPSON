package supson.common.api;

import supson.model.hitbox.impl.CollisionEvents;

public interface Observer {
    
    void onNotify(CollisionEvents event);

}
