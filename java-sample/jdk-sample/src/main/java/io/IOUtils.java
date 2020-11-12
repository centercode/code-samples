package io;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class IOUtils {

    public static String readString(InputStream in) throws IOException {
        InputStreamReader reader = new InputStreamReader(in, StandardCharsets.UTF_8);
        StringBuilder sw = new StringBuilder();
        copy(reader, sw);
        return sw.toString();
    }

    // Java 9: ByteStreams.toByteArray(InputStream)
    //         InputStream.readAllBytes()
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

    public static long copy(Reader reader, Writer out) throws IOException {
        char[] buffer = new char[1 << 12];
        long count = 0;
        int n;
        while ((n = reader.read(buffer)) != -1) {
            out.write(buffer, 0, n);
            count += n;
        }
        return count;
    }

    public static long copy(Reader reader, StringBuilder out) throws IOException {
        char[] buffer = new char[1 << 12];
        long count = 0;
        int n;
        while ((n = reader.read(buffer)) != -1) {
            out.append(buffer, 0, n);
            count += n;
        }
        return count;
    }
}
