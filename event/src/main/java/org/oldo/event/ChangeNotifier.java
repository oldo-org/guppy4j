package org.oldo.event;


/**
 * Change notifier
 */
public interface ChangeNotifier<T> {

    void addListener(ChangeListener<T> listener);

}
