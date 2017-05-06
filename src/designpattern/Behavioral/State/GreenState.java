package designpattern.Behavioral.State;

/**
 * Created by liur on 17-5-1.
 */
public class GreenState extends State {

    @Override
    public void handlePush(Context c) {
        c.state(new RedState());
    }

    @Override
    public void handlePull(Context c) {
        c.state(new BlueState());
    }

    @Override
    public String getColor() {
        return Color.green;
    }
}
