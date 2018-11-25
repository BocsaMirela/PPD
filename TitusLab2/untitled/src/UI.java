import javafx.util.Pair;

import java.util.*;
import java.util.function.Function;

class Operatie<T> implements Runnable{
    private int start, stop;
    private Map<Integer,List<T>> v1,v2,rez;
    private int nrColoane;
    private Function<Pair<T,T>,T> functie;

    public Operatie(int start, int stop, Map<Integer,List<T>> v1, Map<Integer,List<T>> v2,Map<Integer,List<T>> rez, int nrColoane, Function<Pair<T,T>,T> functie) {
        this.start = start;
        this.stop = stop;
        this.v1 = v1;
        this.v2 = v2;
        this.rez = rez;
        this.nrColoane = nrColoane;
        this.functie = functie;
    }


    public void run() {
        for (int i = start; i < stop; i++) {
            for (int j = 0; j < nrColoane; j++) {
                rez.get(i).add(j, (T) functie.apply((new Pair(v1.get(i).get(j), v2.get(i).get(j)))));
            }
        }
    }
}


public class UI {
    private int nrLinii1=1000;
    private int nrColoane=1000;
    private int nrthreads;
    private int type2;

    public void run() {
        int type;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduceti numarul de thread-uri: ");
        nrthreads = scanner.nextInt();
        System.out.println("Introduceti tipul de data pe care doriti sa faceti operatia: 1. Integer, 2. Float, 3. Double, 4. Complex");
        type = scanner.nextInt();
        System.out.println("Introduceti operatia pe care doriti sa o efectuati: 1. Adunare, 2. Inmultire, 3. 1/(1/a + 1/b)");
        type2=scanner.nextInt();
        if(type==1) {
            operatieInteger();
        }
        if(type==2) {
            operatieFloat();
        }
        if(type==3) {
            operatieDouble();
        }
        if(type==4) {
            operatieComplex();
        }

    }

    private void operatieInteger(){
        FileRepository<Integer> fileRepository=new FileRepository<>(nrColoane,nrLinii1,0,0);
        Map<Integer,List<Integer>> v1=fileRepository.getV1();
        Map<Integer,List<Integer>> v2=fileRepository.getV2();
        if(type2==1)
            matrixOperationByElements(v1,v2,(Pair<Integer, Integer> pair) -> pair.getValue() + pair.getKey());
        else
            matrixOperationByElements(v1,v2,(Pair<Integer, Integer> pair) -> pair.getValue() * pair.getKey());
    }

    private void operatieFloat(){
        FileRepository<Float> fileRepository=new FileRepository<>(nrColoane,nrLinii1,0,0f);
        Map<Integer,List<Float>> v1=fileRepository.getV1();
        Map<Integer,List<Float>> v2=fileRepository.getV2();
        if(type2==1)
            matrixOperationByElements(v1,v2,(Pair<Float, Float> pair) -> pair.getValue() + pair.getKey());
        else if(type2==2)
            matrixOperationByElements(v1,v2,(Pair<Float, Float> pair) -> pair.getValue() * pair.getKey());
        else
            matrixOperationByElements(v1,v2,(Pair<Float,Float> pair)->1/(1/pair.getKey()+1/pair.getValue()));
    }

    private void operatieDouble(){
        FileRepository<Double> fileRepository=new FileRepository<>(nrColoane,nrLinii1,0,0.0);
        Map<Integer,List<Double>> v1=fileRepository.getV1();
        Map<Integer,List<Double>> v2=fileRepository.getV2();
        if(type2==1)
            matrixOperationByElements(v1,v2,(Pair<Double, Double> pair) ->
                    pair.getValue() + pair.getKey()
            );
        else if(type2==2)
            matrixOperationByElements(v1,v2,(Pair<Double, Double> pair) -> pair.getValue() + pair.getKey());
        else
            matrixOperationByElements(v1,v2,(Pair<Double,Double> pair)->1/(1/pair.getKey()+1/pair.getValue()));
    }

    private void operatieComplex(){
        FileRepository<ComplexNumber> fileRepository=new FileRepository<>(nrColoane,nrLinii1,0,new ComplexNumber(1,1));
        Map<Integer,List<ComplexNumber>> v1=fileRepository.getV1();
        Map<Integer,List<ComplexNumber>> v2=fileRepository.getV2();
        if(type2==1)
            matrixOperationByElements(v1,v2,(Pair<ComplexNumber,ComplexNumber> pair)->new ComplexNumber(pair.getKey().getRealPart()+pair.getValue().getRealPart(),pair.getKey().getImaginaryPart()+pair.getValue().getImaginaryPart()));
        else if(type2==2)
            matrixOperationByElements(v1,v2,(Pair<ComplexNumber,ComplexNumber> pair)->new ComplexNumber(
                    pair.getKey().getRealPart()*pair.getValue().getRealPart()-pair.getKey().getImaginaryPart()*pair.getValue().getImaginaryPart(),
                    pair.getKey().getImaginaryPart()*pair.getValue().getRealPart()+pair.getKey().getRealPart()*pair.getValue().getImaginaryPart()));
        else
            matrixOperationByElements(v1,v2,(Pair<ComplexNumber,ComplexNumber> pair)->new ComplexNumber((pair.getKey().getRealPart()*pair.getValue().getRealPart()+pair.getKey().getImaginaryPart()+pair.getValue().getImaginaryPart())/(pair.getValue().getRealPart()*pair.getValue().getRealPart()+pair.getValue().getImaginaryPart()*pair.getValue().getImaginaryPart()),(pair.getKey().getImaginaryPart()*pair.getValue().getRealPart()-pair.getKey().getRealPart()*pair.getValue().getImaginaryPart())/(pair.getValue().getRealPart()*pair.getValue().getRealPart()+pair.getValue().getImaginaryPart()*pair.getValue().getImaginaryPart())));
    }


    private <T> void matrixOperationByElements(Map<Integer,List<T>> matrix1, Map<Integer,List<T>> matrix2, Function<Pair<T, T>, T> operation) {
        int remainder, start=0, end;
        Map<Integer,List<T>> result = new HashMap<>(nrLinii1, nrColoane);
        for(int i=0;i<nrLinii1;i++)
            result.put(i, new ArrayList<T>());
        int distSize=nrLinii1/nrthreads;
        remainder=nrLinii1%nrthreads;
        end=distSize;
        long starttime=System.currentTimeMillis();
       // Integer elementsPerThread = elementCount / threadCount, start = 0, remainder = elementCount % threadCount, end = elementsPerThread;
        Thread [] threads=new Thread[nrthreads];
        for (int i = 0; i < nrthreads; i++) {
            if (remainder > 0) {
                end++;
                remainder--;
            }
            threads[i]=new Thread(new Operatie<T> (start ,end,matrix1, matrix2, result, nrColoane, operation));
            threads[i].start();
            start = end;
            end += distSize;
        }


        for (int i=0;i<nrthreads;i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long timediff=System.currentTimeMillis()-starttime;
        for(int i=0;i<nrLinii1;i++) {
            for (int j = 0; j < nrColoane; j++)
                System.out.print(result.get(i).get(j)+" ");
            System.out.println();
        }
        //long timediff=System.currentTimeMillis()-starttime;
        System.out.println(timediff);
    }
}
