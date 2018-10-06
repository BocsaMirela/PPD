import utils.Matrice;

public class MatriceMultiplyThread implements Runnable {
    private Matrice a;
    private Matrice b;
    private Matrice c;
    private int startIndex;
    private int endIndex;

    public MatriceMultiplyThread(Matrice a, Matrice b, Matrice c, int startIndex, int endIndex) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    @Override
    public void run() {
        final int columns = a.getColumns();
        for (int i = startIndex; i < endIndex; i++) {
            final int line = i / columns;
            final int col = i % columns;
//            System.out.println(line + " " + col);
            c.setElement(line, col, a.getElement(line, col) * b.getElement(line, col));
        }
    }
}
