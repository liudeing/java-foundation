package concurrent.ThreadLocal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ThreadLocal,只有当前线程可以访问，线程安全的,
 * 为每一个线程分配一个对象是要在应用层级实现，ThreadLocal只是一个容器。
 * Created by liur on 17-5-5.
 */
public class TestThreadLocal {
    //非线程安全
//    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    static ThreadLocal<SimpleDateFormat> t1 = new ThreadLocal<SimpleDateFormat>();

    public static class ParseDate implements Runnable {
        int i = 0;

        public ParseDate(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            try {
                //用ThreadLocal代替
                if (t1.get() == null) {
                    t1.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
                }
                Date t = t1.get().parse("2015-03-29 19:29" + i % 60);
                System.out.println(i + ":" + t);
                //非线程安全的
//                Date t = sdf.parse("2015-03-29 19:29" + i % 60);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            es.execute(new ParseDate(i));
        }
    }
}
