package org.oldo.sound;

import org.oldo.run.Stoppable;

/**
 * Plays audio files (like wav, mp3, etc.)
 */
public interface AudioPlayer<T> extends Stoppable {

    void play(T source);

}
