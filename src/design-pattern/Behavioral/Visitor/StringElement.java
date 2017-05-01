package Behavioral.Visitor;

/**
 * 被访问的具体元素
 * Created by liur on 17-5-1.
 */
public class StringElement implements Visitable {
    private String value;

    public String getValue() {
        return value;
    }

    public StringElement(String string){
        this.value=string;
    }

    //定义accept的具体内容。这里是一句调用
    @Override
    public void accept(Visitor vistor) {
        vistor.visitString(this);
    }
}
