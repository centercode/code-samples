package org.slf4j;

import java.time.Instant;

public class Logger {

    private final String className;

    private final boolean enableLineNumber = false;

    public Logger(String className) {
        this.className = className;
    }

    public void error(String message) {
        log("ERROR", message);
    }


    public void info(String message) {
        log("INFO", message);
    }

    private void log(String level, String message) {
        String threadName = Thread.currentThread().getName();
        String positionExpr = getLinePositionExpr();
        String log = Instant.now() + " [" + level + "] [" + positionExpr + "] [" + threadName + "]: " + message;
        System.out.println(log);
    }

    private String getLinePositionExpr() {
        if (enableLineNumber) {
            int lineNumber = getLineNumber();
            if (-1 < lineNumber) {
                return className + ":" + lineNumber;
            }
        }
        return className;
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
