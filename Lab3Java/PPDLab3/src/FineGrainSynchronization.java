public class FineGrainSynchronization extends SortedLinkedList {
    @Override
    public void insert(double value){
        if(first==null) {
            lock.lock();
            first = new Node(value);
            lock.unlock();
        }
        else if (first.getValue() > value) {
            lock.lock();
            Node noteToAdd = new Node(value);
            noteToAdd.setNext(first);
            first = noteToAdd;
            lock.unlock();
        }
        else if(first.getNext()==null){
            lock.lock();
            Node nodeToAdd = new Node(value);
            first.setNext(nodeToAdd);
            lock.unlock();
        }
        else {
            Node nodeToAddAfter=first;
            Node nodeToAddBefore=first.getNext();
            nodeToAddAfter.lock();
            nodeToAddBefore.lock();
            while(nodeToAddBefore!=null){
                if(nodeToAddBefore.getValue()>value)
                {
                    Node nodeToAdd = new Node(value);
                    nodeToAdd.setNext(nodeToAddBefore);
                    nodeToAddAfter.setNext(nodeToAdd);
                    nodeToAddAfter.unlock();
                    nodeToAddBefore.unlock();
                    break;
                }
                Node nodeToAddAfter2=nodeToAddAfter;
                nodeToAddAfter=nodeToAddBefore;
                nodeToAddAfter2.unlock();
                nodeToAddBefore = nodeToAddBefore.getNext();
                if(nodeToAddBefore!=null) {
                    nodeToAddBefore.lock();
                }
                else
                {
                    nodeToAddAfter.setNext(new Node(value));
                    nodeToAddAfter.unlock();
                    break;
                }
            }
        }
    }

    @Override
    public void remove(double value){
        lock.lock();
        if(this.first==null || this.first.getNext()==null) {
            lock.unlock();
            return;
        }
        if(this.first.getValue()==value) {
            this.first = this.first.getNext();
            lock.unlock();
            return;
        }
        lock.unlock();
        Node nodeToDeleteBefore=this.first;
        Node nodeToDelete=this.first.getNext();
        nodeToDelete.lock();
        nodeToDeleteBefore.lock();
        while(nodeToDeleteBefore!=null){
            if(nodeToDelete.getValue()==value){
                nodeToDeleteBefore.setNext(nodeToDelete.getNext());
                nodeToDeleteBefore.unlock();
                nodeToDelete.unlock();
                break;
            }
            Node nodeToDeleteBefore2=nodeToDeleteBefore;
            nodeToDeleteBefore2.getLock().unlock();
            nodeToDeleteBefore=nodeToDelete;
            if(nodeToDelete.getNext()!=null) {
                nodeToDelete.getNext().lock();
                nodeToDelete = nodeToDelete.getNext();
            }
            else {
                nodeToDeleteBefore.setNext(null);
                nodeToDeleteBefore.unlock();
                break;
            }
        }
    }
}
