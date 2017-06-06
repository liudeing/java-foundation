package multithreadcore.jointest;

/**
 * Created by liur on 17-5-25.
 */
public class MyThread extends Thread {
    @Override
    public void run() {
        super.run();
        int secondValue = (int) (Math.random()*10000);
        System.out.println(secondValue);
        try {
            Thread.sleep(secondValue);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
