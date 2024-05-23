package supson.model.timer.api;

public interface GameTimer {

    void start();

    void stop();

    void reset();

    long getElapsedTime();

    double getElapsedTimeInSeconds();

}