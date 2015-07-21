package org.guppy4j;

import org.guppy4j.io.Resources;
import org.guppy4j.log.Log;
import org.guppy4j.log.LogProvider;
import org.guppy4j.logic.Predicate;

import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static java.util.Map.Entry;
import static org.guppy4j.log.Log.Level.info;

/**
 * Settings based on a properties file
 */
public class NamedValuesImpl implements NamedValues {

    private final Map<String, NamedValue> map;

    public NamedValuesImpl(LogProvider logProvider,
                           Resources resources,
                           URL propertiesLocation) {
        this(logProvider,
                resources.properties(propertiesLocation),
                propertiesLocation.toString());
    }

    public NamedValuesImpl(LogProvider logProvider,
                           Properties properties,
                           String propertiesOrigin) {

        map = toNamedValuesMap(properties);

        final Log log = logProvider.getLog(getClass());
        log.as(info, "Settings loaded from {}", propertiesOrigin);
        log.as(info, "Number of name/value pairs: {}", map.size());
    }

    @Override
    public NamedValue get(String name) {
        return map.get(name);
    }

    @Override
    public Iterable<NamedValue> all() {
        return map.values();
    }

    @Override
    public Iterable<NamedValue> matches(Predicate<NamedValue> condition) {
        final List<NamedValue> list = new LinkedList<>();
        for (NamedValue s : all()) {
            if (condition.isTrueFor(s)) {
                list.add(s);
            }
        }
        return list;
    }

    private static Map<String, NamedValue> toNamedValuesMap(Properties properties) {
        final Map<String, NamedValue> map = new HashMap<>(properties.size());
        for (Entry<?, ?> e : properties.entrySet()) {
            final String name = e.getKey().toString();
            final String value = e.getValue().toString();
            map.put(name, new NamedValueImpl(name, value));
        }
        return map;
    }
}
