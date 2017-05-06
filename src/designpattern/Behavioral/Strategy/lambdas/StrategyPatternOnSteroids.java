package designpattern.Behavioral.Strategy.lambdas;

import java.util.Arrays;
import java.util.List;

/**
 * 策略模式的lambda实现,就不需要为策略接口创建不同的子类了。
 * Created by liur on 17-5-1.
 */
public class StrategyPatternOnSteroids {
    public static void main(String[] args) {
        System.out.println("Strategy pattern on Steroids");
        List<Strategy> strategies = Arrays.asList(
                ()->{
                    System.out.println("Pattern task a day before deadline!");
                },
                ()->{
                    System.out.println("Pattern task now!");
                }
        );
        strategies.forEach((elem)->elem.performTask());
    }
}
