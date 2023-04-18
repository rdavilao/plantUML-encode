package plantuml.code;

import java.io.IOException;

public class TranscoderSmart implements Transcoder {

    private final Transcoder zlib = TranscoderImpl.utf8(new AsciiEncoder(), new ArobaseStringCompressor(),
            new CompressionZlib());

    public String encode(String text) throws IOException {
        return zlib.encode(text);
    }
}

