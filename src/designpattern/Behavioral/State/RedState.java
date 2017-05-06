package designpattern.Behavioral.State;

/**
 * Created by liur on 17-5-1.
 */
public class RedState extends State {

    @Override
    public void handlePush(Context c) {
        c.state(new BlueState());
    }

    @Override
    public void handlePull(Context c) {
        c.state(new GreenState());
    }

    @Override
    public String getColor() {
        return Color.red;
    }
}
