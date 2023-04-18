package plantuml.zopfli;

public class Options {
    public static enum BlockSplitting {
        FIRST, LAST, NONE
    }

    public static enum OutputFormat {
        DEFLATE, GZIP, ZLIB
    }

    public final int numIterations;
    public final BlockSplitting blockSplitting;
    public final OutputFormat outputType;

    public Options(OutputFormat outputType, BlockSplitting blockSplitting, int numIterations) {
        this.outputType = outputType;
        this.blockSplitting = blockSplitting;
        this.numIterations = numIterations;
    }
}

