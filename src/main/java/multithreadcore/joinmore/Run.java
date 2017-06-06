package multithreadcore.joinmore;

import java.util.Iterator;

/**
 * join()方法后面的代码提前运行
 * Created by liur on 17-5-26.
 */
public class Run {
    public static void main(String[] args) {
        try {
            ThreadB b= new ThreadB();
            ThreadA a=new ThreadA(b);
            a.start();
            b.start();
            b.join(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main end "+System.currentTimeMillis());
    }
}
