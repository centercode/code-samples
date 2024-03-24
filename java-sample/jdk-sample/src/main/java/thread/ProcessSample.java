package thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * https://stackoverflow.com/questions/5483830/process-waitfor-never-returns
 * https://stackoverflow.com/questions/14727579/
 */
public class ProcessSample {
    private static final Logger logger = LoggerFactory.getLogger(ProcessSample.class);

    static void killWhenTimeout(Process process) throws InterruptedException {
        process.waitFor(1, TimeUnit.MINUTES);
        for (int count = 1; process.isAlive(); count++) {
            process.destroy();
            if (!process.isAlive()) {
                return;
            }
            if (3 < count) {
                logger.error("Process {} timeout killed!", process);
                process.destroyForcibly();
                return;
            }
            TimeUnit.SECONDS.sleep(1);
            logger.error("Process {} timeout: {}", process, count);
        }
    }
}
