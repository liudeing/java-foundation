package multithreadcore.waitnotifyinserttest;

/**
 * wait/notify交叉备份
 * Created by liur on 17-5-25.
 */
public class Run {
    public static void main(String[] args) {
        DBTools dbTools=new DBTools();
        for (int i=0;i<20;i++) {
            BackupB b = new BackupB(dbTools);
            b.start();
            BackupA a = new BackupA(dbTools);
            a.start();
        }
    }
}
