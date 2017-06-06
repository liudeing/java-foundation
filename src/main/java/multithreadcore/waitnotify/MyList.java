package multithreadcore.waitnotify;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liur on 17-5-22.
 */
public class MyList {
    private static List list=new ArrayList();
    public static void add(){
        list.add("anyString");
    }
    public static int size(){
        return list.size();
    }
}
