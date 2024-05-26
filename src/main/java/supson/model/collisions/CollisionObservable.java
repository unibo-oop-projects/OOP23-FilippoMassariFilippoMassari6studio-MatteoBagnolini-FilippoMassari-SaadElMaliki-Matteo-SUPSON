package supson.model.collisions;

public interface CollisionObservable {
    
    void register(CollisionObserver observer);

    void unregister(CollisionObserver observer);

    void notifyObservers(CollisionEvent event);

}
