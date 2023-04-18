package plantuml.code;

import java.util.zip.Deflater;

public class CompressionZlib implements Compression {

    // ::comment when __CORE__
    private static boolean USE_ZOPFLI = false;
    // ::done
    private static final int COMPRESSION_LEVEL = 9;

    public byte[] compress(byte[] in) {
        // ::comment when __CORE__
        if (USE_ZOPFLI)
            return new CompressionZopfliZlib().compress(in);
        // ::done

        if (in.length == 0)
            return null;

        int len = in.length * 2;
        if (len < 1000)
            len = 1000;

        // Compress the bytes
        final Deflater compresser = new Deflater(COMPRESSION_LEVEL, true);
        compresser.setInput(in);
        compresser.finish();

        final byte[] output = new byte[len];
        final int compressedDataLength = compresser.deflate(output);
        if (compresser.finished() == false)
            return null;

        return copyArray(output, compressedDataLength);
    }

    private byte[] copyArray(final byte[] data, final int len) {
        final byte[] result = new byte[len];
        System.arraycopy(data, 0, result, 0, len);
        return result;
    }

}

