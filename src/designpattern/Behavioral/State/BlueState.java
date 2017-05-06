package designpattern.Behavioral.State;

/**
 * Created by liur on 17-5-1.
 */
public class BlueState extends State {
    @Override
    public void handlePush(Context c) {
        //根据push方法“如果是blue状态就切换到green”
        c.state(new GreenState());
    }

    @Override
    public void handlePull(Context c) {
        //根据pull方法“如果是blue状态就切换到red”
        c.state(new RedState());
    }

    @Override
    public String getColor() {
        return Color.blue;
    }
}
