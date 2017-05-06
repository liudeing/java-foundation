package designpattern.Behavioral.Memento;

import java.io.File;

/**
 * Created by liur on 17-4-30.
 */
public class Originator {
    public int number;
    public File file;

    public Originator(){

    }

    //创建一个Memento
    public Memento getMemento(){
        return new Memento(this);
    }

    //恢复到一个原始值
    public void setMemento(Memento m){
        number=m.number;
        file=m.file;
    }
}
