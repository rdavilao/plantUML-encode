package plantuml.code;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Upf9Decoder {

    static int decodeChar(InputStream is) throws IOException {
        final int read0 = is.read();
        if (read0 == -1) {
            return -1;
        }
        if (read0 == 0x0B) {
            final int read1 = is.read();
            if (read1 >= 0x80)
                return (char) read1;
            return (char) ((0xE0 << 8) + read1);
        }
        if (read0 == 0x0C) {
            final int read1 = is.read();
            final int read2 = is.read();
            return (char) ((read1 << 8) + read2);
        }
        if (read0 >= 0x01 && read0 <= 0x08) {
            final int read1 = is.read();
            return (char) ((read0 << 8) + read1);
        }
        if (read0 >= 0x80) {
            final int read1 = is.read();
            return (char) (((read0 - 0x60) << 8) + read1);
        }
        return (char) read0;
    }

    public static String decodeString(byte[] data, int length) throws IOException {
        final ByteArrayInputStream bais = new ByteArrayInputStream(data, 0, length);
        final StringBuilder result = new StringBuilder();
        int read;
        while ((read = decodeChar(bais)) != -1) {
            result.append((char) read);
        }
        bais.close();
        return result.toString();
    }

}

