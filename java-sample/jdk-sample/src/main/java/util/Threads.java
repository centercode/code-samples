package util;

import java.util.concurrent.TimeUnit;

public class Threads {

    private Threads() {
    }

    public static void silentSleep(long timeout, TimeUnit unit) {
        try {
            Thread.sleep(unit.toMillis(timeout));
        } catch (InterruptedException ignored) {
        }
    }
}
