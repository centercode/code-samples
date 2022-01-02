package io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

public class IOUtils {

    private IOUtils() {
    }

    /**
     * Load class path file to String
     *
     * @param resourceName absolute path without leading slash
     */
    public static String loadString(String resourceName) throws IOException {
        byte[] data = loadBytes(resourceName);
        return new String(data, StandardCharsets.UTF_8);
    }

    /**
     * Load class path file to byte array
     *
     * @param resourceName absolute path without leading slash
     */
    public static byte[] loadBytes(String resourceName) throws IOException {
        InputStream in = ClassLoader.getSystemResourceAsStream(resourceName);
        return readBytes(in);
    }

    /**
     * Read String from InputStream
     */
    public static String readString(InputStream in) throws IOException {
        InputStreamReader reader = new InputStreamReader(in, StandardCharsets.UTF_8);
        StringBuilder sw = new StringBuilder();
        copy(reader, sw);
        return sw.toString();
    }

    /**
     * Read byte array from InputStream
     * Java 9 can be replaced by InputStream.readAllBytes() or ByteStreams.toByteArray(InputStream)
     */
    public static byte[] readBytes(InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        copy(input, output);
        return output.toByteArray();
    }

    /**
     * Copy bytes from an InputStream to an OutputStream.
     */
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

    /**
     * Copy chars from a Reader to a Writer.
     *
     * @return the number of characters copied
     */
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

    /**
     * Read chars from a Reader and append to a StringBuilder.
     *
     * @return the number of characters copied
     */
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
