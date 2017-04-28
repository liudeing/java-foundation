package concurrent.Future;

/**
 * Created by liur on 17-4-26.
 */
public class Client {
    public Data request(final String queryStr) {
        final FutureData future = new FutureData();
        new Thread() {
            public void run() {
                //RealData构造很慢,所以在单独的线程中进行
                RealData realData = new RealData(queryStr);
                future.setRealData(realData);
            }
        }.start();
        return future;  //FutureData会被立发即返回
    }
}
