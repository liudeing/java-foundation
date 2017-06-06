package multithreadcore.objectlock;

/**
 * Created by liur on 17-5-20.
 */
public class ThreadB extends Thread {
    private MyObject object;
    public ThreadB(MyObject object){
        super();
        this.object=object;
    }

    @Override
    public void run() {
        super.run();
        object.methodA();
    }
}
