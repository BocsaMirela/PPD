package sincronizareNod;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Node implements Comparable {
    private Node next;
    private double value;
    private final Lock lock = new ReentrantLock();


    public Node(Node next, double value) {
        this.next = next;
        this.value = value;
    }

    public Node() {
        this.next = null;
        value = 0;
    }

    public Node(double x) {
        this.value = x;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void lock() {
        this.lock.lock();
    }

    public void unlock() {
        this.lock.unlock();
    }

    @Override
    public int compareTo(Object o) {
        if (value < ((Node) o).value) return -1;
        else if (value > ((Node) o).value) return 1;
        else return 0;
    }
}
