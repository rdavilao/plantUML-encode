package plantuml.zopfli;

public class Buffer {

    byte[] data;
    int size;
    private int bp;

    Buffer() {
        data = new byte[65536];
    }

    public byte[] getData() {
        return data;
    }

    public byte[] getResult() {
        byte[] copy = new byte[size];
        System.arraycopy(data, 0, copy, 0, size);
        return copy;
    }

    void append(byte value) {
        if (size == data.length) {
            byte[] copy = new byte[size * 2];
            System.arraycopy(data, 0, copy, 0, size);
            data = copy;
        }
        data[size++] = value;
    }

    void addBits(int symbol, int length) {
        for (int i = 0; i < length; i++) {
            if (bp == 0) {
                append((byte) 0);
            }
            int bit = (symbol >> i) & 1;
            data[size - 1] |= bit << bp;
            bp = (bp + 1) & 7;
        }
    }

    void addHuffmanBits(int symbol, int length) {
        for (int i = 0; i < length; i++) {
            if (bp == 0) {
                append((byte) 0);
            }
            int bit = (symbol >> (length - i - 1)) & 1;
            data[size - 1] |= bit << bp;
            bp = (bp + 1) & 7;
        }
    }
}

