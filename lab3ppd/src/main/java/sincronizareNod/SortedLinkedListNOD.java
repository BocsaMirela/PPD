package sincronizareNod;

import utils.SortedLinkedList;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SortedLinkedListNOD implements SortedLinkedList {
    private Node first;
    private int nrOfElements;
    private final Lock lock = new ReentrantLock();


    public SortedLinkedListNOD() {
        nrOfElements = 0;
    }

    public Node getFirst() {
        return first;
    }

    public void insert(double x) {
        if (first == null) {
            first = new Node(x);
        }else if(first.getValue()>=x){
            first.lock();
            Node newNode = new Node(x);
            newNode.setNext(first);
            first.unlock();
            first=newNode;
        } else {
            first.lock();
            Node node = first;
            while (node.getNext() != null && x > node.getNext().getValue()) {
                node = node.getNext();
            }

            Node newNode = new Node(x);
            newNode.setNext(node.getNext());
            node.setNext(newNode);
            first.unlock();
        }
        nrOfElements++;
    }

    public void delete(double x) {
        if (nrOfElements==0) return;
        if (nrOfElements == 1) {
            first.lock();
              first = null;
            nrOfElements--;
            return;
        }
        else if (x==first.getValue()){
            first.lock();
            first=first.getNext();
            first.unlock();
        }else {
            first.lock();
            Node node = first;
            while (node != null && node.getNext() != null && x != node.getNext().getValue()) {
                node = node.getNext();
            }
            if (node.getNext() != null) {
                node.setNext(node.getNext().getNext());
            } else {
                node.setNext(null);
            }
            first.unlock();
            nrOfElements--;
        }
    }

    public double get(int position) {
        if (position >= nrOfElements || position < 0)
            throw new RuntimeException("index out of bounds");
        if(first==null){
            throw new RuntimeException("Empty list");
        }
        first.lock();
        Node node = first;
        for (int i = 0; i < position; i++) {
            node = node.getNext();
        }
        first.unlock();
        return node.getValue();
    }

    public void print() {
        if(first==null) return;
        first.lock();
        Node node = first;
        while (node != null) {
            System.out.println("sincronizareLista.Node: Value= " + node.getValue() + " Next= " + node.getNext());
            node = node.getNext();
        }
        first.unlock();
    }


    public void lock() {
        this.lock.lock();
    }

    public void unlock() {
        this.lock.unlock();
    }

    public MyIterator iterator1() {
        return new MyIterator(this);
    }

    public Iterator iterator2() {
        return new Iterator(this);
    }
}
