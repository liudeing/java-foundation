package multithreadcore.extthread;

/**
 * 可重入锁，子类也可以调用父类的方法。
 * Created by liur on 17-5-21.
 */
public class Sub extends Main {
    synchronized public void operateISubMethod() {
        try {
            while (i > 0) {
                i--;
                System.out.println("sub print i= " + i);
                Thread.sleep(1000);
                this.operateIMainMethod();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
