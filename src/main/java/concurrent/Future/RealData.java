package concurrent.Future;

/**
 * Created by liur on 17-4-26.
 */
public class RealData implements Data {
    protected final String result;

    public RealData(String para) {
        //RealData构造很慢,需要用户等待很久,这里使用sleep模拟
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            sb.append(para);
            try {
                //这里使用sleep,代替一个很慢的过程
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        result = sb.toString();
    }

    @Override
    public String getResult() {
        return result;
    }
}
