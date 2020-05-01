package org.slf4j;

import java.time.Instant;

public class Logger {

    private String name;

    public Logger(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void info(String message) {
        System.out.println(Instant.now() + "," + name + ":" + message);
    }
}
