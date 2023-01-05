package io.github.centercode.spark;

import org.apache.spark.launcher.SparkLauncher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * reference: https://stackoverflow.com/questions/31754328
 */
public class SparkLauncherSample {

    public static void main(String[] args) throws IOException, InterruptedException {
        String sparkHome = System.getenv("SPARK_HOME");
        String exampleJarPath = sparkHome + "/examples/jars/spark-examples_2.11-2.4.0.jar";
        Process spark = new SparkLauncher()
                .setSparkHome(sparkHome)
                .setAppResource(exampleJarPath)
                .setMainClass("org.apache.spark.examples.SparkPi")
                .setMaster("local[*]")
                .launch();

        InputStreamReaderRunnable stdoutRunnable = new InputStreamReaderRunnable(spark.getInputStream(), "input");
        Thread inputThread = new Thread(stdoutRunnable, "LogStreamReader input");
        inputThread.start();

        InputStreamReaderRunnable stderrRunnable = new InputStreamReaderRunnable(spark.getErrorStream(), "error");
        Thread errorThread = new Thread(stderrRunnable, "LogStreamReader error");
        errorThread.start();

        System.out.println("Waiting for finish...");
        int exitCode = spark.waitFor();
        System.out.println("Finished! Exit code:" + exitCode);
    }

    public static class InputStreamReaderRunnable implements Runnable {

        private final BufferedReader reader;

        private final String name;

        public InputStreamReaderRunnable(InputStream is, String name) {
            this.reader = new BufferedReader(new InputStreamReader(is));
            this.name = name;
        }

        public void run() {
            System.out.println("InputStream thread '" + name + "' start:");
            try (BufferedReader readerTmp = reader) {
                String line;
                while ((line = readerTmp.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
