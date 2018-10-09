import utils.Matrice;

import java.util.ArrayList;

public class ParallelComputing {
    public ParallelComputing() {
    }

    public static void parallelAdd(Matrice a, Matrice b, Matrice c, final int p) throws InterruptedException {
        ArrayList<Thread> threadList = new ArrayList<Thread>();
        int m = a.getLines();
        int n = b.getColumns();
        long workForThreads = m * n / p;
        long startIndex = 0;
        long endIndex = 0;
        long rest = (m * n) % p;
        for (int k = 1; k <= p; k++) {
            endIndex = startIndex + workForThreads;
            if (rest > 0) {
                endIndex++;
                rest--;
            }
            Thread t = new Thread(new MatriceAddThread(a, b, c, startIndex, endIndex));
            threadList.add(t);
            startIndex = endIndex;
        }
        for (Thread t : threadList) {
            t.start();
        }
        for (Thread t : threadList) {
            t.join();
        }

    }

    public static void parallelMultiply(Matrice a, Matrice b, Matrice c, final int p) throws InterruptedException {
        ArrayList<Thread> threadList = new ArrayList<Thread>();
        int m = b.getLines();
        int n = b.getColumns();
        int workForThreads = m * n / p;
        int startLine = 0;
        int endLine = 0;
        int endCol = 0;
        int rest = (m * n) % p;
        for (int k = 1; k <= p; k++) {
            int cate;
            if (rest > 0) {
                rest--;
                cate = workForThreads + 1;
            } else
                cate = workForThreads;

            while (cate > 0) {
                if (endCol == a.getColumns() - 1) {
                    endCol = 0;
                    ++endLine;
                }
                endCol++;
                cate--;
            }

            if (endLine > a.getLines())
                endLine = a.getLines();
//            System.out.println("linii "+startLine+" "+endLine);
            Thread t = new Thread(new MatriceMultiplyThread(a, b, c, startLine, endLine));
            threadList.add(t);
            startLine = endLine;
        }
        for (Thread t : threadList) {
            t.start();
        }
        for (Thread t : threadList) {
            t.join();
        }
    }
}
