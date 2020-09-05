package jdk.io;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class IOUtils {

    public static String readString(InputStream input) throws IOException {
        InputStreamReader in = new InputStreamReader(input, StandardCharsets.UTF_8);
        StringBuilder sw = new StringBuilder();
        copy(in, sw);
        return sw.toString();
    }

    public static byte[] readBytes(InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        copy(input, output);
        return output.toByteArray();
    }

    public static long copy(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1 << 12];
        long count = 0;
        int n;
        while ((n = in.read(buffer)) != -1) {
            out.write(buffer, 0, n);
            count += n;
        }
        return count;
    }

    public static long copy(Reader in, Writer out) throws IOException {
        char[] buffer = new char[1 << 12];
        long count = 0;
        int n;
        while ((n = in.read(buffer)) != -1) {
            out.write(buffer, 0, n);
            count += n;
        }
        return count;
    }

    public static long copy(Reader in, StringBuilder out) throws IOException {
        char[] buffer = new char[1 << 12];
        long count = 0;
        int n;
        while ((n = in.read(buffer)) != -1) {
            out.append(buffer, 0, n);
            count += n;
        }
        return count;
    }
}
