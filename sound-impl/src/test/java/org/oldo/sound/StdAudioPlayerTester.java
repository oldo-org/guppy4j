package org.oldo.sound;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

import org.oldo.log.LogProvider;
import org.oldo.log.SystemLogProvider;

/**
 * For manual testing of audio file playback
 */
public class StdAudioPlayerTester {

    public static void main(String[] args) throws IOException {
        final AudioPlayer<URL> player = new AudioPlayerFactory(new SystemLogProvider()).get();

        final String mp3 = "/home/oliver/media/audio/AURORA - Heathens (Live Performance) Vevo.mp3";
        final String aac = "/home/oliver/files/sample4.aac";

        final URL url = Paths.get(mp3).toUri().toURL();
        player.play(url);
    }
}
