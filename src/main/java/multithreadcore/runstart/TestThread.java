package multithreadcore.runstart;

/**
 * 继承Thread的run方法覆盖父类，那么会执行父类的run方法，在父类中支判断target是否为空，这个target是
 * Runnable接口，如果这个target不不为空，那么就会执行这个Runnable中的run方法，而不是去执行thread本身的run方法
 * Created by liur on 17-5-25.
 */
public class TestThread {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        new Thread().sleep(500);
                        System.out.println("--Runnable->" + Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }) {
            @Override
            public void run() {
                //如果有super.run();执行thread中的run方法
                super.run();
                while (true) {
                    try {
                        new Thread().sleep(500);
                        System.out.println("--Thread.run" + Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.run();


    }
}
