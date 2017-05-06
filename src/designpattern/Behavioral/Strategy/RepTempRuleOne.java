package Behavioral.Strategy;

/**
 * Created by liur on 17-5-1.
 */
public class RepTempRuleOne extends RepTempRule {
    @Override
    public void replace() throws Exception {
        newString = oldString.replaceFirst("aaa","bbbb");
        System.out.println("this is replace one");
    }
}
