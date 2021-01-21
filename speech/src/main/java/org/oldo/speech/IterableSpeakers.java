package org.oldo.speech;

/**
 * A set of speakers
 */
public interface IterableSpeakers extends Speaker {

    /**
     * Switches to the next speaker
     */
    void next();

}
