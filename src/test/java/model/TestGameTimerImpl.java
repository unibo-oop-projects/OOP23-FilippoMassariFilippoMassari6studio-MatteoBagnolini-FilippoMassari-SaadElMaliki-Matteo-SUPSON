package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import supson.model.timer.api.GameTimer;
import supson.model.timer.impl.GameTimerImpl;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the GameTimerImpl class.
 */
public class TestGameTimerImpl {

    private GameTimer timer;

    @BeforeEach
    void setUp() {
        timer = new GameTimerImpl();
    }

    /**
     * Tests the start() method of the GameTimerImpl class.
     * It checks if the timer starts correctly and records the start time.
     */
    @Test
    void testStart() {
        timer.start();
        assertTrue(timer.getElapsedTime() >= 0);
    }

    /**
     * Tests the stop() method of the GameTimerImpl class.
     * It checks if the timer stops correctly and records the elapsed time.
     */
    @Test
    void testStop() throws InterruptedException {
        timer.start();
        Thread.sleep(100); // Sleep for 100 milliseconds
        timer.stop();
        long elapsedTime = timer.getElapsedTime();
        assertTrue(elapsedTime >= 100_000_000); // 100 milliseconds in nanoseconds
    }

    /**
     * Tests the reset() method of the GameTimerImpl class.
     * It checks if the timer resets correctly and clears the start and elapsed times.
     */
    @Test
    void testReset() {
        timer.start();
        timer.stop();
        timer.reset();
        assertEquals(0, timer.getElapsedTime());
        assertEquals(0, timer.getElapsedTimeInSeconds(), 0.000001);
    }

    /**
     * Tests the getElapsedTime() method of the GameTimerImpl class.
     * It checks if the elapsed time is correctly calculated.
     */
    @Test
    void testGetElapsedTime() throws InterruptedException {
        timer.start();
        Thread.sleep(200); // Sleep for 200 milliseconds
        timer.stop();
        long elapsedTime = timer.getElapsedTime();
        assertTrue(elapsedTime >= 200_000_000); // 200 milliseconds in nanoseconds
    }

    /**
     * Tests the getElapsedTimeInSeconds() method of the GameTimerImpl class.
     * It checks if the elapsed time in seconds is correctly calculated.
     */
    @Test
    void testGetElapsedTimeInSeconds() throws InterruptedException {
        timer.start();
        Thread.sleep(300); // Sleep for 300 milliseconds
        timer.stop();
        double elapsedTimeInSeconds = timer.getElapsedTimeInSeconds();
        assertTrue(elapsedTimeInSeconds >= 0.3); // 300 milliseconds in seconds
    }

    /**
     * Tests the timer when it is started, stopped, and then started again.
     * It checks if the elapsed time is accumulated correctly.
     */
    @Test
    void testStartStopStart() throws InterruptedException {
        timer.start();
        Thread.sleep(100); // Sleep for 100 milliseconds
        timer.stop();
        
        long firstElapsedTime = timer.getElapsedTime();
        
        timer.start();
        Thread.sleep(100); // Sleep for another 100 milliseconds
        timer.stop();
        
        long secondElapsedTime = timer.getElapsedTime();
        
        assertTrue(secondElapsedTime >= firstElapsedTime + 100_000_000); // 100 milliseconds in nanoseconds
    }
}
