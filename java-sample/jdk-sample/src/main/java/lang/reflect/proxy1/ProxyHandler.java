package lang.reflect.proxy1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyHandler implements InvocationHandler {

    public <T> T newInstance(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(
                clazz.getClassLoader(),
                new Class[]{clazz},
                this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        if (Object.class.equals(method.getDeclaringClass())) {
            try {
                return method.invoke(this, args);
            } catch (Throwable t) {
                System.err.println(t.getMessage());
            }
        }
        return new User((Integer) args[0], "zhangsan", 18);
    }
}
