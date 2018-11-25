package sincronizareLista;

import java.util.Iterator;

public class MyIterator implements Iterator {
    private  Node current;

    public MyIterator(SortedLinkedListALL linkedList) {
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

    public Node getCurrent() {
        return current;
    }

    public synchronized double getElement() {
        return current.getValue();
    }
}
