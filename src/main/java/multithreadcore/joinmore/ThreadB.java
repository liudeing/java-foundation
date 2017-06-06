package multithreadcore.joinmore;

/**
 * Created by liur on 17-5-26.
 */
public class ThreadB extends Thread {
    synchronized public void run(){
        try {
            System.out.println("bein b ThreadName=" + Thread.currentThread().getName() + " " + System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println("bein b ThreadName=" + Thread.currentThread().getName() + " " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
