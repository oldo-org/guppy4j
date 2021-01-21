package org.oldo.sound;

import org.oldo.collections.Consumables;
import org.oldo.collections.ConsumablesImpl;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.LineEvent.Type;
import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;

/**
 * Plays supported audio files directly
 */
public final class DirectStreamPlayer implements AudioPlayer<AudioInputStream> {

    private final Consumables<DataLine> linesPlaying = new ConsumablesImpl<>();

    @Override
    public void play(AudioInputStream stream) {

        final Clip clip = openClip(stream);
        clip.start();

        linesPlaying.add(clip);

        clip.addLineListener(event -> {
            if (Type.STOP.equals(event.getType())) {
                linesPlaying.remove(clip);
                clip.close();
            }
        });
    }

    @Override
    public void stop() {
        linesPlaying.applyToAll(DataLine::stop);
    }

    private Clip openClip(AudioInputStream stream) {
        final Info info = new Info(Clip.class, stream.getFormat());
        try {
            final Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
            return clip;
        } catch (LineUnavailableException | IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
