package org.guppy4j.text;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static java.lang.String.valueOf;
import static java.util.regex.Pattern.LITERAL;
import static java.util.regex.Pattern.compile;

/**
 * Splits String values using Pattern#split and a cached
 * regular expression Pattern object for each separator
 * character
 */
public class StringSplitterImpl implements StringSplitter {

    private final Map<Character, Pattern> patterns = new HashMap<>();

    @Override
    public String[] split(String s, char separator) {
        return getPattern(separator).split(s);
    }

    private Pattern getPattern(char separator) {
        final Pattern pattern = patterns.get(separator);
        if (pattern != null) {
            return pattern;
        } else {
            final Pattern newPattern = compile(valueOf(separator), LITERAL);
            patterns.put(separator, newPattern);
            return newPattern;
        }
    }
}
