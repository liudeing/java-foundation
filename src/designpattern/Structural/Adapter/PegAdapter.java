package designpattern.Structural.Adapter;

/**
 * Created by liur on 17-4-28.
 */
public class PegAdapter extends SquarePeg{
    private RoundPeg roundPeg;

    public PegAdapter(RoundPeg peg){
        this.roundPeg=peg;
    }

    /**
     * 使用new生成对象和使用extends继承生成对象不同,
     * 前者无需对原来的类修改,甚至无需要知道其内部结构和源代码.
     * @param str
     */
    public void insert(String str){
        super.insert(str);
        roundPeg.insertIntoHole(str);
    }

}
