package org.guppy4j.sound;

import org.guppy4j.log.Log;
import org.guppy4j.log.LogProvider;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;

import static javax.sound.sampled.AudioSystem.getAudioFileFormat;
import static javax.sound.sampled.AudioSystem.getAudioInputStream;
import static javax.sound.sampled.AudioSystem.isFileTypeSupported;
import static org.guppy4j.log.Log.Level.error;

/**
 * Default audio player that delegates to the appropriate stream player
 */
public final class StdAudioPlayer implements AudioPlayer<URL> {

    private final AudioPlayer<AudioInputStream> directPlayer;
    private final AudioPlayer<AudioInputStream> convertingPlayer;

    private final Log log;

    public StdAudioPlayer(LogProvider logProvider,
                          AudioPlayer<AudioInputStream> directPlayer,
                          AudioPlayer<AudioInputStream> convertingPlayer) {
        this.directPlayer = directPlayer;
        this.convertingPlayer = convertingPlayer;
        this.log = logProvider.getLog(getClass());
    }

    @Override
    public void play(final URL url) {
        try {
            play(getPlayer(getAudioFileFormat(url)), url);
        } catch (UnsupportedAudioFileException | IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private AudioPlayer<AudioInputStream> getPlayer(AudioFileFormat format) {
        return isFileTypeSupported(format.getType()) ? directPlayer : convertingPlayer;
    }

    private void play(AudioPlayer<AudioInputStream> player, URL url) {
        try (AudioInputStream stream = getAudioInputStream(url)) {

            player.play(stream);

        } catch (UnsupportedAudioFileException e) {
            throw new IllegalArgumentException(e);
        } catch (IOException e) {
            log.as(error, e);
        }
    }

    @Override
    public void stop() {
        directPlayer.stop();
        convertingPlayer.stop();
    }
}
