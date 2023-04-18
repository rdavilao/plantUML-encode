package plantuml.code;

public interface Compression {

    /**
     * Shrinks the given <code>in</code> array with length <code>len</code>
     *
     * @return a newly created array with the compressed data.
     */
    byte[] compress(final byte[] in);
}
