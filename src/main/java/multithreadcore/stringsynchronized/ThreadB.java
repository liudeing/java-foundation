package multithreadcore.stringsynchronized;

/**
 * Created by liur on 17-5-24.
 */
public class ThreadB extends Thread {
    private Service service;
    public ThreadB(Service service){
        super();
        this.service=service;
    }

    @Override
    public void run() {
        service.print("AA");
    }
}
