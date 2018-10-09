import utils.Matrice;

public class MatriceMultiplyThread implements Runnable {
    private Matrice a;
    private Matrice b;
    private Matrice c;
    private int startLine;
    private int endLine;
    private int endCol;
    private int startCol;

    public MatriceMultiplyThread(Matrice a, Matrice b, Matrice c, int startIndex1, int endIndex1, int startIndex2, int endIndex2) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.startLine = startIndex1;
        this.endLine = endIndex1;
        this.endCol = endIndex2;
        this.startCol = startIndex2;
    }

    @Override
    public void run() {
        final int columnsA = a.getColumns();
        final int columnsB = b.getColumns();
        for (int i = startLine; i < endLine; i++) {
            for (int j = 0; j < columnsB; j++) {
                double res = 0;
                for (int k = 0; k < columnsA; k++) {
//                    System.out.println();
//                    System.out.println("a "+i+"  "+k);
//                    System.out.println("b "+k+"  "+j);
//                    System.out.println();
                    res += (a.getValues()[i][k] * b.getValues()[k][j]);
                }
//            System.out.println(line + " " + col);
                c.setElement(i, j, res);
            }
        }
    }
}
