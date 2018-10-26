import utils.AsociativOperator;
import utils.GenerateFiles;
import utils.Matrice;

public class Main {
    public static void main(String[] args) {
        final int nrLines = 4;
        final int nrCols = 4;

        final int nrThreads = 3;

        final AsociativOperator<Integer> asociativOperator = (a, b) -> (a + b);

        addMatrix(nrLines, nrCols, nrThreads, asociativOperator);

    }

    private static void addMatrix(int nrLines, int nrCols, int nrThreads, AsociativOperator asociativOperator) {
        Matrice<Integer> matriceA = new Matrice<>(nrLines, nrCols);
        Matrice<Integer> matriceB = new Matrice<>(nrLines, nrCols);
        Matrice<Integer> matriceResultADD = new Matrice<>(nrLines, nrCols);

        GenerateFiles.generateArray(nrLines, nrCols, "D:\\RepoUniversity\\PPD\\LAB1\\src\\files\\matriceA.txt");
        Integer[][] a = new Integer[nrLines][nrLines];
        Double[][] ab = GenerateFiles.getArrayFromFile(nrLines, nrCols, "D:\\RepoUniversity\\PPD\\LAB1\\src\\files\\matriceA.txt");
        for (int i = 0; i < nrLines; i++) {
            for (int j = 0; j < nrCols; j++) {
                a[i][j] = ab[i][j].intValue();
            }
            System.out.println();
        }
        matriceA.setValues(a);

        GenerateFiles.generateArray(nrLines, nrCols, "D:\\RepoUniversity\\PPD\\LAB1\\src\\files\\matriceB.txt");
        Double[][] b = GenerateFiles.getArrayFromFile(nrLines, nrCols, "D:\\RepoUniversity\\PPD\\LAB1\\src\\files\\matriceB.txt");
        for (int i = 0; i < nrLines; i++) {
            for (int j = 0; j < nrCols; j++) {
                a[i][j] = b[i][j].intValue();
            }
            System.out.println();
        }
        matriceB.setValues(a);
        try {
            performADD(matriceA, matriceB, matriceResultADD, nrThreads, asociativOperator);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private static void performADD(Matrice<?> matriceA, Matrice<?> matriceB, Matrice<?> matriceC, int nrThreads, AsociativOperator<?> asociativOperator) throws InterruptedException {
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
