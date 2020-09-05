package thread;

public class DaemonDemo {

    public static void main(String[] args) {
        Thread t1 = new Thread("子线程1") {
            @Override
            public void run() {
                System.out.println(this.getName() +
                        "开始执行," +
                        (this.isDaemon() ? "我是守护线程" : "我是用户线程"));
                while (true) ;
            }
        };
        t1.setDaemon(true);
        t1.start();
        System.out.println("主线程结束");
    }
}