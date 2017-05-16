package designpattern.Behavioral.Mediator;

/**
 * ,都需要双方提供一些共同接口,这种要求在Visitor Observer等模式中都是相同的.
 * Created by liur on 17-5-1.
 */
public class Colleague {
    private Mediator mediator;

    public Mediator getMediator() {
        return mediator;
    }

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }
}
