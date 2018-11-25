package sincronizareLista;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SortedLinkedListALL {
    private Node first;
    private int nrOfElements;
    private final Lock lock = new ReentrantLock();


    public SortedLinkedListALL() {
        nrOfElements = 0;
    }

    public Node getFirst() {
        return first;
    }

    public synchronized void insert(double x) {
        if (first == null) {
            first = new Node(x);
        } else if (first.getValue() >= x) {
            Node newNode = new Node(x);
            newNode.setNext(first);
            first = newNode;
        } else {
            Node node = getFirst();
            while (node.getNext() != null && x > node.getNext().getValue()) {
                node = node.getNext();
            }

            Node newNode = new Node(x);
            newNode.setNext(node.getNext());
            node.setNext(newNode);
        }
        nrOfElements++;
    }

    public synchronized void delete(double x) {
        if (nrOfElements==0) return;
        if (nrOfElements == 1) {
            first = null;
            nrOfElements--;
            return;
        }else {
            Node node = first;
            boolean ok = false;
            if (x == first.getValue()) {
                first = first.getNext();
                ok = true;
            } else {
                while (node != null && node.getNext() != null && x != node.getNext().getValue()) {
                    node = node.getNext();
                }
                if (node.getNext() != null) {
                    ok = true;
                    node.setNext(node.getNext().getNext());
                } else if (node != null && node.getValue() == x) {
                    node.setNext(null);
                    ok = false;
                }
            }
            if (!ok) System.out.println("No such element");
            nrOfElements--;
        }
    }

    public synchronized double get(int position) {
        if (position >= nrOfElements || position < 0)
            throw new RuntimeException("index out of bounds");
        Node node = first;
        for (int i = 0; i < position; i++) {
            node = node.getNext();
        }
        return node.getValue();
    }

    public void print() {
        Node node = first;
        while (node != null) {
            System.out.println("Node: Value= " + node.getValue() + " Next= " + node.getNext());
            node = node.getNext();
        }
    }

    public synchronized MyIterator iterator1() {
        return new MyIterator(this);
    }

    public synchronized Iterator iterator2() {
        return new Iterator(this);
    }

    public synchronized void setFirst(Node first) {
        this.first = first;
    }

    public synchronized int getSize() {
        return nrOfElements;
    }

    public synchronized void setNrOfElements(int nrOfElements) {
        this.nrOfElements = nrOfElements;
    }
    public void lock() {
        this.lock.lock();
    }

    public void unlock() {
        this.lock.unlock();
    }
}
