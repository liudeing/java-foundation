package designpattern.Behavioral.Visitor;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by liur on 17-5-1.
 */
public class ConcreteVisitor implements Visitor {
    @Override
    public void visitString(StringElement stringElement) {
        System.out.println(""+stringElement.getValue()+"");
    }

    @Override
    public void visitFloat(FloatElement floatElement) {
        System.out.println(floatElement.getValue().toString()+"f");
    }

    //实现对Collection元素的访问
    @Override
    public void visitCollection(Collection collection) {
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()){
            Object o=iterator.next();
            if (o instanceof Visitable){
               ((Visitable) o).accept(this);
            }
        }
    }
}
