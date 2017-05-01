package Behavioral.Strategy.lambdas;

/**
 * Created by liur on 17-5-1.
 */
public class LazyStrategy implements Strategy {
    @Override
    public void performTask() {
        System.out.println("Perform task a day before deadline!");
    }
}
