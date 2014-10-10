package org.guppy4j.camel;

import org.apache.camel.Exchange;

/**
 * A convenient wrapper around a Camel exchange object
 */
public final class ExchangeWrapper implements IExchange {

    private final Exchange exchange;

    ExchangeWrapper(Exchange exchange) {
        this.exchange = exchange;
    }

    @Override
    public String getBodyAsString() {
        return exchange.getIn().getBody(String.class);
    }

    @Override
    public <X> void set(X x, Class<X> type) {
        exchange.setProperty(type.getName(), x);
    }

    @Override
    public <X> X get(Class<X> c) {
        return c.cast(exchange.getProperty(c.getName()));
    }

    @Override
    public void setHeader(String name, Object value) {
        exchange.getOut().setHeader(name, value);
    }

    @Override
    public void stop() {
        exchange.setProperty(Exchange.ROUTE_STOP, Boolean.TRUE);
    }

    @Override
    public Exception getCaughtException() {
        return exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
    }

    @Override
    public void setBody(String payload) {
        exchange.getOut().setBody(payload);
    }
}
