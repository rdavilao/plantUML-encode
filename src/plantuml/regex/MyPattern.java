package plantuml.regex;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

class MaxSizeHashMap<K, V> extends LinkedHashMap<K, V> {
    private final int maxSize;

    public MaxSizeHashMap(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > maxSize;
    }
}

// Splitter.java to be finished
public abstract class MyPattern {

    private static final Map<String, Pattern2> cache = new MaxSizeHashMap<>(512);

    private static final Pattern2 EMPTY = new Pattern2(Pattern.compile(""));

//	static int CPT1;
//	static int CPT2;

    public static Pattern2 cmpile(final String p) {
        if (p == null || p.length() == 0) {
            return EMPTY;
        }
//		CPT1++;
        Pattern2 result = null;
        synchronized (cache) {
            result = cache.get(p);
            if (result != null) {
                return result;
            }
        }
        assert result == null;
        result = new Pattern2(Pattern.compile(transform(p), Pattern.CASE_INSENSITIVE));

        synchronized (cache) {
            cache.put(p, result);
//			CPT2++;
//			System.err.println("CPT= " + CPT1 + " / " + CPT2 + " " + cache.size());
        }

        return result;
    }

    private static String transform(String p) {
        // Replace ReadLineReader.java
        p = p.replace("%pLN", "\\p{L}0-9"); // Unicode Letter, digit
        p = p.replace("%s", "\\s\u00A0"); // space
        p = p.replace("%q", "'\u2018\u2019"); // quote
        p = p.replace("%g", "\"\u201c\u201d"); // double quote
        return p;
    }
}
