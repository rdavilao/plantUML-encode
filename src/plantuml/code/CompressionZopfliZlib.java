package plantuml.code;

import plantuml.zopfli.Options;
import plantuml.zopfli.Options.BlockSplitting;
import plantuml.zopfli.Options.OutputFormat;
import plantuml.zopfli.Zopfli;

public class CompressionZopfliZlib implements Compression {
    // ::remove file when __CORE__

    public byte[] compress(byte[] in) {
        if (in.length == 0)
            return null;

        int len = in.length * 2;
        if (len < 100)
            len = 100;

        final Zopfli compressor = new Zopfli(len);
        final Options options = new Options(OutputFormat.DEFLATE, BlockSplitting.FIRST, 30);

        return compressor.compress(options, in).getResult();
    }
}

