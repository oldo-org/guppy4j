package org.guppy4j.sound;

import org.guppy4j.log.LogProvider;

import java.util.function.Supplier;

/**
 * Convenient creation of AudioPlayer instances
 */
public class AudioPlayerFactory implements Supplier<AudioPlayer> {

    private final LogProvider logProvider;

    public AudioPlayerFactory(LogProvider logProvider) {
        this.logProvider = logProvider;
    }

    @Override
    public AudioPlayer get() {
        return new StdAudioPlayer(logProvider,
            new DirectStreamPlayer(),
            new ConvertingStreamPlayer(logProvider));
    }
}
