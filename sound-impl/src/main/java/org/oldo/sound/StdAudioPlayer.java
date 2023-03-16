package org.oldo.sound;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;

import org.oldo.log.Log;
import org.oldo.log.LogProvider;

import static javax.sound.sampled.AudioSystem.getAudioFileFormat;
import static javax.sound.sampled.AudioSystem.getAudioInputStream;
import static javax.sound.sampled.AudioSystem.isFileTypeSupported;
import static org.oldo.log.Log.Level.error;

/**
 * Default audio player that delegates to the appropriate stream player
 */
public final class StdAudioPlayer implements AudioPlayer<URL> {

    private final AudioPlayer<URL> directPlayer;
    private final AudioPlayer<URL> convertingPlayer;

    private final Log log;

    public StdAudioPlayer(LogProvider logProvider,
                          AudioPlayer<URL> directPlayer,
                          AudioPlayer<URL> convertingPlayer) {
        this.directPlayer = directPlayer;
        this.convertingPlayer = convertingPlayer;
        this.log = logProvider.getLog(getClass());
    }

    @Override
    public void play(final URL url) {
        try {
            final AudioPlayer<URL> player = getPlayer(getAudioFileFormat(url));
            player.play(url);
        } catch (UnsupportedAudioFileException | IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private AudioPlayer<URL> getPlayer(AudioFileFormat format) {
        return isFileTypeSupported(format.getType()) ? directPlayer : convertingPlayer;
    }

    @Override
    public void stop() {
        directPlayer.stop();
        convertingPlayer.stop();
    }
}
