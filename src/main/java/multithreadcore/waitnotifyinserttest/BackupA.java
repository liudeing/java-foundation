package multithreadcore.waitnotifyinserttest;

/**
 * Created by liur on 17-5-25.
 */
public class BackupA extends Thread {
    private DBTools dbTools;
    public BackupA(DBTools dbTools){
        super();
        this.dbTools=dbTools;
    }

    @Override
    public void run() {
        dbTools.backupA();
    }
}
