package plantuml.code;

public class AsciiEncoder implements URLEncoder {

    // Temporary because of AsciiEncoderFinalZeros
    final static/* private */char encode6bit[] = new char[64];
    final static/* private */byte decode6bit[] = new byte[128];

    static {
        for (byte b = 0; b < 64; b++) {
            encode6bit[b] = encode6bit(b);
            decode6bit[encode6bit[b]] = b;
        }
    }

    public String encode(byte data[]) {
        if (data == null)
            return "";

        final StringBuilder result = new StringBuilder((data.length * 4 + 2) / 3);
        for (int i = 0; i < data.length; i += 3)
            append3bytes(result, data[i] & 0xFF, i + 1 < data.length ? data[i + 1] & 0xFF : 0,
                    i + 2 < data.length ? data[i + 2] & 0xFF : 0);

        return result.toString();
    }

    public static char encode6bit(byte b) {
        assert b >= 0 && b < 64;
        if (b < 10)
            return (char) ('0' + b);

        b -= 10;
        if (b < 26)
            return (char) ('A' + b);

        b -= 26;
        if (b < 26)
            return (char) ('a' + b);

        b -= 26;
        if (b == 0)
            return '-';

        if (b == 1) {
            return '_';
        }
        assert false;
        return '?';
    }

    private void append3bytes(StringBuilder sb, int b1, int b2, int b3) {
        final int c1 = b1 >> 2;
        final int c2 = ((b1 & 0x3) << 4) | (b2 >> 4);
        final int c3 = ((b2 & 0xF) << 2) | (b3 >> 6);
        final int c4 = b3 & 0x3F;
        sb.append(encode6bit[c1 & 0x3F]);
        sb.append(encode6bit[c2 & 0x3F]);
        sb.append(encode6bit[c3 & 0x3F]);
        sb.append(encode6bit[c4 & 0x3F]);
    }
}

