package jvm.OverOfMemory;

/**
 * JDK5.0以后每个线程堆 栈大小为1M
 * 内存泄漏：垃圾收集器无法回收它们
 * 栈容量由-Xss参数设定(java8至少228k)
 * VM args:-Xss228K
 * Created by liur on 17-5-5.
 */
public class JavaVMStackSOF {
    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack leak:" + oom.stackLength);
            throw e;
        }
    }
}
