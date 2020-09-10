package io;

import com.sun.net.httpserver.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.StandardCharsets;

/**
 * kill -31 $pid [on MacOS]
 */
public class MyHttpServer {

    private static Logger logger = LoggerFactory.getLogger(MyHttpServer.class);

    private HttpServer server;

    public static void main(String[] args) {
        MyHttpServer httpServer = new MyHttpServer();
        httpServer.start();
    }

    public void start() {
        try {
            server = HttpServer.create(new InetSocketAddress(5555), 50);
            server.createContext("/demo", exchange -> {
                //request
                URI uri = exchange.getRequestURI();
                System.out.println("Access url:" + uri);
                //response
                exchange.sendResponseHeaders(200, 0);
                byte[] body = "hello world".getBytes(StandardCharsets.UTF_8);
                try (OutputStream out = exchange.getResponseBody()) {
                    out.write(body);
                }
            });
            server.start();
        } catch (IOException e) {
            logger.info(e.getMessage());
        }
    }

    private void stop() {
        server.stop(1);
    }
}