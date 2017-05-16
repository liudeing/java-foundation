package concurrent.Java8;

import java.util.Arrays;
import java.util.function.IntConsumer;

/**
 * Created by liur on 17-5-3.
 */
public class LambdaStart {
    static int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    public static void main(String[] args) {
        //传统方式
        for (int i : arr) {
            System.out.println(i);
        }

        //流对象，Inconsumer接口
        Arrays.stream(arr).forEach(new IntConsumer() {
            @Override
            public void accept(int value) {
                System.out.println(value);
            }
        });

        //简化，类弄推导
        Arrays.stream(arr).forEach((final int x) -> {
            System.out.println(x);
        });

        //直接使用
        Arrays.stream(arr).forEach((x) -> {
            System.out.println(x);
        });

        //省略花括号
        Arrays.stream(arr).forEach((x) -> System.out.println(x));

        //多个处理器的整合，依次调用
        IntConsumer outprintln = System.out::println;
        IntConsumer errprintln = System.err::println;
        Arrays.stream(arr).forEach(outprintln.andThen(errprintln));
    }
}
