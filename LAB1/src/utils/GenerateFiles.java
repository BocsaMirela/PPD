package utils;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Random;
import java.util.Scanner;

public class GenerateFiles {

    public static int[][] getArrayFromFile(final int n, final int m, final String fileName) {
        int[][] result = new int[n][m];
        try (Scanner scanner = new Scanner(new FileInputStream(fileName))) {
            for (int i = 0; i < n; i++)
                for (int j = 0; j < m; j++) {
                    result[i][j] = scanner.nextInt();
                }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void generateArray(final int n, final int m, final String fileName) {
        try {
            BufferedWriter br = new BufferedWriter(new FileWriter(fileName));
            final Random random = new Random();
            for (int i = 0; i < n; i++)
                for (int j = 0; j < m; j++) {
                    final int randomValue = random.nextInt();
                    br.write(randomValue + " ");
                }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("Fisierul nu a fost gasit");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
