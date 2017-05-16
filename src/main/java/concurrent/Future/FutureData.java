package concurrent.Future;

/**
 * Created by liur on 17-4-26.
 */
public class FutureData implements Data {
    protected RealData realData = null;     //FutureData是RealData的包装
    protected boolean isReady = false;

    public synchronized void setRealData(RealData realData) {
        if (isReady) {
            return;
        }
        this.realData = realData;
        isReady = true;
        notifyAll();    //RealData已被注入,通知getResult()
    }

    @Override
    public synchronized String getResult() {    //等待RealData构造完成
        while (!isReady) {
            try {
                wait();     //一直等待,知道RealData被注入
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return realData.result;     //由RealData实现
    }
}
