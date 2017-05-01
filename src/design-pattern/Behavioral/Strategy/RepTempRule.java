package Behavioral.Strategy;

/**
 * 定义一些公用变量和方法
 * Created by liur on 17-5-1.
 */
public abstract class RepTempRule {
    protected String oldString="";
    public void setOldString(String oldString){
        this.oldString=oldString;
    }
    protected String newString="";

    public String getNewString() {
        return newString;
    }

    //代替的具体方法
    public abstract void replace() throws Exception;
}
