package security;

import org.junit.Assert;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;

public class ChecksumSampleTest {

    ChecksumSample sample = new ChecksumSample();

    @Test
    public void sha1sum() throws NoSuchAlgorithmException {
        byte[] input = {};
        String s = sample.sha1sum(input, 0, input.length);
        Assert.assertEquals("da39a3ee5e6b4b0d3255bfef95601890afd80709", s);
    }

    @Test
    public void sha256sum() throws NoSuchAlgorithmException {
        byte[] input = {};
        String s = sample.sha256sum(input, 0, input.length);
        Assert.assertEquals("e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855", s);
    }

    @Test
    public void md5sum() throws NoSuchAlgorithmException {
        byte[] input = {};
        String s = sample.md5sum(input, 0, input.length);
        Assert.assertEquals("d41d8cd98f00b204e9800998ecf8427e", s);
    }
}