package utils;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MyLogger {

    private Logger logger = Logger.getLogger("LOGGER");
    private FileHandler fh;
    private final Lock lock = new ReentrantLock();


    public MyLogger(String fileName){
        init(fileName);
    }

    public void init(String fileName) {

        try {

            // This block configure the logger with handler and formatter
            fh = new FileHandler(fileName);
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);


        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void log(final String message) {

        logger.info(message);

    }
    public void lock() {
        this.lock.lock();
    }

    public void unlock() {
        this.lock.unlock();
    }
}