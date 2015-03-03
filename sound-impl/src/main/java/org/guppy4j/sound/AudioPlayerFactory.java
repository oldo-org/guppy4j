package org.guppy4j.sound;

import org.guppy4j.log.LogProvider;
import org.guppy4j.logic.Source;

/**
 * Convenient creation of AudioPlayer instances
 */
public class AudioPlayerFactory implements Source<AudioPlayer> {

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
