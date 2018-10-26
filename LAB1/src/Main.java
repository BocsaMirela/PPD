import utils.GenerateFiles;
import utils.Matrice;

public class Main {
    public static void main(String[] args) {
        final int nrLines = 5;
        final int nrCols = 4;

        final int nrLinesM2 = 4;
        final int nrColsM2 = 5;
        final int nrThreads = 3;

        addMatrix(nrLines, nrCols, nrThreads);
        multiplyMatrix(nrLines,nrCols,nrLinesM2,nrColsM2,nrThreads);

    }

    private static void addMatrix(int nrLines, int nrCols, int nrThreads) {
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
            performADD(matriceA, matriceB, matriceResultADD, nrThreads);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void multiplyMatrix(int nrLines, int nrCols,int nrLines2, int nrCols2, int nrThreads) {
        Matrice matriceA = new Matrice(nrLines, nrCols);
        Matrice matriceB = new Matrice(nrLines2, nrCols2);
        Matrice matriceResultM = new Matrice(nrLines, nrCols2);

        GenerateFiles.generateArray(nrLines, nrCols, "D:\\RepoUniversity\\PPD\\LAB1\\src\\files\\matriceA.txt");
        double[][] a = GenerateFiles.getArrayFromFile(nrLines, nrCols, "D:\\RepoUniversity\\PPD\\LAB1\\src\\files\\matriceA.txt");
        matriceA.setValues(a);

        GenerateFiles.generateArray(nrLines2, nrCols2, "D:\\RepoUniversity\\PPD\\LAB1\\src\\files\\matriceB.txt");
        double[][] b = GenerateFiles.getArrayFromFile(nrLines2, nrCols2, "D:\\RepoUniversity\\PPD\\LAB1\\src\\files\\matriceB.txt");
        matriceB.setValues(b);

        try {
            performMULTIPLY(matriceA, matriceB, matriceResultM, nrThreads);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void performADD(Matrice matriceA, Matrice matriceB, Matrice matriceC, int nrThreads) throws InterruptedException {
        System.out.println("TEST ADD");
        System.out.println("Matrice A");
        matriceA.printValues();

        System.out.println("Matrice B");
        matriceB.printValues();

        long timeBeforeExecution = System.nanoTime();
        ParallelComputing.parallelAdd(matriceA, matriceB, matriceC, nrThreads);
        long timeAfterExecution = System.nanoTime();

        System.out.println("Matrice C");
        matriceC.printValues();

        System.out.println();
        System.out.println("TIMPP");
        System.out.println(timeAfterExecution - timeBeforeExecution);
        System.out.println();


    }

    private static void performMULTIPLY(Matrice matriceA, Matrice matriceB, Matrice matriceC, int nrThreads) throws InterruptedException {
        System.out.println("TEST MULTIPLY");
        System.out.println("Matrice A");
        matriceA.printValues();

        System.out.println("Matrice B");
        matriceB.printValues();

        long timeBeforeExecution = System.nanoTime();
        ParallelComputing.parallelMultiply(matriceA, matriceB, matriceC, nrThreads);
        long timeAfterExecution = System.nanoTime();

        System.out.println("Matrice C");
        matriceC.printValues();

        System.out.println();
        System.out.println("TIMPP");
        System.out.println(timeAfterExecution - timeBeforeExecution);
        System.out.println();


    }

}
