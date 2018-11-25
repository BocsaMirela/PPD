package sincronizareLista;

import sincronizareLista.MyIterator;
import sincronizareLista.Node;
import sincronizareLista.SortedLinkedListALL;
import utils.MyLogger;
import utils.SortedLinkedList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

public class TestLista {

    private SortedLinkedListALL sortedLinkedList;
    private MyLogger logger = new MyLogger("D:\\RepoUniversity\\PPD\\lab3ppd\\src\\main\\java\\List.log");
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    final ThreadLocalRandom generator = ThreadLocalRandom.current();

    public TestLista(SortedLinkedListALL sortedLinkedList) {
        this.sortedLinkedList = sortedLinkedList;

    }

    public void test1() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                sortedLinkedList.insert(i * 10);
                logMessage(i * 3, "ADD");

            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                sortedLinkedList.insert(i);
                logMessage(i, "ADD");

            }
        });

        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 7; i++) {
                sortedLinkedList.delete(i * 3);
                logMessage(i * 3, "DELETE");

            }
        });

        t1.start();
        t3.start();
        t2.start();

        Thread t4 = new Thread(() -> {
            while (t1.getState() != Thread.State.TERMINATED || t2.getState() != Thread.State.TERMINATED || t3.getState() != Thread.State.TERMINATED) {
                sortedLinkedList.lock();
                MyIterator iterator = sortedLinkedList.iterator1();
                System.out.println(iterator.hasNext());
                while (iterator.hasNext()) {
                    logMessage(iterator.getElement(), "ITERATE " + "NOD ");
                    iterator.next();
                }
                sortedLinkedList.unlock();
                try {
                    Thread.sleep(10);
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
                writer = new PrintWriter(new File("D:\\RepoUniversity\\PPD\\lab3ppd\\src\\main\\java\\ListaALL.res"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            int it = 0;
            while (t1.getState() != Thread.State.TERMINATED || t2.getState() != Thread.State.TERMINATED || t3.getState() != Thread.State.TERMINATED) {
                sortedLinkedList.lock();
                MyIterator iterator = sortedLinkedList.iterator1();
                while (iterator.hasNext()) {
                    writer.println("Iteratia " + it + "" + " Node= " + iterator.getCurrent().getValue() + " Next " + iterator.getCurrent().getNext());
                    iterator.next();
                }
                sortedLinkedList.unlock();
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                }
                it++;
            }

            if (writer != null) {
                System.out.println("gsgsgsgsgsg");
                writer.close();
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
}
