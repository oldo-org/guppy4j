package org.guppy4j.sound;

import java.util.function.Supplier;

import org.guppy4j.log.LogProvider;

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
