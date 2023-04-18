package plantuml.regex;

import java.util.regex.Pattern;

public class Pattern2 {

    private final Pattern pattern;

    public Pattern2(Pattern pattern) {
        this.pattern = pattern;
    }

    public Matcher2 matcher(CharSequence input) {
        return Matcher2.build(pattern, input);
    }

}
