package org.guppy4j.sound;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;

/**
 * Plays audio files (like wav, mp3, etc.)
 */
public interface AudioPlayer {

    void play(URL url) throws IOException, UnsupportedAudioFileException;

    void stopAll();
}
