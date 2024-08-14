package lang;

/**
 * Reference：https://www.cnblogs.com/GrimMjx/p/10105626.html
 */
public class OuterClass {

    private static int FOO = 1;

    private int bar;

    public static void main(String[] args) {
        OuterClass outerInstance = new OuterClass();
        // 'lang.OuterClass.this' cannot be referenced from a static context
        // new InnerClass();
        InnerStaticClass innerStaticInstance = new InnerStaticClass();
        innerStaticInstance.inner(outerInstance);
    }

    /**
     * 非静态内部类可以直接访问外部类的实例变量、类变量、实例方法、类方法。
     * 非静态内部类对象总有一个隐式引用，指向了创建它的外部类对象。
     */
    class InnerClass {

        // 非静态内部类不允许定义静态成员。
        // static declarations in inner classes are not supported at language level '8'
        // private static int INNER_FOO = 1;

        private int innerBar;

        /**
         * 如果非静态内部类方法访问某个变量，其顺序为：
         * 该方法是否有该名字的成员变量 - 直接用该变量名
         * 内部类中是否有该名字的成员变量 - 使用this.变量名
         * 外部类中是否有该名字的成员变量 - 使用外部类的类名.this.变量名
         */
        void inner(OuterClass outerInstance) {
            //// InnerClass can access OuterClass's private property.
            System.out.println(OuterClass.FOO);
            System.out.println(OuterClass.this.bar);
            System.out.println(outerInstance.bar);
        }
    }

    static class InnerStaticClass {

        private static int INNER_FOO = 1;

        private int innerBar;

        void inner(OuterClass instance) {
            System.out.println(OuterClass.FOO);
            // 静态内部类不能访问外部类的实例成员
            // System.out.println(OuterClass.this.bar);
            System.out.println(instance.bar);
        }
    }

    void outer() {
        InnerClass innerInstance = new InnerClass();
        InnerStaticClass innerStaticInstance = new InnerStaticClass();
        //// OuterClass can access InnerClass's private property.
        System.out.println(innerInstance.innerBar);
        System.out.println(innerStaticInstance.innerBar);
        System.out.println(InnerStaticClass.INNER_FOO);
    }

    static void staticOuter() {
        // 外部类的静态方法不能访问非静态内部类。
        // 'lang.OuterClass.this' cannot be referenced from a static context
        // new InnerClass();
    }
}
