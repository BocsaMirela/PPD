package sincronizareNod;

public class Iterator implements java.util.Iterator {
    Node current;
    SortedLinkedListNOD linkedList;

    public Iterator(SortedLinkedListNOD linkedList) {
        this.linkedList=linkedList;
        current=linkedList.getFirst();
    }

    public synchronized boolean hasNext() {
        return current.getNext()!=null;
    }

    public synchronized Object next() {
        return current.getNext();
    }

    public synchronized double getElement() {
        return current.getNext().getValue();
    }}
