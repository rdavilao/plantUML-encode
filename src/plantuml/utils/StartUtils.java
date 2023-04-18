package plantuml.utils;

public class StartUtils {

    public static final String PAUSE_PATTERN = "((?:\\W|\\<[^<>]*\\>)*)[@\\\\]unpause";

    public static String beforeStartUml(final String s) {
        boolean inside = false;
        for (int i = 0; i < s.length(); i++) {
            final String tmp = s.substring(i, s.length());
            if (startsWithSymbolAnd("start", tmp)) {
                return s.substring(0, i);
            }
            final String single = s.substring(i, i + 1);
            if (inside) {
                if (single.equals(">")) {
                    inside = false;
                }
                continue;
            }
            if (single.equals("<")) {
                inside = true;
            } else if (single.matches("[\\w~]")) {
                return null;
            }
        }
        return null;
    }

    public static boolean startsWithSymbolAnd(String value, final String tmp) {
        return tmp.startsWith("@" + value) || tmp.startsWith("\\" + value);
    }
}

