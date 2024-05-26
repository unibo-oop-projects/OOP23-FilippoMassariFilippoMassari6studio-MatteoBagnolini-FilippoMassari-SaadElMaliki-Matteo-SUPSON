package supson.model.collisions;

/**
 * This is an interface to implement an observer design pattern for collisions.
 * It represents an observer, that can be notified when an event occurs.
 * @see supson.model.collisions.CollisionObservable
 */
public interface CollisionObserver {
    
    /**
     * This method perform some actions based on the event occured.
     * It should be called by {@link supson.model.collisions.CollisionObservable#notifyObservers(CollisionEvent)}.
     * @param event the event occured
     */
    void onNotify(CollisionEvent event);

}
