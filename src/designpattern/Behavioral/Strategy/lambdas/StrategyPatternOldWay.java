package Behavioral.Strategy.lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 策略模式的调用(传统实现)
 * Created by liur on 17-5-1.
 */
public class StrategyPatternOldWay {
    public static void main(String[] args) {
        List<Strategy> strategies = Arrays.asList(new LazyStrategy(),new ActiveStrategy());

        for (Strategy stg : strategies){
            stg.performTask();
        }
    }
}
