package multithreadcore.produceconsumer;

/**
 * wait/notify生产者/消费者模式
 * Created by liur on 17-5-24.
 */
public class Run {
    public static void main(String[] args) {
        String lock =new String("");
        Produce p = new Produce(lock);
        Consumer c= new Consumer(lock);
        ThreadP threadP = new ThreadP(p);
        ThreadC threadC = new ThreadC(c);
        threadP.start();
        threadC.start();
    }
}
