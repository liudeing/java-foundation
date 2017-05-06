package Behavioral.Observer;

import java.util.Observable;
import java.util.Observer;

/**
 * 产品价格观察
 * Created by liur on 17-4-30.
 */
public class PriceObserver implements Observer {
    private float price = 0;
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Float){
            price = ((Float) arg).floatValue();
            System.out.println("PriceObserver price change to "+price);
        }
    }
}
