package designpattern.Structural.Flyweight.CD;

import java.util.Hashtable;

/**
 * 专门用来制造上面的可共享的ConcreteFlyweight:artist
 * Created by liur on 17-4-30.
 */
public class ArtistFactory {
    Hashtable pool = new Hashtable();

    Artist getArtist(String key){
        Artist result;
        result = (Artist) pool.get(key);

        if (result==null){
            result=new Artist(key);
            pool.put(key,result);
        }
        return result;
    }
}
