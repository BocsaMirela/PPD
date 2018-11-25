public class CourseGrainSynchronization extends SortedLinkedList {

    @Override
    public synchronized void insert(double value) {
        super.insert(value);
    }

    @Override
    public synchronized void remove(double value){
        super.remove(value);
    }
}
