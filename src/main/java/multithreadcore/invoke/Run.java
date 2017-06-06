package multithreadcore.invoke;

/**
 * 多线程下，代码执行顺序与调用顺序无关
 * Created by liur on 17-5-11.
 */
public class Run {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
        System.out.println("运行结束！");
    }
}
