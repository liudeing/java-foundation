package concurrent.JdkFuture;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created by liur on 17-4-26.
 */
public class FutureMain {
    public static void main(String[] args) {
        //构造FutureTask
        FutureTask<String> future = new FutureTask<String>(new RealData("a"));
        ExecutorService executor = Executors.newFixedThreadPool(1);

    }
}
