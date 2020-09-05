package object;

/**
 //    对于一个类而言，按照如下顺序执行：
 //    执行静态代码块
 //    执行构造代码块
 //    执行构造函数
 //    对于静态变量、静态初始化块、变量、初始化块、构造器，它们的初始化顺序依次是:
 //   （静态变量、静态初始化块）>（变量、初始化块）>构造器。
 //
 [静态代码块1] 初始化 <br>
 [静态代码块2] 初始化 <br>
 [main方法] 初始化 <br>
 &nbsp;&nbsp;    staticVariable1值:StaticVariable1 <br>
 [非静态代码块3] 初始化 <br>
 */
public class CodeBlockLoadingOrder {


    {
        printf("构造代码块3");
    }

    static {
        printf("静态代码块1");
        //// Illegal forward reference
        // printf(staticVariable1);
    }

    private static String staticVariable1 = "StaticVariable1";

    static {
        printf("静态代码块2");
    }

    public static void main(String[] args) {
        printf("main方法");
        System.out.printf("\tstaticVariable1值:%s\n", staticVariable1);
        CodeBlockLoadingOrder objectMain = new CodeBlockLoadingOrder();
    }
    private static void printf(String name) {
        System.out.printf("[%s] 初始化\n", name);
    }

}
