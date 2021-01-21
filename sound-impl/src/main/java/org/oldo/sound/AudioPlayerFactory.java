package org.oldo.sound;

import java.net.URL;
import java.util.function.Supplier;

import org.oldo.log.LogProvider;

/**
 * Convenient creation of AudioPlayer instances
 */
public class AudioPlayerFactory implements Supplier<AudioPlayer<URL>> {

    private final LogProvider logProvider;

    public AudioPlayerFactory(LogProvider logProvider) {
        this.logProvider = logProvider;
    }

    @Override
    public AudioPlayer<URL> get() {
        return new StdAudioPlayer(logProvider,
            new DirectStreamPlayer(),
            new ConvertingStreamPlayer(logProvider));
    }
}
