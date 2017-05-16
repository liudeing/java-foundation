package designpattern.Behavioral.Template;

/**
 * 希望重复执行benchMark操作，但没有说明具体操作，延迟到子类中描述
 * Created by liur on 17-4-30.
 */
public abstract class BenchMark {
    /**
     * 下面的操作是我们希望在子类中完成
     */
    public abstract void benchMark();

    /**
     * 模板方法
     * @param count
     * @return
     */
    public final long repeat(int count){
        long startTime;
        if (count<=0){
            return 0;
        } else {
            startTime = System.currentTimeMillis();
        }

        for (int i=0;i<count;i++){
            benchMark();
        }

        long stopTime = System.currentTimeMillis();
        return stopTime-startTime;
    }
}
