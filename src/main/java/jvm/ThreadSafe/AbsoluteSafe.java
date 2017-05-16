package jvm.ThreadSafe;

import java.util.Vector;

/**
 * 利用线程安全的容器查看线程的绝对安全（不管运行时环境如何,调用者都不需要任何额外的同步措施）
 * 需在调用端做同步
 * Created by liur on 17-5-9.
 */
public class AbsoluteSafe {
    private static Vector<Integer> vector = new Vector<Integer>();

    public static void main(String[] args) {
        while (true){
            for (int i=0;i<10;i++){
                vector.add(i);
            }
            Thread removeThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    //要保证线程安全，必须对for循环加锁
                    for (int i=0;i<vector.size();i++){
                        vector.remove(i);
                    }
                }
            });
            Thread printThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    //要保证线程安全，必须对for循环加锁
                    for (int i=0;i<vector.size();i++){
                        System.out.println(vector.get(i));
                    }
                }
            });
            removeThread.start();
            printThread.start();

            //不要同时产生过多的线程,否则会导致操作系统假死;while没有大括号，后面一个分号前的语句就是。
            while(Thread.activeCount()>20);
        }
    }
}
