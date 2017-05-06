package designpattern.Structural.Decorator;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * 一个应用需要在打桩前挖坑，打桩后钉上木板，还可能动态增加一些功能，如打桩后钉上架子。
 * 在这里方形柱就是（decoratee）,我们需要在decoratee上刷上油漆（功能）
 * Created by liur on 17-4-29.
 */
public class Decorator implements Work {
    private Work work;
    //额外的功能被打包在这个List中
    private ArrayList others = new ArrayList();

    //在构造器中使用组合new方式，引入work对象
    public Decorator(Work work){
        this.work = work;

        others.add("挖坑");
        others.add("钉木板");
    }
    @Override
    public void insert() {
        newMethod();
    }

    //在新方法中，我们需要在insert之前增加其他的方法，这里次序先后是由用户灵活指定的
    public void newMethod() {
        otherMethod();
        work.insert();
    }

    public void otherMethod(){
        ListIterator listIterator= others.listIterator();
        while (listIterator.hasNext()){
            System.out.println(listIterator.next()+" 正在进行");
        }
    }
}
