package io.github.centercode.sample;

public class ManifestSample {
    public static void main(String[] args) {
        if (1 == args.length && "--version".equals(args[0])) {
            printVersion();
        }
    }

    private static void printVersion() {
        Package aPackage = ManifestSample.class.getPackage();
        String version = aPackage.getImplementationVersion();
        System.out.println(version);
    }
}
