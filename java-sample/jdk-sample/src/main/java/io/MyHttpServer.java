package io;

import com.sun.net.httpserver.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;

/**
 * http://localhost:8080/greet
 */
public class MyHttpServer {

    private static Logger logger = LoggerFactory.getLogger(MyHttpServer.class);

    private HttpServer server;

    public static void main(String[] args) {
        MyHttpServer httpServer = new MyHttpServer();
        httpServer.start();
        Runtime.getRuntime().addShutdownHook(new Thread(httpServer::stop));
    }

    public void start() {
        try {
            //maxConnection=5
            server = HttpServer.create(new InetSocketAddress(8080), 5);
            server.createContext("/greet", exchange -> {
                //request
                URI uri = exchange.getRequestURI();
                logger.info("Request uri:" + uri);
                //response
                exchange.sendResponseHeaders(200, 0);
                try (OutputStream out = exchange.getResponseBody()) {
                    out.write("hello world".getBytes());
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