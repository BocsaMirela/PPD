import utils.GenerateFiles;
import utils.Matrice;

public class Main {

    public static void main(String[] args) {
        GenerateFiles.generateArray(4,4,"D:\\RepoUniversity\\PPD\\LAB1\\src\\files\\matrice.txt");
        long[][]a=GenerateFiles.getArrayFromFile(4,4,"D:\\RepoUniversity\\PPD\\LAB1\\src\\files\\matrice.txt");
        Matrice ma=new Matrice(4,4,a);

        GenerateFiles.generateArray(4,4,"D:\\RepoUniversity\\PPD\\LAB1\\src\\files\\matrice.txt");
        long[][]b=GenerateFiles.getArrayFromFile(4,4,"D:\\RepoUniversity\\PPD\\LAB1\\src\\files\\matrice.txt");
        Matrice mb=new Matrice(4,4,b);

        mb.printValues();
        ma.printValues();

        Matrice c=new Matrice(4,4);
        try {
            ParallelComputing.parallelAdd(ma,mb,c,3);
            c.printValues();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
