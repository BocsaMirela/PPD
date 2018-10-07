import utils.GenerateFiles;
import utils.Matrice;

public class Main {
    public static void main(String[] args) {

//        final int nrLines = Integer.parseInt(args[0]);
//        final int nrCols = Integer.parseInt(args[1]);
//        final int nrThreads = Integer.parseInt(args[2]);
        final int nrLines = 2000;
        final int nrCols = 2000;
        final int nrThreads = 100;

        Matrice matriceA = new Matrice(nrLines, nrCols);
        Matrice matriceB = new Matrice(nrLines, nrCols);
        Matrice matriceC = new Matrice(nrLines, nrCols);

        GenerateFiles.generateArray(nrLines, nrCols, "D:\\RepoUniversity\\PPD\\LAB1\\src\\files\\matriceA.txt");
        double[][] a = GenerateFiles.getArrayFromFile(nrLines, nrCols, "D:\\RepoUniversity\\PPD\\LAB1\\src\\files\\matriceA.txt");
        matriceA.setValues(a);

        GenerateFiles.generateArray(nrLines, nrCols, "D:\\RepoUniversity\\PPD\\LAB1\\src\\files\\matriceB.txt");
        double[][] b = GenerateFiles.getArrayFromFile(nrLines, nrCols, "D:\\RepoUniversity\\PPD\\LAB1\\src\\files\\matriceB.txt");
        matriceB.setValues(b);

        try {
            performADD(matriceA, matriceB, matriceC, nrThreads);
            performMULTIPLY(matriceA, matriceB, matriceC, nrThreads);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void performADD(Matrice matriceA, Matrice matriceB, Matrice matriceC, int nrThreads) throws InterruptedException {
        System.out.println("TEST ADD");
        System.out.println("Matrice A");
//        matriceA.printValues();

        System.out.println("Matrice B");
//        matriceB.printValues();

        long timeBeforeExecution = System.nanoTime();
        ParallelComputing.parallelAdd(matriceA, matriceB, matriceC, nrThreads);
        long timeAfterExecution = System.nanoTime();

        System.out.println("Matrice C");
//        matriceC.printValues();

        System.out.println();
        System.out.println("TIMPP");
        System.out.println(timeAfterExecution - timeBeforeExecution);
        System.out.println();


    }

    private static void performMULTIPLY(Matrice matriceA, Matrice matriceB, Matrice matriceC, int nrThreads) throws InterruptedException {
        System.out.println("TEST MULTIPLY");
        System.out.println("Matrice A");
//        matriceA.printValues();

        System.out.println("Matrice B");
//        matriceB.printValues();

        long timeBeforeExecution = System.nanoTime();
        ParallelComputing.parallelMultiply(matriceA, matriceB, matriceC, nrThreads);
        long timeAfterExecution = System.nanoTime();

        System.out.println("Matrice C");
//        matriceC.printValues();

        System.out.println();
        System.out.println("TIMPP");
        System.out.println(timeAfterExecution - timeBeforeExecution);
        System.out.println();


    }

}
