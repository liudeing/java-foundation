package Behavioral.Strategy;

/**
 * Created by liur on 17-5-1.
 */
public class RepTempRuleTwo extends RepTempRule {
    @Override
    public void replace() throws Exception {
        newString = oldString.replaceFirst("aaa","ccc");
        System.out.println("this is replace two");
    }
}
