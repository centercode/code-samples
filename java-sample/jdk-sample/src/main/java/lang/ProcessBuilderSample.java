package lang;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;

public class ProcessBuilderSample {
    public static void main(String[] args) throws IOException, InterruptedException {
        String name = ManagementFactory.getRuntimeMXBean().getName();
        System.out.println("Current process id: " + name);
        String[] cmd = {"/bin/bash", "-c", "echo shell process id:$$"};
        ProcessBuilder builder = new ProcessBuilder(cmd);
        builder.environment().put("name", "jack");
        builder.redirectErrorStream(true);
        Process process = builder.start();
        print(process.getInputStream());
        int code = process.waitFor();
        System.out.println("Shell exit code: " + code);
    }

    private static void print(InputStream inputStream) throws IOException {
        String line;
        try (InputStreamReader streamReader = new InputStreamReader(inputStream);
             BufferedReader in = new BufferedReader(streamReader)) {
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

}
