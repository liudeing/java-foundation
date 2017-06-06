package multithreadcore.produceconsumer;

/**
 * Created by liur on 17-5-24.
 */
public class ThreadC extends Thread {
    private Consumer c;
    public ThreadC(Consumer c){
        super();
        this.c=c;
    }

    @Override
    public void run() {
        while (true){
            c.getValue();
        }
    }
}
