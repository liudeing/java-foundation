package concurrent.ConcurrentSearch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 定义一个整形数组，需要查找其中的数据，定义线程池，线程数量以及存放结果的变量result，
 * 找到符合条件的数据的数组下标，默认为-1,表示没有找到指定的元素
 * Created by liur on 17-5-2.
 */
public class ArraySearch {
    static int arr[];

    static ExecutorService pool = Executors.newCachedThreadPool();
    static final int Thread_Num = 2;
    static AtomicInteger result = new AtomicInteger(-1);

    public static int search(int searchValue, int beginPos, int endPos) {
        int i = 0;
        for (i = beginPos; i < endPos; i++) {
            //是否已有其他线程找到了结果
            if (result.get() >= 0) {
                return result.get();
            }

            //找到了需要的结果
            if (arr[i] == searchValue) {
                //如果设置失败，表示其他线程先找到了，CAS
                if (!result.compareAndSet(-1, i)) {
                    return result.get();
                }
                return i;
            }
        }
        return -1;
    }

    /**
     * 线程通过result共享彼此的信息，
     *
     * @param searchValue
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static int pSearch(int searchValue) throws ExecutionException, InterruptedException {
        int subArrSize = arr.length / Thread_Num + 1;
        List<Future<Integer>> re = new ArrayList<Future<Integer>>();
        //将原始数组分成若干段，并根据划分结果建立子任务，每一个子任务返回一个Future对象，
        // 通过Future对象可以获得线程组的最终结果。
        for (int i = 0; i < arr.length; i += subArrSize) {
            int end = i + subArrSize;
            if (end >= arr.length) {
                end = arr.length;
            }
            re.add(pool.submit(new SearchTask(searchValue, i, end)));
        }

        for (Future<Integer> fu : re) {
            if (fu.get() >= 0) {
                return fu.get();
            }
        }
        return -1;
    }
}
