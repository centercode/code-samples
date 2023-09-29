package lang;

import java.nio.charset.Charset;

/**
 * See more assertions in UnicodeSampleTest.java.
 */
public class UnicodeSample {

    public static Charset UTF_32LE = Charset.forName("UTF_32LE");

    public static Charset UTF_32BE = Charset.forName("UTF_32BE");

    /**
     * convert a unicode codepoint to java.lang.String.
     */
    public static String toString(int codepoint) {
        return new String(Character.toChars(codepoint));
    }

    /**
     * convert a codepoint to String and get bytes by specified charset.
     */
    public static byte[] toBytes(int codepoint, Charset charset) {
        return toString(codepoint).getBytes(charset);
    }

}
