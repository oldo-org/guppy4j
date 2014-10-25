package org.guppy4j.sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;

/**
 * Plays supported audio files directly
 */
public final class DirectStreamPlayer implements AudioStreamPlayer {

    @Override
    public void play(AudioInputStream stream)
            throws LineUnavailableException, IOException {

        final Clip clip = (Clip) AudioSystem.getLine(
                new DataLine.Info(Clip.class, stream.getFormat()));

        clip.open(stream);
        clip.start();

        clip.addLineListener(new LineListener() {
            @Override
            public void update(LineEvent event) {
                if (LineEvent.Type.STOP.equals(event.getType())) {
                    clip.close();
                }
            }
        });
    }
}
