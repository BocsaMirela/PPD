package utils;

public class Matrice<T> {
    private int lines;
    private int columns;
    private Object[][] values;

    public Matrice(int lines, int columns, T[][] values) {
        this.lines = lines;
        this.columns = columns;
        this.values = values;
    }

    public Matrice(final int n, final int m) {
        this.lines = n;
        this.columns = m;
        this.values = new Object[n][m];
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

    public T[][] getValues() {
        return (T[][])values;
    }

    public void setValues(T[][] values) {
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


    public void setElement(final int i, final int j, final T value){
        this.values[i][j]=value;
    }

    public T getElement(final int i, final int j){
        return (T)values[i][j];
    }

}
