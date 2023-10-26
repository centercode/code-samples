package thread;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.locks.LockSupport;

public class ThreadPoolSampleTest {

    @Test
    public void execute() {
        ThreadPoolSample pool = new ThreadPoolSample(3, 5, 4);
        Assert.assertEquals(0, pool.getRunningSize());
        pool.submit(LockSupport::park);
        Assert.assertEquals(1, pool.getRunningSize());
        pool.submit(LockSupport::park);
        pool.submit(LockSupport::park);
        Assert.assertEquals(3, pool.getRunningSize());
        // enqueue
        pool.submit(LockSupport::park);
        Assert.assertEquals(3, pool.getRunningSize());
        pool.submit(LockSupport::park);
        pool.submit(LockSupport::park);
        pool.submit(LockSupport::park);
        Assert.assertEquals(3, pool.getRunningSize());
        // queue overflow
        pool.submit(LockSupport::park);
        Assert.assertEquals(4, pool.getRunningSize());
    }

}