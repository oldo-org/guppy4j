package org.guppy4j.sound;

import org.guppy4j.run.Stoppable;

/**
 * Plays audio files (like wav, mp3, etc.)
 */
public interface AudioPlayer<T> extends Stoppable {

    void play(T source);

}
