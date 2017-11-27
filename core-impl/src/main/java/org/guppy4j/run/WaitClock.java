package org.guppy4j.run;

/**
 * Defines a target duration, then starts a timer and later
 * waits for the required remaining time
 */
public final class WaitClock implements Startable, Stoppable {

    private final long millis;
    private long start;

    public WaitClock(long millis) {
        this.millis = millis;
    }

    @Override
    public void start() {
        start = System.currentTimeMillis();
    }

    @Override
    public void stop() {
        waitRemaining();
    }

    public void waitRemaining() {
        final long durationMillis = System.currentTimeMillis() - start;
        if (durationMillis < millis) {
            try {
                Thread.sleep(millis - durationMillis);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
