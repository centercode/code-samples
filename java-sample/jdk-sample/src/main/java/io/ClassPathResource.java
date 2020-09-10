package io;

/**
 * http://codepub.cn/2015/04/22/How-to-load-resource-file-in-a-Jar-package-correctly/
 */
public class ClassPathResource {

    public void load() {
        // 1. 从加载该类的classLoader下获取;
        //    如果该类是由bootstrapClassLoader加载，
        //    那么该方法等效于ClassLoader.getSystemResource()
        getClass().getResource("/my.conf");

        // 2. 从此类所在的包下获取资源
        getClass().getResource("my.conf");

        // 3. 等同于2, 所有的ClassLoader路径都是绝对路径，
        //    不需要参数以/作为合法的路径开始符号
        getClass().getClassLoader().getResource("my.conf");
    }

}
