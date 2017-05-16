package concurrent.JdkFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created by liur on 17-4-26.
 */
public class FutureMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //构造FutureTask
        FutureTask<String> future = new FutureTask<String>(new RealData("a"));
        ExecutorService executor = Executors.newFixedThreadPool(1);

        //执行FutureTask,相当于client发出请求
        //在这里开启线程执行RealDate的call()操作
        executor.submit(future);
        System.out.println("请求完毕");

        try {
            //在这里用sleep代替额外的操作（用于处理其他业务逻辑）
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //相当于取得方法的返回值，如果没有依然会等待
        System.out.println("数据= "+future.get());
    }
}
