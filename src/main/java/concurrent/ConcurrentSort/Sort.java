package concurrent.ConcurrentSort;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by liur on 17-5-2.
 */
public class Sort {
    static int arr[];
    static ExecutorService pool = Executors.newCachedThreadPool();

    /**
     * 串条的冒泡算法，相邻比较，小的逐一上浮，大的下沉。
     *
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        for (int i = arr.length - 1; i > 0; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j] + 1) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 奇偶交换排序
     *
     * @param arr
     */
    public static void oddEventSort(int[] arr) {
        //记录当前迭代是否发生了交换
        int exchFlag = 1;
        //记录是奇交换还是偶交换，初始为0表示是偶交换，每次迭代结束后，切换start状态。
        int start = 0;
        while (exchFlag == 1 || start == 1) {
            exchFlag = 0;
            for (int i = start; i < arr.length - 1; i += 2) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    exchFlag = 1;
                }
            }
            if (start == 0) {
                start = 1;
            } else {
                start = 0;
            }
        }
    }

    /**
     * 并发奇偶排序
     */
    static int exchFlag = 1;

    static synchronized void setExchFlag(int v) {
        exchFlag = v;
    }

    static synchronized int getExchFlag() {
        return exchFlag;
    }

    public static class OddEvenSortTask implements Runnable {
        int i;
        CountDownLatch latch;

        public OddEvenSortTask(int i, CountDownLatch latch) {
            this.i = i;
            this.latch = latch;
        }

        @Override
        public void run() {
            if (arr[i] > arr[i + 1]) {
                int temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
                setExchFlag(1);
            }
            latch.countDown();
        }
    }

    public static void pOddEvenSort(int[] arr) throws InterruptedException {
        int start = 0;
        while (getExchFlag() == 1 || start == 1) {
            setExchFlag(0);
            //偶数的数组长度，当start为1时，只有len/2-1个线程
            CountDownLatch latch = new CountDownLatch(arr.length / 2 - (arr.length % 2 == 0 ? start : 0));
            for (int i = start; i < arr.length - 1; i += 2) {
                pool.submit(new OddEvenSortTask(i, latch));
            }
            //等待所有线程结束
            latch.await();
            if (start == 0) {
                start = 1;
            } else {
                start = 0;
            }
        }
    }

    /**
     * 插入排序
     */
    public static void insertSort(int[] arr) {
        int length = arr.length;
        int j, i, key;
        for (i = 1; i < length; i++) {
            //key为准备要插入的元素
            key = arr[i];
            j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            //找到合适的位置 插入key
            arr[j + 1] = key;
        }
    }

    /**
     * 希尔排序
     *
     * @param arr
     */
    public static void shellSort(int[] arr) {
        //计算出最大的h值
        int h = 1;
        while (h <= arr.length / 3) {
            h = h * 3 + 1;
        }
        while (h > 0) {
            //进行间隔为h的插入排序
            for (int i = h; i < arr.length; i++) {
                if (arr[i] < arr[i - h]) {
                    int tmp = arr[i];
                    int j = i - h;
                    while (j >= 0 && arr[j] > tmp) {
                        arr[j + h] = arr[j];
                        j -= h;
                    }
                    arr[j + h] = tmp;
                }
            }
            //计算下一个h值
            h = (h - 1) / 3;
        }
    }

    /**
     * shell排序的并行算法
     * 给定起始位置和h,对子数组进行排序
     */
    public static class ShellSortTask implements Runnable {
        int i = 0;
        int h = 0;
        CountDownLatch latch;

        public ShellSortTask(int i, int h, CountDownLatch latch) {
            this.i = i;
            this.h = h;
            this.latch = latch;
        }

        @Override
        public void run() {
            if (arr[i] < arr[i - h]) {
                int tmp = arr[i];
                int j = i - h;
                while (j >= 0 && arr[j] > tmp) {
                    arr[j + h] = arr[j];
                    j -= h;
                }
                arr[j + h] = tmp;
            }
            latch.countDown();
        }
    }

    public static void pShellSort(int[] arr) throws InterruptedException {
        //计算出最大值
        int h = 1;
        CountDownLatch latch = null;
        while (h < arr.length / 3) {
            h = h * 3 + 1;
        }
        while (h > 0) {
            System.out.println("h=" + h);
            //h大于或等于4时用并行线程
            if (h >= 4) {
                latch = new CountDownLatch(arr.length - h);
            }
            for (int i = h; i < arr.length; i++) {
                if (h >= 4) {
                    pool.execute(new ShellSortTask(i, h, latch));
                } else {
                    if (arr[i] < arr[i - h]) {
                        int tmp = arr[i];
                        int j = i - h;
                        while (j >= 0 && arr[j] > tmp) {
                            arr[j + h] = arr[j];
                            j -= h;
                        }
                        arr[j + h] = tmp;
                    }
                }
            }
            //等待线程排序完成，进入下一次排序
            latch.wait();
            //计算下一个h值
            h = (h - 1) / 3;
        }
    }

}
