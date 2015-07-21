package org.guppy4j.messaging;

import org.guppy4j.NamedValue;
import org.guppy4j.NamedValues;
import org.guppy4j.log.Log;
import org.guppy4j.log.LogProvider;
import org.guppy4j.logic.Predicate;

import static org.guppy4j.log.Log.Level.debug;

/**
 * Filters messages based on rules from properties
 */
public class ConfigurableFilterCondition implements Predicate<ReadableTree> {

    private final NamedValues filterRules;
    private final Log log;

    public ConfigurableFilterCondition(NamedValues filterRules,
                                       LogProvider logProvider) {
        log = logProvider.getLog(getClass());
        this.filterRules = filterRules;
    }

    public boolean isTrueFor(ReadableTree message) {
        for (NamedValue rule : filterRules.all()) {

            final String filterExpression = rule.name();

            final String value = message.get(filterExpression);

            final boolean contained = contains(rule.valueSplitBy(','), value);

            log.as(debug, "Value {} is {} in filter values [{}] (expr={})",
                    value, contained ? "contained" : "NOT",
                    rule.value(), filterExpression);

            if (not(contained)) {
                return false;
            }
        }
        // otherwise
        return true;
    }

    private boolean contains(String[] filterValues, String value) {
        for (String filterValue : filterValues) {
            if (filterValue.equalsIgnoreCase(value)) {
                return true;
            }
        }
        // otherwise
        return false;
    }

    private static boolean not(boolean b) {
        return !b;
    }
}
