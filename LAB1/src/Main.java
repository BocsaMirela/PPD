import utils.AsociativOperator;
import utils.GenerateFiles;
import utils.Matrice;

public class Main {
    public static void main(String[] args) {
        final int nrLines = 4;
        final int nrCols = 4;

        final int nrThreads = 3;

        final AsociativOperator asociativOperator = (a, b) -> 	1/	(1/	a + 1/b);

        addMatrix(nrLines, nrCols, nrThreads, asociativOperator);

    }

    private static void addMatrix(int nrLines, int nrCols, int nrThreads, AsociativOperator asociativOperator) {
        Matrice matriceA = new Matrice(nrLines, nrCols);
        Matrice matriceB = new Matrice(nrLines, nrCols);
        Matrice matriceResultADD = new Matrice(nrLines, nrCols);

        GenerateFiles.generateArray(nrLines, nrCols, "D:\\RepoUniversity\\PPD\\LAB1\\src\\files\\matriceA.txt");
        double[][] a = GenerateFiles.getArrayFromFile(nrLines, nrCols, "D:\\RepoUniversity\\PPD\\LAB1\\src\\files\\matriceA.txt");
        matriceA.setValues(a);

        GenerateFiles.generateArray(nrLines, nrCols, "D:\\RepoUniversity\\PPD\\LAB1\\src\\files\\matriceB.txt");
        double[][] b = GenerateFiles.getArrayFromFile(nrLines, nrCols, "D:\\RepoUniversity\\PPD\\LAB1\\src\\files\\matriceB.txt");
        matriceB.setValues(b);
        try {
            performADD(matriceA, matriceB, matriceResultADD, nrThreads, asociativOperator);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private static void performADD(Matrice matriceA, Matrice matriceB, Matrice matriceC, int nrThreads, AsociativOperator asociativOperator) throws InterruptedException {
        System.out.println("TEST ADD");
        System.out.println("Matrice A");
        matriceA.printValues();

        System.out.println("Matrice B");
        matriceB.printValues();

        long timeBeforeExecution = System.nanoTime();
        ParallelComputing.parallelAdd(matriceA, matriceB, matriceC, nrThreads, asociativOperator);
        long timeAfterExecution = System.nanoTime();

        System.out.println("Matrice C");
        matriceC.printValues();

        System.out.println();
        System.out.println("TIMPP");
        System.out.println(timeAfterExecution - timeBeforeExecution);
        System.out.println();


    }


}
