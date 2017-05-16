package designpattern.Creational.Builder;

/**
 * 构建一个复杂对象
 * Created by liur on 17-4-28.
 */
public class Director {
    private Builder builder;

    public Director (Builder builder){
        this.builder=builder;
    }

    /**
     * 将部件A,B,C组建成复杂对象
     */
    public void construct(){
        builder.buildPartA();
        builder.buildPartB();
        builder.buildPartC();
    }
}
