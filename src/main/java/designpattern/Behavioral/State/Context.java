package designpattern.Behavioral.State;

/**
 * state manager
 * Created by liur on 17-5-1.
 */
public class Context {
    private State state=null;
    public void state(State state) {
        this.state=state;
    }

    //用来改变state的状态，使用setState实现状态的切换
    public void setState(State state){
        this.state = state;
    }

    public void push(){
        //状态切换的细节，已经封装在子类的handlepush中实现
        state.handlePush(this);
        Sample sample = new Sample(state.getColor());
        sample.operate();
    }

    public void pull(){
        state.handlePull(this);
        Sample2 sample2 = new Sample2(state.getColor());
        sample2.operate();
    }

}
