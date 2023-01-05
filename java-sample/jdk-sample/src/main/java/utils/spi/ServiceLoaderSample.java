package utils.spi;

import java.util.Iterator;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;

public class ServiceLoaderSample {
    public static void main(String[] args) {
        ServiceLoader<Facade> loader = ServiceLoader.load(Facade.class);
        Iterator<Facade> it = loader.iterator();
        while (it.hasNext()) {
            try {
                // iterate ServiceLoader using for-each is deprecated,
                // for it.next() maybe throw ServiceConfigurationError
                Facade facade = it.next();
                System.out.println(facade.getClass());
            } catch (ServiceConfigurationError e) {
                e.printStackTrace();
            }
        }
    }
}
