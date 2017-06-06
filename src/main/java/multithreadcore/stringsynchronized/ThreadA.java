package multithreadcore.stringsynchronized;

/**
 * Created by liur on 17-5-24.
 */
public class ThreadA extends Thread {
    private Service service;
    public ThreadA(Service service){
        super();
        this.service=service;
    }

    @Override
    public void run() {
        //如果改在service.print(new Object())就持有不同的锁
        service.print("AA");
    }
}
