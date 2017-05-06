package Behavioral.Strategy;

/**
 * Created by liur on 17-5-1.
 */
public class Test {
    public static void main(String[] args) throws Exception {
        //使用第一套替代方案
        RepTempRuleSolve solver= new RepTempRuleSolve(new RepTempRuleOne());
        solver.getNewContext("a","b");

        //使用第二套替代方案
        solver=new RepTempRuleSolve(new RepTempRuleTwo());
        solver.getNewContext("a","b");
    }
}
