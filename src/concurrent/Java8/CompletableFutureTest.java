package concurrent.Java8;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 增强Future
 * Created by liur on 17-5-3.
 */
public class CompletableFutureTest {
    public static class AskThread implements Runnable {
        CompletableFuture<Integer> re = null;

        public AskThread(CompletableFuture<Integer> re) {
            this.re = re;
        }

        @Override
        public void run() {
            int myRe = 0;
            try {
                //阻塞
                myRe = re.get() * re.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            System.out.println(myRe);
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        final CompletableFuture<Integer> future = new CompletableFuture<>();
        new Thread(new AskThread(future)).start();
        //模拟长时间计算过程
        Thread.sleep(1000);
        //载入数据并标记为完成，告知完成结果
        future.complete(60);

        //异步
        final CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(()->calc(50));
        System.out.println(future1.get());

        //使用CompletableFuture的方法（实现了CompletionStage接口）
        CompletableFuture<Void> fu = CompletableFuture.supplyAsync(()->calc2(50))
                .thenCompose((i)->CompletableFuture.supplyAsync(()->calc2(i)))
                .thenApply((str)->"\""+str+"\"")
                .thenAccept(System.out::println);
        fu.get();

        //thenCombine()
    }

    public static Integer calc2(Integer para){
        return para/2;
    }

    /**
     * 异步调用
     */
    public static Integer calc(Integer para){
        try {
            //模拟一个长时间的执行
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return para*para;
    }


}
