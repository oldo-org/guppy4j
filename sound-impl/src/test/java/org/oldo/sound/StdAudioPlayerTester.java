package org.oldo.sound;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

import org.oldo.log.LogProvider;

/**
 * For manual testing of audio file playback
 */
public class StdAudioPlayerTester {

    public static void main(String[] args) throws IOException, UnsupportedAudioFileException {
        final AudioPlayer<URL> player = new AudioPlayerFactory(LogProvider.NONE).get();

        final String mp3 = "/home/oliver/bubba/storage/music/morrissey/Morrissey_-_You_Have_Killed_Me.mp3";
        final String aac = "/home/oliver/files/iTunes_test1_AAC-LC_v4_Stereo_VBR_128kbps_44100Hz.m4a";

        playPath(player, mp3);
//        playPath(player, aac);

//        playPath(player, "/home/oliver/Hallelujah.m4a");
    }

    private static void playPath(AudioPlayer<URL> player, String path) throws IOException {
        final URL url = Paths.get(path).toUri().toURL();
        player.play(url);
    }
}
