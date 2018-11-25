import sincronizareLista.SortedLinkedListALL;
import sincronizareLista.TestLista;
import sincronizareNod.MyIterator;
import sincronizareNod.SortedLinkedListNOD;
import sincronizareNod.TestNod;

public class Main {
    public static void main(String[] args) {
//        TestNod program = new TestNod(new SortedLinkedListNOD());
//        program.test1();
//        program.test2();

        TestLista testLista=new TestLista(new SortedLinkedListALL());
        testLista.test1();

        TestLista testLista2=new TestLista(new SortedLinkedListALL());
        testLista2.test2();

////        TestNod program2=new TestNod(new SortedLinkedListNOD());
////        program2.test1();
//////        program2.test2();
//        SortedLinkedListNOD sortedLinkedListNOD =new SortedLinkedListNOD();
//        long m=System.currentTimeMillis();
//        sortedLinkedListNOD.insert(1077);
//        sortedLinkedListNOD.insert(10);
//        sortedLinkedListNOD.insert(109);
//        long n=System.currentTimeMillis();
////        System.out.println(n-m);
//        sortedLinkedListNOD.insert(108);
//        sortedLinkedListNOD.insert(107);
//        sortedLinkedListNOD.insert(106);
//        sortedLinkedListNOD.insert(104);
//        sortedLinkedListNOD.insert(102);
//        sortedLinkedListNOD.insert(10);
////
//        MyIterator iterator= sortedLinkedListNOD.iterator1();
//        while (iterator.hasNext()){
//            System.out.println(iterator.getElement());
//            iterator.next();
//        }
//
    }
}
