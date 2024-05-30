package supson.model.collisions.api;

import supson.model.collisions.CollisionEvent;

/**
 * This is an interface to implement an observer design pattern for collisions.
 * It represents an observable, that can be observed.
 * @see supson.model.collisions.api.CollisionObserver
 */
public interface CollisionObservable {

    /**
     * This method registers a new observer, which will be notified
     * about collision events.
     * @param observer the observer to be registered
     */
    void register(CollisionObserver observer);

    /**
     * This method unregisters an observer that was previously registeres.
     * The behavior is undefined if the observer is not registered.
     * @param observer the observer to be unregistered
     */
    void unregister(CollisionObserver observer);

    /**
     * This method notify all the registered observer of an event occured.
     * @param event the event occured
     */
    void notifyObservers(CollisionEvent event);

}
