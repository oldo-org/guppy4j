package org.guppy4j.text;

import org.guppy4j.log.Log;
import org.guppy4j.log.LogProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.function.Predicate;

import static java.util.Map.Entry;
import static java.util.stream.Collectors.toList;
import static org.guppy4j.log.Log.Level.info;

/**
 * Named Strings based on a properties file
 */
public class NamedStringsImpl implements NamedStrings {

    private final Map<String, NamedString> map;

    public NamedStringsImpl(LogProvider logProvider,
                            Properties properties,
                            String propertiesOrigin) {
        map = toNamedValuesMap(properties);

        final Log log = logProvider.getLog(getClass());
        log.as(info, "Settings loaded from {}", propertiesOrigin);
        log.as(info, "Number of name/value pairs: {}", map.size());
    }

    @Override
    public NamedString get(String name) {
        return map.get(name);
    }

    @Override
    public Iterable<NamedString> all() {
        return map.values();
    }

    @Override
    public Iterable<NamedString> matches(Predicate<NamedString> condition) {
        return map.values().stream().filter(condition).collect(toList());
    }

    private static Map<String, NamedString> toNamedValuesMap(Properties properties) {
        final Map<String, NamedString> map = new HashMap<>(properties.size());
        for (Entry<?, ?> e : properties.entrySet()) {
            final String name = e.getKey().toString();
            final String value = e.getValue().toString();
            map.put(name, new NamedStringImpl(name, value));
        }
        return map;
    }
}
