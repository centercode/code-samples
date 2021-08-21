package lang;

public class MyClassLoader extends ClassLoader {

    /**
     * 遵循双亲委派模型
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }
}
