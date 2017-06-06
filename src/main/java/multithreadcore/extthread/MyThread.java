package multithreadcore.extthread;

/**
 * Created by liur on 17-5-21.
 */
public class MyThread extends Thread {
    @Override
    public void run() {
        Service service = new Service();
        service.service1();
    }
}
