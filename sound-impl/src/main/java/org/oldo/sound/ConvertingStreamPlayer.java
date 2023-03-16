package org.oldo.sound;

import org.oldo.collections.Consumables;
import org.oldo.collections.ConsumablesImpl;
import org.oldo.log.Log;
import org.oldo.log.LogProvider;

import javax.sound.sampled.*;
import javax.sound.sampled.DataLine.Info;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static javax.sound.sampled.AudioFormat.Encoding.PCM_SIGNED;
import static javax.sound.sampled.AudioSystem.getAudioInputStream;
import static org.oldo.log.Log.Level.debug;
import static org.oldo.log.Log.Level.error;

/**
 * Converts and plays audio stream (based on conversion
 * support found in the Java audio system)
 */
public final class ConvertingStreamPlayer implements AudioPlayer<URL> {

    private static final int DEFAULT_BUFFER_SIZE = 65536;

    private final Consumables<DataLine> linesPlaying = new ConsumablesImpl<>();

    private final Log log;
    private final int bufferSize;

    private final ExecutorService executor = Executors.newCachedThreadPool();

    public ConvertingStreamPlayer(LogProvider logProvider) {
        this(logProvider, DEFAULT_BUFFER_SIZE);
    }

    public ConvertingStreamPlayer(LogProvider logProvider, int bufferSize) {
        log = logProvider.getLog(getClass());
        this.bufferSize = bufferSize;
    }

    @Override
    public void play(URL url) {
        executor.execute(() -> playLoop(url));
    }

    private void playLoop(URL url) {
        try (AudioInputStream stream = getAudioInputStream(url)) {

            final AudioFormat outFormat = getOutFormat(stream.getFormat());
            final Info info = new Info(SourceDataLine.class, outFormat);

            log.as(debug, "Playing started ...");

            try (final SourceDataLine line =
                         (SourceDataLine) AudioSystem.getLine(info)) {

                if (line != null) {
                    line.open(outFormat);
                    linesPlaying.add(line);
                    line.start();
                    stream(getAudioInputStream(outFormat, stream), line);
                    line.drain();
                    line.stop();
                    linesPlaying.remove(line);
                }
            } catch (LineUnavailableException | IOException e) {
                throw new IllegalStateException(e);
            }

            log.as(debug, "Playing ended ...");

        } catch (UnsupportedAudioFileException e) {
            throw new IllegalArgumentException(e);
        } catch (IOException e) {
            log.as(error, e);
        }
    }

    @Override
    public void stop() {
        linesPlaying.applyToAll(DataLine::stop);
    }

    private static AudioFormat getOutFormat(AudioFormat inFormat) {
        final int ch = inFormat.getChannels();
        final float rate = inFormat.getSampleRate();
        return new AudioFormat(PCM_SIGNED, rate, 16, ch, ch * 2, rate, false);
    }

    private void stream(AudioInputStream in, SourceDataLine line) throws IOException {
        final byte[] buffer = new byte[bufferSize];
        for (int n = 0; n != -1; n = in.read(buffer, 0, buffer.length)) {
            line.write(buffer, 0, n);
        }
    }
}