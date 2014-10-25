package org.guppy4j.event;


/**
 * Change notifier
 */
public interface ChangeNotifier<T> {

    void addListener(ChangeListener<T> listener);

}
