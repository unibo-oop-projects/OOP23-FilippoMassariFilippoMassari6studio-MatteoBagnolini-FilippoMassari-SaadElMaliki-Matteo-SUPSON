package supson.common.api;

import supson.model.hitbox.impl.CollisionEvents;

public interface Observable {
    
    void register(Observer observer);

    void unregister(Observer observer);

    void notifyObservers(CollisionEvents event);

}
