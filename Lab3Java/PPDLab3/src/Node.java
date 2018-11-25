import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Node {
    private Node next;
    private Double value;
    private Lock lock =new ReentrantLock();

    public Node(Double value){
        this.next=null;
        this.value=value;
    }

    public Lock getLock(){
        return lock;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Double getValue() {
        return value;
    }

    public Node getNext() {
        return next;
    }

    public void lock() {
        this.lock.lock();
    }

    public void unlock() {
        this.lock.unlock();
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
