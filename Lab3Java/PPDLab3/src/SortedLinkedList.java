import java.util.Iterator;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SortedLinkedList {


    protected Node first;
    protected Lock lock;

    public Node getFirst() {
        return first;
    }

    public Iteratorr getIterator() {
        return new Iteratorr(this.first);
    }


    public SortedLinkedList() {
        this.first = null;
        this.lock = new ReentrantLock();
    }

    public void insert(double value) {
        if (this.first == null) {
            this.first = new Node(value);
        } else if (first.getValue() > value) {
            Node noteToAdd = new Node(value);
            noteToAdd.setNext(first);
            first = noteToAdd;
        } else if (first.getNext() == null) {
            Node nodeToAdd = new Node(value);
            first.setNext(nodeToAdd);
        } else {
            Node nodeToAddAfter = first;
            Node nodeToAddBefore = first.getNext();
            while (nodeToAddBefore != null) {
                if (nodeToAddBefore.getValue() > value) {
                    Node nodeToAdd = new Node(value);
                    nodeToAdd.setNext(nodeToAddBefore);
                    nodeToAddAfter.setNext(nodeToAdd);
                    break;
                }
                nodeToAddAfter = nodeToAddBefore;
                if (nodeToAddBefore.getNext() != null)
                    nodeToAddBefore = nodeToAddBefore.getNext();
                else {
                    nodeToAddAfter.setNext(new Node(value));
                    break;
                }
            }
        }
    }


    public void remove(double value) {
        if (this.first.getValue() == value) {
            this.first = this.first.getNext();
            return;
        }
        if (this.first == null || this.first.getNext() == null)
            return;
        Node nodeToDeleteBefore = this.first;
        Node nodeToDelete = this.first.getNext();
        while (nodeToDeleteBefore != null) {
            if (nodeToDelete.getValue() == value) {
                nodeToDeleteBefore.setNext(nodeToDelete.getNext());
                break;
            }
            nodeToDeleteBefore = nodeToDelete;
            if (nodeToDelete.getNext() != null)
                nodeToDelete = nodeToDelete.getNext();
            else {
                nodeToDeleteBefore.setNext(null);
                break;
            }
        }
    }

    class Iteratorr implements Iterator {
        private Node node;

        public Iteratorr(Node node) {
            this.node = node;
            lock.lock();
        }

        @Override
        public boolean hasNext() {
            if (node == null) {
                lock.unlock();
                return false;
            }
            return true;
        }

        @Override
        public Double next() {
            if (node != null) {
                Double value = node.getValue();
                node = node.getNext();
                return value;
            } else return null;
        }
    }
}
