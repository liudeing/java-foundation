package designpattern.Behavioral.Memento;

import java.io.File;
import java.io.Serializable;

/**
 * Created by liur on 17-4-30.
 */
public class Memento implements Serializable{
    public int number;
    public File file =null;

    public Memento(Originator o){
        number=o.number;
        file=o.file;
    }

}
