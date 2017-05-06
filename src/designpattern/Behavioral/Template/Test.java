package designpattern.Behavioral.Template;

/**
 * Created by liur on 17-4-30.
 */
public class Test {
    public static void main(String[] args) {
        BenchMark operation = new MethodBenchMark();
        long duration = operation.repeat(22);
        System.out.println("The operation took"+duration+" millseconds");
    }
}
