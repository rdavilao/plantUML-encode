package plantuml.text;

import plantuml.utils.LineLocation;

import java.util.Objects;

final public class StringLocated {
    private final String s;
    private final LineLocation location;
    private final String preprocessorError;

    public StringLocated(String s, LineLocation location) {
        this(s, location, null);
    }

    public StringLocated(String s, LineLocation location, String preprocessorError) {
        this.s = Objects.requireNonNull(s);
        this.location = location;
        this.preprocessorError = preprocessorError;
    }

    @Override
    public String toString() {
        return s;
    }

    public StringLocated append(String endOfLine) {
        return new StringLocated(s + endOfLine, location, preprocessorError);
    }

    public StringLocated substring(int start, int end) {
        return new StringLocated(this.getString().substring(start, end), this.getLocation(),
                this.getPreprocessorError());
    }

    public String getString() {
        return s;
    }

    public LineLocation getLocation() {
        return location;
    }

    public String getPreprocessorError() {
        return preprocessorError;
    }

    public int length() {
        return s.length();
    }

}
