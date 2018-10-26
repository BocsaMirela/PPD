import utils.AsociativOperator;
import utils.Matrice;

import java.util.ArrayList;

public class ParallelComputing {
    public ParallelComputing() {
    }

    public static void parallelAdd(Matrice a, Matrice b, Matrice c, final int p, AsociativOperator asociativOperator) throws InterruptedException {
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
            Thread t = new Thread(new MatriceAddThread(a, b, c, startIndex, endIndex, asociativOperator));
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
}
