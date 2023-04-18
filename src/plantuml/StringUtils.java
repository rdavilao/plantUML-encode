package plantuml;

public class StringUtils {

    public static boolean endsWithBackslash(final String s) {
        return s.endsWith("\\") && s.endsWith("\\\\") == false;
    }

    public static String trin(String arg) {
        if (arg.length() == 0)
            return arg;

        return trinEndingInternal(arg, getPositionStartNonSpace(arg));
    }

    private static int getPositionStartNonSpace(String arg) {
        int i = 0;
        while (i < arg.length() && isSpaceOrTabOrNull(arg.charAt(i)))
            i++;

        return i;
    }

    private static String trinEndingInternal(String arg, int from) {
        int j = arg.length() - 1;
        while (j >= from && isSpaceOrTabOrNull(arg.charAt(j)))
            j--;

        if (from == 0 && j == arg.length() - 1)
            return arg;

        return arg.substring(from, j + 1);
    }

    private static boolean isSpaceOrTabOrNull(char c) {
        return c == ' ' || c == '\t' || c == '\r' || c == '\n' || c == '\0';
    }
}

