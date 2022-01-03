package lang;

import java.util.Map;
import java.util.Properties;

public class PropertyEnvSample {

    public static void main(String[] args) {
        Map<String, String> env = System.getenv();
        print("Environment", env);
        Properties properties = System.getProperties();
        print("Properties", properties);
        // change 'user.name' property
        String propName = "user.home";
        System.out.println(System.getProperty(propName));
        System.setProperty(propName, "/tmp");
        System.out.println(System.getProperty(propName));
    }

    private static void print(String name, Map<?, ?> map) {
        System.out.println(name + " Size: " + map.size());
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            String pair = "[" + entry.getKey() + "=" + entry.getValue() + "]";
            System.out.println(pair);
        }
        System.out.println();
    }
}
