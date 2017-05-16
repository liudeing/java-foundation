package concurrent.ThreadLocal;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 多线程下共享一个产生随机数
 * Created by liur on 17-5-5.
 */
public class MultiRandom {
    public static final int GEN_COUNT = 10000000;
    public static final int THREAD_COUNT = 4;
    static ExecutorService exe = Executors.newFixedThreadPool(THREAD_COUNT);
    public static Random rnd = new Random(123);

    public static ThreadLocal<Random> tnd = new ThreadLocal<Random>() {
        protected Random initialValue() {
            return new Random(123);
        }
    };

    /**
     * 1,共享一个Random(mode=0);2,多线程各分配一个Random（mode=1)
     */
    public static class RndTask implements Callable<Long> {
        private int mode = 0;

        public RndTask(int mode) {
            this.mode = mode;
        }

        public Random getRandom() {
            if (mode == 0) {
                return rnd;
            } else if (mode == 1) {
                return tnd.get();
            } else {
                return null;
            }
        }

        @Override
        public Long call() throws Exception {
            long b = System.currentTimeMillis();
            for (long i = 0; i < GEN_COUNT; i++) {
                getRandom().nextInt();
            }
            long e = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + " spend " + (e - b) + "ms");
            return e - b;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Future<Long>[] futs = new Future[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            futs[i] = exe.submit(new RndTask(0));
        }
        long totalTime1 = 0;
        for (int i = 0; i < THREAD_COUNT; i++) {
            totalTime1 += futs[i].get();
        }
        System.out.println("多线程访问一个Random实例：" + totalTime1 + "ms");

        //ThreadLocal方式
        for (int i = 0; i < THREAD_COUNT; i++) {
            futs[i] = exe.submit(new RndTask(1));
        }
        long totalTime2 = 0;
        for (int i = 0; i < THREAD_COUNT; i++) {
            totalTime2 += futs[i].get();
        }
        System.out.println("使用ThreadLocal包装的Random实例：" + totalTime2 + "ms");
        exe.shutdown();
    }
}
