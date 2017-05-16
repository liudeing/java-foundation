package multithreadcore.t1;

/**
 * Created by liur on 17-5-11.
 */
public class MyThread extends Thread {
    @Override
    public void run() {
        super.run();
        System.out.println("MyThread");
    }
}
