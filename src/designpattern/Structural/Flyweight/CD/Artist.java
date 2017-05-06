package Structural.Flyweight.CD;

/**
 * 歌唱家作为可共享的ConcreteFlyweight
 * Created by liur on 17-4-30.
 */
public class Artist {
    //内部状态
    private String name;

    //note that Artist is immutable;
    String getName(){
        return name;
    }

    Artist(String n){
        name=n;
    }
}
