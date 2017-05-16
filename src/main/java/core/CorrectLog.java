package core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by liur on 17-5-14.
 */
public class CorrectLog {
    private static final Logger log = LoggerFactory.getLogger(CorrectLog.class);

    public static void main(String[] args) {
        try {
            throw new NullPointerException("Just for test");
        } catch (Exception e){
          //  log.error(e);        //A
          //  log.error(e, e);        //B
            log.error("" + e);        //C
            log.error(e.toString());        //D
            log.error(e.getMessage());        //E
            log.error(null, e);        //F
            //correct
            log.error("", e);        //G
            log.error("{}", e);        //H
            log.error("{}", e.getMessage());        //I
            log.error("Error reading configuration file: " + e);        //J
            log.error("Error reading configuration file: " + e.getMessage());        //K
            //correct
            log.error("Error reading configuration file", e);        //L
        }
    }
}
