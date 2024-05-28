package supson.model.timer.api;

public interface CollectibleTimer extends Runnable {

    void activateEffect();

    void terminateEffect();

    void run();

}