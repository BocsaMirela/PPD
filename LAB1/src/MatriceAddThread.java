import utils.AsociativOperator;
import utils.Matrice;

public class MatriceAddThread<T> implements Runnable {
    private Matrice<T> a;
    private Matrice<T> b;
    private Matrice<T> c;
    private long startIndex;
    private long endIndex;
    AsociativOperator<T> op;

    public MatriceAddThread(Matrice<T> a, Matrice<T> b, Matrice<T> c, long startIndex, long endIndex, AsociativOperator<T> op) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.op = op;
    }

    @Override
    public void run() {
        final int columns = a.getColumns();
        for (long i = startIndex; i < endIndex; i++) {
            final int line = (int) (i / columns);
            final int col = (int) (i % columns);
////            System.out.println(line + " " + col);
//                System.out.println("Pairs");
//            System.out.println(line);
//            System.out.println(col);
//            System.out.println();
//                System.out.println(a.getElement(line, col));
//                System.out.println(b.getElement(line, col));
//                System.out.println();
            c.setElement(line, col, op.operation(a.getElement(line, col), b.getElement(line, col)));
        }
    }
}
