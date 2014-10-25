package org.guppy4j.sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;

/**
 * Plays audio streams
 */
public interface AudioStreamPlayer {

    void play(AudioInputStream stream) throws LineUnavailableException, IOException;
}
