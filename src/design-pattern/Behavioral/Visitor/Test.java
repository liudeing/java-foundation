package Behavioral.Visitor;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by liur on 17-5-1.
 */
public class Test {
    public static void main(String[] args) {
        Visitor visitor = new ConcreteVisitor();

        StringElement stringE = new StringElement("I am a String");
        visitor.visitString(stringE);

        Collection list = new ArrayList();
        list.add(new StringElement("I am a String1"));
        list.add(new StringElement("I am a String2"));
        list.add(new FloatElement(new Float(12)));
        list.add(new StringElement("I am a String3"));
        visitor.visitCollection(list);
    }
}
