package sincronizareNod;

import java.util.Iterator;

public class MyIterator implements Iterator {
    Node current;

    public MyIterator(SortedLinkedListNOD linkedList) {
        current=linkedList.getFirst();
    }

    public synchronized boolean hasNext() {
        return current!=null;
    }

    public synchronized Object next()
    {
        current=current.getNext();
        return current;
    }
    public synchronized double getElement() {
        return current.getValue();
    }
}
