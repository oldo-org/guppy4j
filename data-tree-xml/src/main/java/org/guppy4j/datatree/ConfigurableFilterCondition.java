package org.guppy4j.datatree;

import org.guppy4j.NamedString;
import org.guppy4j.NamedStrings;
import org.guppy4j.log.Log;
import org.guppy4j.log.LogProvider;

import java.util.function.Predicate;

import static org.guppy4j.log.Log.Level.debug;

/**
 * Filters messages based on rules from properties
 */
public class ConfigurableFilterCondition implements Predicate<ReadableTree> {

    private final NamedStrings filterRules;
    private final Log log;

    public ConfigurableFilterCondition(NamedStrings filterRules,
                                       LogProvider logProvider) {
        log = logProvider.getLog(getClass());
        this.filterRules = filterRules;
    }

    public boolean test(ReadableTree message) {
        for (NamedString rule : filterRules.all()) {

            final String filterExpression = rule.name();

            final String value = message.get(filterExpression);

            final boolean contained = contains(rule.splitBy(','), value);

            log.as(debug, "Value {} is {} in filter values [{}] (expr={})",
                    value, contained ? "contained" : "NOT",
                    rule.get(), filterExpression);

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
