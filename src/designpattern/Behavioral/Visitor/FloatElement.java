package designpattern.Behavioral.Visitor;

/**
 * Created by liur on 17-5-1.
 */
public class FloatElement implements Visitable {
    private Float value;

    public FloatElement(Float value) {
        this.value = value;
    }

    public Float getValue() {
        return value;
    }

    @Override
    public void accept(Visitor vistor) {
        vistor.visitFloat(this);
    }
}
