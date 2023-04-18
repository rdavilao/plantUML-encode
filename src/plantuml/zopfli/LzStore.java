package plantuml.zopfli;

final class LzStore {
    final char[] litLens;
    final char[] dists;
    int size;

    LzStore(final int maxBlockSize) {
        litLens = new char[maxBlockSize];
        dists = new char[maxBlockSize];
    }

    final void append(final char length, final char dist) {
        litLens[size] = length;
        dists[size++] = dist;
    }

    final void reset() {
        size = 0;
    }

    final void copy(final LzStore source) {
        size = source.size;
        System.arraycopy(source.litLens, 0, litLens, 0, size);
        System.arraycopy(source.dists, 0, dists, 0, size);
    }
}

