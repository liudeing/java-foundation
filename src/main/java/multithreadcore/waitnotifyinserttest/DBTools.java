package multithreadcore.waitnotifyinserttest;

/**
 * Created by liur on 17-5-25.
 */
public class DBTools {
    //确保备份A先执行，然后与B交替执行
    volatile private boolean prevIsA = false;

    synchronized public void backupA() {
        try {
            while (prevIsA == true) {
                wait();
            }
            //备份开始
            for (int i = 0; i < 5; i++) {
                System.out.println("*****");
            }
            prevIsA = true;
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized public void backupB() {
        try {
            while (prevIsA == false) {
                wait();
            }
            //备份开始
            for (int i = 0; i < 5; i++) {
                System.out.println("#####");
            }
            prevIsA = false;
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
