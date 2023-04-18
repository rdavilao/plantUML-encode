package plantuml.code;

public class ByteArray {

    private final byte data[];
    private final int length;

    private ByteArray(byte data[], int length) {
        this.data = data;
        this.length = length;
    }

    public static ByteArray from(byte[] input) {
        return new ByteArray(input, input.length);
    }

    public int length() {
        return length;
    }

}
