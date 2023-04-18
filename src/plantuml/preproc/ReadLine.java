package plantuml.preproc;

import plantuml.text.StringLocated;

import java.io.Closeable;
import java.io.IOException;

public interface ReadLine extends Closeable {

    public StringLocated readLine() throws IOException;
}
