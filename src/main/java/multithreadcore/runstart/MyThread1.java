package multithreadcore.runstart;

/**
 * Created by liur on 17-5-20.
 */
public class MyThread1 extends Thread {
    @Override
    public void run() {
        try {
            System.out.println("run threadName=" + this.currentThread().getName() + " begin");
            Thread.sleep(2000);
            System.out.println("run threadName="+this.currentThread().getName()+" end");
        } catch (InterruptedException e) {

        }
    }
}
