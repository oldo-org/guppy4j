package org.guppy4j.speech;

public interface Speaker {

    void speak(String text);

    String name();

    void stopAll();

    Speaker NONE = new Speaker() {
        @Override
        public void speak(String text) {
            // do nothing
        }

        @Override
        public String name() {
            return "NONE";
        }

        @Override
        public void stopAll() {
            // do nothing
        }
    };
}
