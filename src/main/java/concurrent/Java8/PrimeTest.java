package concurrent.Java8;

import java.util.stream.IntStream;

/**
 * Created by liur on 17-5-3.
 */
public class PrimeTest {
    public static void main(String[] args) {
        //串行
        long i = IntStream.range(1,1000000).filter(PrimeUtil::isPrime).count();
        //并行,得到一个并行流
        long j = IntStream.range(1,1000000).parallel().filter(PrimeUtil::isPrime).count();
        System.out.println(i);
        System.out.println(j);
    }
}
