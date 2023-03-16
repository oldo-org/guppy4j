package org.oldo.sound;

import org.oldo.collections.Consumables;
import org.oldo.collections.ConsumablesImpl;

import javax.sound.sampled.*;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.LineEvent.Type;
import java.io.IOException;
import java.net.URL;

import static javax.sound.sampled.AudioSystem.getAudioInputStream;

/**
 * Plays supported audio files directly
 */
public final class DirectStreamPlayer implements AudioPlayer<URL> {

    private final Consumables<DataLine> linesPlaying = new ConsumablesImpl<>();

    @Override
    public void play(URL url) {
        try (AudioInputStream stream = getAudioInputStream(url)) {

            final Clip clip = openClip(stream);
            clip.start();

            linesPlaying.add(clip);

            clip.addLineListener(event -> {
                if (Type.STOP.equals(event.getType())) {
                    linesPlaying.remove(clip);
                    clip.close();
                }
            });
        } catch (UnsupportedAudioFileException | IOException e) {
            throw new IllegalArgumentException(e);
        }
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
