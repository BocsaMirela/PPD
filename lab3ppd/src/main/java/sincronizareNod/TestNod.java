package sincronizareNod;

import utils.MyLogger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

public class TestNod {

    private SortedLinkedListNOD sortedLinkedList;
    private MyLogger logger = new MyLogger("D:\\RepoUniversity\\PPD\\lab3ppd\\src\\main\\java\\ListaNOD.log");
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    final ThreadLocalRandom generator = ThreadLocalRandom.current();

    public TestNod(SortedLinkedListNOD sortedLinkedList) {
        this.sortedLinkedList = sortedLinkedList;

    }

    public void test1() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                logger.lock();
                sortedLinkedList.insert(i * 10);
                logMessage(i * 10, "ADD");
                logger.unlock();

            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                logger.lock();
                sortedLinkedList.insert(i);
                logMessage(i, "ADD");
                logger.unlock();

            }
        });

        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 7; i++) {
                logger.lock();
                sortedLinkedList.delete(i * 3);
                logMessage(i * 3, "DELETE");
                logger.unlock();


            }
        });

        t1.start();
        t3.start();
        t2.start();

        Thread t4 = new Thread(() -> {
            while (t1.getState() != Thread.State.TERMINATED || t2.getState() != Thread.State.TERMINATED || t3.getState() != Thread.State.TERMINATED) {
                sortedLinkedList.lock();
                MyIterator iterator = sortedLinkedList.iterator1();
                logger.lock();
                while (iterator.hasNext()) {
                    logMessage(iterator.getElement(), "ITERATE " + "NOD ");
                    iterator.next();
                }
                logger.unlock();
                sortedLinkedList.unlock();
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t4.start();


        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void logMessage(double nr, String opp) {
        LocalDateTime now = LocalDateTime.now();
        logger.log(opp + " " + nr + " executed by " + Thread.currentThread().getId() + " at " + dtf.format(now) + "\n");
    }

    public void test2() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                sortedLinkedList.insert(i);
//                logMessage(i,"ADD");
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                sortedLinkedList.insert(i);
//                logMessage(i,"ADD");

            }
        });

        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                sortedLinkedList.delete(i);
//                logMessage(i,"DELEE");

            }
        });
        t1.start();
        t3.start();
        t2.start();

        Thread t4 = new Thread(() -> {
            PrintWriter writer = null;
            try {
                writer = new PrintWriter(new File("D:\\RepoUniversity\\PPD\\lab3ppd\\src\\main\\java\\Lista.res"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            while (t1.getState() != Thread.State.TERMINATED || t2.getState() != Thread.State.TERMINATED || t3.getState() != Thread.State.TERMINATED) {
                sortedLinkedList.lock();
                MyIterator iterator = sortedLinkedList.iterator1();
                int it = 0;
                while (iterator.hasNext()) {
                    Node node = (Node) iterator.next();
                    writer.println("Iteratia " + it + " Node= " + node.getValue() + " Next " + node.getNext());
                    it++;
                }
                writer.println();
            }
            sortedLinkedList.unlock();
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {

            }
            if (writer != null)
                writer.close();
        });

        t4.start();


        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (
                InterruptedException e) {
            e.printStackTrace();
        }

    }
}
