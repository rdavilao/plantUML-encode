package plantuml.preproc;

import plantuml.text.StringLocated;
import plantuml.utils.LineLocation;
import plantuml.utils.LineLocationImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
//import net.sourceforge.plantuml.utils.Log;

public class ReadLineReader implements ReadLine {

    private final BufferedReader br;
    private LineLocationImpl location;
    private final String description;

    private ReadLineReader(Reader reader, String description, LineLocation parent) {
        if (description == null)
            description = "?";

        this.br = new BufferedReader(reader);
        this.location = new LineLocationImpl(description, parent);
        this.description = description;
        //Log.info("Reading from " + description);
    }

    @Override
    public String toString() {
        return super.toString() + " " + description;
    }

    private ReadLineReader(Reader reader, String desc) {
        this(reader, desc, null);
    }

    public static ReadLine create(Reader reader, String description) {
        return new ReadLineReader(reader, description, null);
    }

    public static ReadLine create(Reader reader, String description, LineLocation parent) {
        return new ReadLineReader(reader, description, parent);
    }

    public StringLocated readLine() throws IOException {
        String s = br.readLine();
        location = location.oneLineRead();
        if (s == null)
            return null;

        // if (s.length() > LIMIT) {
        // Log.debug("Line truncated from " + s.length() + " to " + LIMIT);
        // s = s.substring(0, LIMIT);
        // }
        if (s.startsWith("\uFEFF"))
            s = s.substring(1);

        s = s.replace('\u2013', '-');
        // s = BackSlash.convertHiddenNewLine(s);
        // s = s.replace('\u00A0', ' ');
        // s = s.replace('\u201c', '\"');
        // s = s.replace('\u201d', '\"');
        // s = s.replace('\u00ab', '\"');
        // s = s.replace('\u00bb', '\"');
        // s = s.replace('\u2018', '\'');
        // s = s.replace('\u2019', '\'');
        // for (int i = 0; i < s.length(); i++) {
        // char c = s.charAt(i);
        // System.err.println("X " + Integer.toHexString((int) c) + " " + c);
        // }
        return new StringLocated(s, location);
    }

    public void close() throws IOException {
        br.close();
    }

}

