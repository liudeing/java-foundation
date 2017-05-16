package concurrent.Java8;

/**
 * 求质数,如果质数返回true,否则返回false
 * Created by liur on 17-5-3.
 */
public class PrimeUtil {
    public static boolean isPrime(int number) {
        int tmp = number;
        if (tmp < 2) {
            return false;
        }
        for (int i = 2; Math.sqrt(tmp) >= i; i++) {
            if (tmp % i == 0) {
                return false;
            }
        }
        return true;
    }

}
