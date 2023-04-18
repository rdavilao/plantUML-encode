package plantuml.code;

import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;

public class TranscoderImpl implements Transcoder {

    static enum Format {
        UTF8, UPF9;
    }

    private final Compression compression;
    private final URLEncoder urlEncoder;
    private final StringCompressor stringCompressor;
    private final Format format;

    private TranscoderImpl(URLEncoder urlEncoder, StringCompressor stringCompressor, Compression compression,
                           Format format) {
        this.compression = compression;
        this.urlEncoder = urlEncoder;
        this.stringCompressor = stringCompressor;
        this.format = format;
    }

    public static Transcoder utf8(URLEncoder urlEncoder, StringCompressor stringCompressor, Compression compression) {
        return new TranscoderImpl(urlEncoder, stringCompressor, compression, Format.UTF8);
    }

    public String encode(String text) throws IOException {
        final String stringAnnoted = stringCompressor.compress(text);
        final byte[] data;
        // ::comment when __CORE__
        if (format == Format.UPF9)
            data = Upf9Encoder.getBytes(stringAnnoted);
        else
            // ::done
            data = stringAnnoted.getBytes(UTF_8);

        final byte[] compressedData = compression.compress(data);

        return urlEncoder.encode(compressedData);
    }
}

