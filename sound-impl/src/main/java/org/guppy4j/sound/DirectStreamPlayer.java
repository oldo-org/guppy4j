package org.guppy4j.sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineEvent.Type;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;
import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Plays supported audio files directly
 */
public final class DirectStreamPlayer implements AudioStreamPlayer {

    private final Deque<Clip> clipsPlaying = new ConcurrentLinkedDeque<>();

    @Override
    public void play(AudioInputStream stream)
        throws LineUnavailableException, IOException {

        final Clip clip = (Clip) AudioSystem.getLine(
            new Info(Clip.class, stream.getFormat()));

        clip.open(stream);
        clipsPlaying.add(clip);

        clip.start();

        clip.addLineListener(new LineListener() {
            @Override
            public void update(LineEvent event) {
                if (Type.STOP.equals(event.getType())) {
                    clipsPlaying.remove(clip);
                    clip.close();
                }
            }
        });
    }

    @Override
    public void stopAll() {
        while (clipsPlaying.peek() != null) {
            try (final Clip clip = clipsPlaying.pop()) {
                clip.stop();
            }
        }
    }
}
