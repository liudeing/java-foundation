package designpattern.Behavioral.Template;

/**
 * Created by liur on 17-4-30.
 */
public class MethodBenchMark extends BenchMark {
    /**
     * 真正的benchMark内容
     */
    @Override
    public void benchMark() {
        for (int i=0;i<Integer.MAX_VALUE;i++){
            System.out.println("i="+i);
        }
    }
}
