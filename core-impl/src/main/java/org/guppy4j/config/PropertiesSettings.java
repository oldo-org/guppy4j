package org.guppy4j.config;

import org.guppy4j.log.Log;
import org.guppy4j.log.LogProvider;
import org.guppy4j.logic.Predicate;

import java.io.IOException;
import java.io.InputStream;
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
public class PropertiesSettings implements Settings {

    private final Map<String, Setting> settingsMap;

    public PropertiesSettings(LogProvider logProvider,
                              URL propertiesLocation) {
        this(logProvider,
                loadProperties(propertiesLocation),
                propertiesLocation.toString());
    }

    public PropertiesSettings(LogProvider logProvider,
                              Properties properties,
                              String propertiesOrigin) {

        settingsMap = toSettingsMap(properties);

        final Log log = logProvider.getLog(getClass());
        log.as(info, "Settings loaded from {}", propertiesOrigin);
        log.as(info, "Number of name/value pairs: {}", settingsMap.size());
    }

    @Override
    public Setting get(String name) {
        return settingsMap.get(name);
    }

    @Override
    public Iterable<Setting> all() {
        return settingsMap.values();
    }

    @Override
    public Iterable<Setting> matches(Predicate<Setting> condition) {
        final List<Setting> list = new LinkedList<>();
        for (Setting s : all()) {
            if (condition.isTrueFor(s)) {
                list.add(s);
            }
        }
        return list;
    }

    private static Properties loadProperties(URL url) {
        final Properties properties = new Properties();
        try (InputStream stream = url.openStream()) {
            properties.load(stream);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return properties;
    }

    private static Map<String, Setting> toSettingsMap(Properties properties) {
        final Map<String, Setting> map = new HashMap<>(properties.size());
        for (Entry<Object, Object> entry : properties.entrySet()) {
            final String name = entry.getKey().toString();
            final String value = entry.getValue().toString();
            map.put(name, new SettingImpl(name, value));
        }
        return map;
    }
}
