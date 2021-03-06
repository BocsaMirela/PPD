import utils.Matrice;

public class MatriceAddThread implements Runnable {
    private Matrice a;
    private Matrice b;
    private Matrice c;
    private long startIndex;
    private long endIndex;

    public MatriceAddThread(Matrice a, Matrice b, Matrice c, long startIndex, long endIndex) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    @Override
    public void run() {
        final int columns = a.getColumns();
        for (long i = startIndex; i < endIndex; i++) {
            final int line = (int) (i / columns);
            final int col = (int) (i % columns);
//            System.out.println(line + " " + col);
            c.setElement(line, col, a.getElement(line, col) + b.getElement(line, col));
        }
    }
}
