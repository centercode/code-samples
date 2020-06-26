package org.slf4j;

import java.time.Instant;

public class Logger {

    private final String name;

    public Logger(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void info(String message) {
        String threadName = Thread.currentThread().getName();
        int lineNumber = getLineNumber();
        String lineExpr = -1 >= lineNumber ? "" : ":" + lineNumber;
        System.out.println(Instant.now() + " [INFO] [" + threadName + "] [" + this.name + lineExpr + "]: " + message);
    }

    private int getLineNumber() {
        Throwable t = new Throwable();
        for (StackTraceElement e : t.getStackTrace()) {
            if (!e.getClassName().equals(this.getClass().getName())) {
                return e.getLineNumber();
            }
        }
        return -1;
    }
}
