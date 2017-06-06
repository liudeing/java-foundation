package multithreadcore.jointest;

/**
 * Created by liur on 17-5-25.
 */
public class Test {
    public static void main(String[] args) {
        MyThread threadTest = new MyThread();
        threadTest.start();
        try {
            threadTest.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("在threadTest执行完成后再执行");
    }
}
