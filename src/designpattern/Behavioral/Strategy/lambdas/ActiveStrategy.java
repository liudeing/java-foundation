package designpattern.Behavioral.Strategy.lambdas;

/**
 * Created by liur on 17-5-1.
 */
public class ActiveStrategy implements Strategy {
    @Override
    public void performTask() {
        System.out.println("Perform task now!");
    }
}
