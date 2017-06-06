package multithreadcore.produceconsumer;

/**
 * Created by liur on 17-5-24.
 */
public class ThreadP extends Thread {
    private Produce p;
    public ThreadP(Produce p){
        super();
        this.p=p;
    }

    @Override
    public void run() {
        while (true){
            p.setValue();
        }
    }
}
