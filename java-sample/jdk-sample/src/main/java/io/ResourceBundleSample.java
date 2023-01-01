package io;

import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleSample {

    public static void main(String[] args) {
        ResourceBundleSample sample = new ResourceBundleSample();
        sample.get();
    }

    void get() {
        Locale[] locales = {new Locale("zh", "CN"), new Locale("en", "US")};
        for (Locale locale : locales) {
            System.out.println("locale: '" + locale + "'");
            ResourceBundle bundle = ResourceBundle.getBundle("sample", locale);
            System.out.println(bundle.getString("sample.k1"));
            System.out.println(Arrays.toString(bundle.getStringArray("sample")));
        }
    }

}
