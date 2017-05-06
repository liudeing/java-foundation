package Behavioral.State;

/**
 * 父类中要有对应的state manager中的开关行为，还要有获取结果的方法
 * Created by liur on 17-5-1.
 */
public abstract class State {
    public abstract void handlePush(Context c);
    public abstract void handlePull(Context c);
    public abstract String getColor();
}
