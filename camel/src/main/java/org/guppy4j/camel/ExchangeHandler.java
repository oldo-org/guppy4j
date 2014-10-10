package org.guppy4j.camel;

/**
 * Exchange handler (typically used by a DelegatingProcessor)
 */
public interface ExchangeHandler {

    /**
     * @param exchange The exchange
     */
    void handle(IExchange exchange);

}
