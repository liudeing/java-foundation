package multithreadcore.waitnotifyinserttest;

/**
 * Created by liur on 17-5-25.
 */
public class BackupB extends Thread {
    private DBTools dbTools;
    public BackupB(DBTools dbTools){
        super();
        this.dbTools=dbTools;
    }

    @Override
    public void run() {
       dbTools.backupB();
    }
}
