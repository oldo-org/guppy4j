package org.guppy4j.camel;

/**
 * Everything we usually need from Camel exchange
 * as simpler methods
 */
public interface IExchange {

    String getBodyAsString();

    <X> void set(X x, Class<X> type);

    <X> X get(Class<X> c);

    void setHeader(String name, Object value);

    void stop();

    Exception getCaughtException();

    void setBody(String outgoingMessagePayload);
}
