package org.guppy4j.sound;

import org.guppy4j.log.Log;
import org.guppy4j.log.LogProvider;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import java.io.IOException;

import static javax.sound.sampled.AudioFormat.Encoding.PCM_SIGNED;
import static javax.sound.sampled.AudioSystem.getAudioInputStream;
import static org.guppy4j.log.Log.Level.debug;

/**
 * Converts and plays audio stream (based on conversion
 * support found in the Java audio system)
 */
public final class ConvertingStreamPlayer implements AudioStreamPlayer {

    private static final int DEFAULT_BUFFER_SIZE = 65536;

    private final Log log;
    private final int bufferSize;

    public ConvertingStreamPlayer(LogProvider logProvider) {
        this(logProvider, DEFAULT_BUFFER_SIZE);
    }

    public ConvertingStreamPlayer(LogProvider logProvider, int bufferSize) {
        log = logProvider.getLog(getClass());
        this.bufferSize = bufferSize;
    }

    @Override
    public void play(final AudioInputStream stream)
            throws LineUnavailableException, IOException {

        final AudioFormat outFormat = getOutFormat(stream.getFormat());
        final Info info = new Info(SourceDataLine.class, outFormat);

        log.as(debug, "Playing started ...");

        try (final SourceDataLine line =
                     (SourceDataLine) AudioSystem.getLine(info)) {

            if (line != null) {
                line.open(outFormat);
                line.start();
                stream(getAudioInputStream(outFormat, stream), line);
                line.drain();
                line.stop();
            }
        }

        log.as(debug, "Playing ended ...");
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