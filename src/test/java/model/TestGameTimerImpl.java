package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import supson.model.timer.api.GameTimer;
import supson.model.timer.impl.GameTimerImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This class contains unit tests for the GameTimerImpl class.
 */
final class TestGameTimerImpl {

    private static final int NANO_SEC = 100_000_000;
    private static final double MICRO_SEC = 0.1;
    private static final int SLEEP_SEC = 100;
    private static final double DELTA = 0.000_001;

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
        Thread.sleep(SLEEP_SEC);
        timer.stop();
        final long elapsedTime = timer.getElapsedTime();
        assertTrue(elapsedTime >= NANO_SEC);
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
        assertEquals(0, timer.getElapsedTimeInSeconds(), DELTA);
    }

    /**
     * Tests the getElapsedTime() method of the GameTimerImpl class.
     * It checks if the elapsed time is correctly calculated.
     */
    @Test
    void testGetElapsedTime() throws InterruptedException {
        timer.start();
        Thread.sleep(2 * SLEEP_SEC);
        timer.stop();
        final long elapsedTime = timer.getElapsedTime();
        assertTrue(elapsedTime >= 2 * NANO_SEC);
    }

    /**
     * Tests the getElapsedTimeInSeconds() method of the GameTimerImpl class.
     * It checks if the elapsed time in seconds is correctly calculated.
     */
    @Test
    void testGetElapsedTimeInSeconds() throws InterruptedException {
        timer.start();
        Thread.sleep(3 * SLEEP_SEC);
        timer.stop();
        final double elapsedTimeInSeconds = timer.getElapsedTimeInSeconds();
        assertTrue(elapsedTimeInSeconds >= 3 * MICRO_SEC);
    }

    /**
     * Tests the timer when it is started, stopped, and then started again.
     * It checks if the elapsed time is accumulated correctly.
     */
    @Test
    void testStartStopStart() throws InterruptedException {
        timer.start();
        Thread.sleep(SLEEP_SEC);
        timer.stop();
        final long firstElapsedTime = timer.getElapsedTime();
        timer.start();
        Thread.sleep(SLEEP_SEC);
        timer.stop();
        final long secondElapsedTime = timer.getElapsedTime();
        assertTrue(secondElapsedTime >= firstElapsedTime + MICRO_SEC);
    }
}
