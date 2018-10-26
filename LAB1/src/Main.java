import utils.AsociativOperator;
import utils.ComplexNumber;
import utils.GenerateFiles;
import utils.Matrice;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final int nrLines = 4;
        final int nrCols = 4;
        final int nrThreads = 3;

        displayMenu(nrLines, nrCols, nrThreads);

    }

    private static void displayMenu(int nrLines, int nrCols, int nrThreads) {
        int type = 0, type2 = 0;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose the type:\n 1. Integer \n 2. Float \n 3. Double \n 4. Complex \n");
            type = scanner.nextInt();
            System.out.println("Choose the operation:\n 1. ADD \n 2. MULTIPLY \n 3. 1/(1/a + 1/b) \n");
            type2 = scanner.nextInt();
            if (type == 1) {
                operatieInteger(nrLines, nrCols, nrThreads, type2);
            }
            if (type == 2) {
                operatieFloat(nrLines, nrCols, nrThreads, type2);
            }
            if (type == 3) {
                operatieDouble(nrLines, nrCols, nrThreads, type2);
            }
            if (type == 4) {
                operatieComplex(nrLines, nrCols, nrThreads, type2);
            }
        }

    }

    private static void operatieFloat(int nrLines, int nrCols, int nrThreads, int op) {
        AsociativOperator<Float> asociativOperator = null;
        switch (op) {
            case 1:
                asociativOperator = (a, b) -> (a + b);
                break;
            case 2:
                asociativOperator = (a, b) -> (a * b);
                break;
            case 3:
                asociativOperator = (a, b) -> (1/(1/a + 1/b));
                break;
        }
        addMatrixFloat(nrLines, nrCols, nrThreads, asociativOperator);
    }

    private static void operatieInteger(int nrLines, int nrCols, int nrThreads, int op) {
        AsociativOperator<Integer> asociativOperator = null;
        switch (op) {
            case 1:
                asociativOperator = (a, b) -> (a + b);
                break;
            case 2:
                asociativOperator = (a, b) -> (a * b);
                break;
            case 3:
                asociativOperator = (a, b) -> ((1/a + 1/b));
                break;
        }
        addMatrixInteger(nrLines, nrCols, nrThreads, asociativOperator);
    }

    private static void operatieDouble(int nrLines, int nrCols, int nrThreads, int op) {
        AsociativOperator<Double> asociativOperator = null;
        switch (op) {
            case 1:
                asociativOperator = (a, b) -> (a + b);
                break;
            case 2:
                asociativOperator = (a, b) -> (a * b);
                break;
            case 3:
                asociativOperator = (a, b) -> (1/(1/a + 1/b));
                break;
        }
        addMatrixDouble(nrLines, nrCols, nrThreads, asociativOperator);
    }

    private static void operatieComplex(int nrLines, int nrCols, int nrThreads, int op) {
        AsociativOperator<ComplexNumber> asociativOperator = null;
        switch (op) {
            case 1:
                asociativOperator = (a, b) -> (ComplexNumber.add(a, b));
                break;
            case 2:
                asociativOperator = (a, b) -> (ComplexNumber.multiply(a, b));
                break;
            case 3:
                asociativOperator = (a, b) -> (ComplexNumber.evaluteExpresion(a, b));
                break;
        }
        addMatrixComplex(nrLines, nrCols, nrThreads, asociativOperator);

    }

    private static void addMatrixComplex(int nrLines, int nrCols, int nrThreads, AsociativOperator asociativOperator) {
        Matrice<ComplexNumber> matriceA = new Matrice<>(nrLines, nrCols);
        Matrice<ComplexNumber> matriceB = new Matrice<>(nrLines, nrCols);
        Matrice<ComplexNumber> matriceResultADD = new Matrice<>(nrLines, nrCols);

        GenerateFiles.generateArray(nrLines, nrCols, "D:\\RepoUniversity\\PPD\\LAB1\\src\\files\\matriceA.txt");
        Double[][] ab = GenerateFiles.getArrayFromFile(nrLines, nrCols, "D:\\RepoUniversity\\PPD\\LAB1\\src\\files\\matriceA.txt");

        GenerateFiles.generateArray(nrLines, nrCols, "D:\\RepoUniversity\\PPD\\LAB1\\src\\files\\matriceA.txt");
        Double[][] b = GenerateFiles.getArrayFromFile(nrLines, nrCols, "D:\\RepoUniversity\\PPD\\LAB1\\src\\files\\matriceA.txt");

        ComplexNumber[][] complexNumbers = new ComplexNumber[nrLines][nrCols];
        for (int i = 0; i < nrLines; i++) {
            for (int j = 0; j < nrCols; j++) {
                complexNumbers[i][j] = new ComplexNumber(ab[i][j], b[i][j]);
            }
        }
        matriceA.setValues(complexNumbers);

        GenerateFiles.generateArray(nrLines, nrCols, "D:\\RepoUniversity\\PPD\\LAB1\\src\\files\\matriceB.txt");
        ab = GenerateFiles.getArrayFromFile(nrLines, nrCols, "D:\\RepoUniversity\\PPD\\LAB1\\src\\files\\matriceA.txt");

        GenerateFiles.generateArray(nrLines, nrCols, "D:\\RepoUniversity\\PPD\\LAB1\\src\\files\\matriceB.txt");
        b = GenerateFiles.getArrayFromFile(nrLines, nrCols, "D:\\RepoUniversity\\PPD\\LAB1\\src\\files\\matriceA.txt");

        ComplexNumber[][] complexNumbersb = new ComplexNumber[nrLines][nrCols];
        for (int i = 0; i < nrLines; i++) {
            for (int j = 0; j < nrCols; j++) {
                complexNumbersb[i][j] = new ComplexNumber(ab[i][j], b[i][j]);
            }
        }
        matriceB.setValues(complexNumbersb);

        try {
            performADD(matriceA, matriceB, matriceResultADD, nrThreads, asociativOperator);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void addMatrixDouble(int nrLines, int nrCols, int nrThreads, AsociativOperator asociativOperator) {
        Matrice<Double> matriceA = new Matrice<>(nrLines, nrCols);
        Matrice<Double> matriceB = new Matrice<>(nrLines, nrCols);
        Matrice<Double> matriceResultADD = new Matrice<>(nrLines, nrCols);

        GenerateFiles.generateArray(nrLines, nrCols, "D:\\RepoUniversity\\PPD\\LAB1\\src\\files\\matriceA.txt");
        Double[][] ab = GenerateFiles.getArrayFromFile(nrLines, nrCols, "D:\\RepoUniversity\\PPD\\LAB1\\src\\files\\matriceA.txt");
        matriceA.setValues(ab);

        GenerateFiles.generateArray(nrLines, nrCols, "D:\\RepoUniversity\\PPD\\LAB1\\src\\files\\matriceB.txt");
        Double[][] b = GenerateFiles.getArrayFromFile(nrLines, nrCols, "D:\\RepoUniversity\\PPD\\LAB1\\src\\files\\matriceB.txt");
        matriceB.setValues(b);
        try {
            performADD(matriceA, matriceB, matriceResultADD, nrThreads, asociativOperator);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void addMatrixFloat(int nrLines, int nrCols, int nrThreads, AsociativOperator asociativOperator) {
        Matrice<Float> matriceA = new Matrice<>(nrLines, nrCols);
        Matrice<Float> matriceB = new Matrice<>(nrLines, nrCols);
        Matrice<Float> matriceResultADD = new Matrice<>(nrLines, nrCols);

        GenerateFiles.generateArray(nrLines, nrCols, "D:\\RepoUniversity\\PPD\\LAB1\\src\\files\\matriceA.txt");
        Float[][] a = new Float[nrLines][nrLines];
        Double[][] ab = GenerateFiles.getArrayFromFile(nrLines, nrCols, "D:\\RepoUniversity\\PPD\\LAB1\\src\\files\\matriceA.txt");
        for (int i = 0; i < nrLines; i++) {
            for (int j = 0; j < nrCols; j++) {
                a[i][j] = ab[i][j].floatValue();
            }
        }
        matriceA.setValues(a);

        GenerateFiles.generateArray(nrLines, nrCols, "D:\\RepoUniversity\\PPD\\LAB1\\src\\files\\matriceB.txt");
        Double[][] b = GenerateFiles.getArrayFromFile(nrLines, nrCols, "D:\\RepoUniversity\\PPD\\LAB1\\src\\files\\matriceB.txt");
        for (int i = 0; i < nrLines; i++) {
            for (int j = 0; j < nrCols; j++) {
                a[i][j] = b[i][j].floatValue();
            }
        }
        matriceB.setValues(a);
        try {
            performADD(matriceA, matriceB, matriceResultADD, nrThreads, asociativOperator);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void addMatrixInteger(int nrLines, int nrCols, int nrThreads, AsociativOperator asociativOperator) {
        Matrice<Integer> matriceA = new Matrice<>(nrLines, nrCols);
        Matrice<Integer> matriceB = new Matrice<>(nrLines, nrCols);
        Matrice<Double> matriceResultADD = new Matrice<>(nrLines, nrCols);

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
