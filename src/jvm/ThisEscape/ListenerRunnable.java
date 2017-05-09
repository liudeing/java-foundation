package jvm.ThisEscape;

import java.util.List;

/**
 * Created by liur on 17-5-7.
 */
public class ListenerRunnable implements Runnable{
    private EventSource<EventListener> source;
    public ListenerRunnable(EventSource<EventListener> source) {
        this.source = source;
    }
    public void run() {
        List<EventListener> listeners = null;

        try {
            listeners = this.source.retrieveListeners();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        for(EventListener listener : listeners) {
            listener.onEvent(new Object());
        }
    }
}
