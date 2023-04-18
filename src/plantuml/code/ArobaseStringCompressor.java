package plantuml.code;

import plantuml.StringUtils;
import plantuml.preproc.ReadLine;
import plantuml.preproc.ReadLineReader;
import plantuml.preproc.UncommentReadLine;
import plantuml.regex.Matcher2;
import plantuml.regex.MyPattern;
import plantuml.regex.Pattern2;
import plantuml.text.StringLocated;

import java.io.IOException;
import java.io.StringReader;

public class ArobaseStringCompressor implements StringCompressor {

    private final static Pattern2 pattern = MyPattern
            .cmpile("(?s)^[%s]*(@startuml[^\\n\\r]*)?[%s]*(.*?)[%s]*(@enduml)?[%s]*$");

    public String compress(final String data) throws IOException {
        final ReadLine r = new UncommentReadLine(ReadLineReader.create(new StringReader(data), "COMPRESS"));
        final StringBuilder sb = new StringBuilder();
        final StringBuilder full = new StringBuilder();
        StringLocated s = null;
        boolean startDone = false;
        while ((s = r.readLine()) != null) {
            append(full, s);
            if (s.getString().startsWith("@startuml")) {
                startDone = true;
            } else if (s.getString().startsWith("@enduml")) {
                return sb.toString();
            } else if (startDone) {
                append(sb, s);
            }
        }
        if (startDone == false) {
            return compressOld(full.toString());
        }
        return sb.toString();
    }

    private void append(final StringBuilder sb, StringLocated s) {
        if (sb.length() > 0) {
            sb.append('\n');
        }
        sb.append(s.getString());
    }

    private String compressOld(String s) throws IOException {
        final Matcher2 m = pattern.matcher(s);
        if (m.find()) {
            return clean(m.group(2));
        }
        return "";
    }

    private String clean(String s) {
        // s = s.replace("\0", "");
        s = StringUtils.trin(s);
        s = clean1(s);
        s = s.replaceAll("@enduml[^\\n\\r]*", "");
        s = s.replaceAll("@startuml[^\\n\\r]*", "");
        s = StringUtils.trin(s);
        return s;
    }

    private String clean1(String s) {
        final Matcher2 m = pattern.matcher(s);
        if (m.matches())
            return m.group(2);

        return s;
    }

}
