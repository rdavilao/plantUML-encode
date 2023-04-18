import plantuml.code.Transcoder;
import plantuml.code.TranscoderUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {

        if (args.length == 0) {
            System.err.println("No plantUml file provided");
        }

        String contenidoArchivo = null;

        try {
            contenidoArchivo = new String(Files.readAllBytes(Paths.get(args[0])), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String encoded = null;

        try {
            encoded = getTranscoder().encode(contenidoArchivo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        File file = new File("./plantUML-encoded.txt");

        FileWriter write = null;
        try {
            write = new FileWriter(file);
            write.write(encoded);
            write.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Transcoder getTranscoder() {
        return TranscoderUtil.getDefaultTranscoder();
    }
}