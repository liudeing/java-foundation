package core.LRU;

/**
 * Created by liur on 17-5-15.
 */
public class Node {
    int key,value;
    Node pre,next;

    public Node(int key,int value){
        this.key=key;
        this.value=value;
    }
}
