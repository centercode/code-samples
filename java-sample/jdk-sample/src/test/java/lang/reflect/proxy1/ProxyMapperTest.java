package lang.reflect.proxy1;

import org.junit.Test;

public class ProxyMapperTest {

    @Test
    public void invoke() {
        ProxyHandler proxy = new ProxyHandler();
        UserMapper mapper = proxy.newInstance(UserMapper.class);
        User user = mapper.getUserById(1001);
        System.out.println("id:" + user.getId());
        System.out.println("name:" + user.getName());
        System.out.println("age:" + user.getAge());
        System.out.println(mapper.toString());
    }
}