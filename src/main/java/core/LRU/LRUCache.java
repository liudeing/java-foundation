package core.LRU;

import java.util.HashMap;

/**
 * 双向链表加hashMap实现LRUCache
 * Created by liur on 17-5-15.
 */
public class LRUCache {
    int capacity;
    HashMap<Integer, Node> map = new HashMap<Integer, Node>();
    //先声明一个头结点和一个尾结点
    Node head = null;
    Node end = null;

    //初始化缓存大小，超过规定就得移除
    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    //获得一个数据后，应该所数据从当前位置移除，并添加到头位置中，这些都是在返回数据之前完成的
    public int get(int key) {
        if (map.containsKey(key)) {
            Node n = map.get(key);
            remove(n);
            setHead(n);
            return n.value;
        }
        return -1;
    }

    //移除的元素分为，N的前边和N的后边都要看是怎样的情况
    public void remove(Node n) {
        if (n.pre != null) {
            n.pre.next = n.next;
        } else {
            head = n.next;
        }

        if (n.next != null) {
            n.next.pre = n.pre;
            end = n.pre;
        }
    }

    public void setHead(Node n) {
        //head的原位置应该是指向第一个元素，现在把这个位置给n.next
        n.next = head;
        n.pre = null;

        if (head != null) head.pre = n;
        head = n;
        //判断头尾是否为空
        if (end == null) end = head;
    }

    //设置原位置是否有元素，如果有的话就替换，如果有的话，证明被使用过了，然后将其替换为头结点的元素，
    //如果是新节点，就要判断它的大小是否符合规范
    public void set(int key, int value) {
        if (map.containsKey(key)) {
            Node old = map.get(key);
            old.value = value;
            remove(old);
            setHead(old);
        } else {
            Node created = new Node(key, value);
            if (map.size() >= capacity) {
                map.remove(end.key);
                remove(end);
                setHead(created);
            } else {
                setHead(created);
            }
            map.put(key, created);
        }
    }
}
