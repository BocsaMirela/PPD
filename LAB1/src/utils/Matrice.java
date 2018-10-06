package utils;

public class Matrice {
    private int lines;
    private int columns;
    public long[][] values;

    public Matrice(int lines, int columns, long[][] values) {
        this.lines = lines;
        this.columns = columns;
        this.values = values;
    }

    public Matrice(final int n, final int m) {
        this.lines = n;
        this.columns = m;
        this.values = new long[n][m];
    }

    public int getLines() {
        return lines;
    }

    public void setLines(final int lines) {
        this.lines = lines;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(final int columns) {
        this.columns = columns;
    }

    public long[][] getValues() {
        return values;
    }

    public void setValues(long[][] values) {
        this.values = values;
    }

    public void printValues() {
        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(values[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printMatrice(final Matrice m) {
        for (int i = 0; i < m.getLines(); i++) {
            for (int j = 0; j < m.getColumns(); j++) {
                System.out.print(m.values[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static Matrice add(final Matrice a, final Matrice b) {
        long[][] values = new long[a.getLines()][b.getColumns()];
        for (int i = 0; i < a.getLines(); i++)
            for (int j = 0; j < a.getColumns(); j++) {
                values[i][j] = a.getValues()[i][j] + b.getValues()[i][j];
            }
        return new Matrice(a.getLines(), a.getColumns(), values);
    }

    public void add(final Matrice other) {
        for (int i = 0; i < other.getLines(); i++)
            for (int j = 0; j < other.getColumns(); j++) {
                this.values[i][j] += other.getValues()[i][j];
            }
    }

    public static Matrice multiply(final Matrice a, final Matrice b) {
        long[][] values = new long[a.getLines()][b.getColumns()];
        for (int i = 0; i < a.getLines(); i++)
            for (int j = 0; j < a.getColumns(); j++) {
                values[i][j] = a.getValues()[i][j] * b.getValues()[i][j];
            }
        return new Matrice(a.getLines(), a.getColumns(), values);
    }

    public void multiply(final Matrice other) {
        for (int i = 0; i < other.getLines(); i++)
            for (int j = 0; j < other.getColumns(); j++) {
                this.values[i][j] *= other.getValues()[i][j];
            }
    }

    public void setElement(final int i, final int j, final long value){
        this.values[i][j]=value;
    }

    public long getElement(final int i, final int j){
        return values[i][j];
    }

}
