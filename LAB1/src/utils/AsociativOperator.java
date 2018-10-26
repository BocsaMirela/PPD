package utils;

@FunctionalInterface
public interface AsociativOperator<T> {
    public T operation(T a, T b);
}
