package org.guppy4j.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Camel processor that wraps the Exchange and passes
 * it to a configurable ExchangeHandler
 */
public class DelegatingProcessor implements Processor {

    private final ExchangeHandler handler;

    public DelegatingProcessor(ExchangeHandler handler) {
        this.handler = handler;
    }

    @Override
    public final void process(Exchange exchange) throws Exception {
        handler.handle(new ExchangeWrapper(exchange));
    }
}
