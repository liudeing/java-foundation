package designpattern.Behavioral.Strategy;

/**
 * 算法解决类，以提供给客户端可以自由选择算法
 * Created by liur on 17-5-1.
 */
public class RepTempRuleSolve {
    private RepTempRule strategy;

    public RepTempRuleSolve(RepTempRule rule){
        this.strategy=rule;
    }

    public void getNewContext(String newString,String oldString) throws Exception {
        strategy.replace();
    }

    //新算法
    public void changeAlgorithm(RepTempRule newAlgorithm){
        strategy=newAlgorithm;
    }
}
