import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class FileRepository<T>  {
    private int nrColoane;
    private int nrLinii;
    private Map<Integer,List<T>> v1, v2;
    private String filename;
    T type;
    public FileRepository(int nrColoane, int nrLinii, int nrColoane2,T type){
        this.nrColoane=nrColoane;
        this.nrLinii=nrLinii;
        v1=new HashMap<>();
        if(nrColoane2==0)
            v2=new HashMap<>();
        else
            v2=new HashMap<>();
        this.filename="src\\"+nrLinii+"x"+nrColoane+".txt";
        this.type=type;
        if(type instanceof Integer)
            this.filename="src\\"+nrLinii+"x"+nrColoane+"Integer.txt";
        else if(type instanceof Double)
            this.filename="src\\"+nrLinii+"x"+nrColoane+"Double.txt";
        else if(type instanceof Float)
            this.filename="src\\"+nrLinii+"x"+nrColoane+"Float.txt";
        else
            this.filename="src\\"+nrLinii+"x"+nrColoane+"Complex.txt";
        loadData(type);
    }

    public Map<Integer,List<T>> getV1() {
        return v1;
    }

    public Map<Integer,List<T>> getV2() {
        return v2;
    }

    public void loadData(T type) {
        try {
            File file = new File(filename);
            String linie;
            if (!file.exists()) {
                file.createNewFile();
                BufferedWriter bf = new BufferedWriter(new FileWriter(filename));
                for(int i=0;i<nrLinii*2;i++) {
                    linie="";
                    for (int j = 0; j < nrColoane; j++) {

                        Random random=new Random();

                        if (type instanceof Integer) {
                            int max=1000;
                            int min=0;
                            Integer numar = random.nextInt(max - min);
                            linie+=numar+" ";
                        }
                        else if(!(type instanceof Float || type instanceof Double))
                        {
                            Double numar=random.nextDouble()*1000;
                            Double numar2=random.nextDouble()*1000;
                            ComplexNumber complexNumber=new ComplexNumber(numar.intValue(),numar2.intValue());
                            linie+=complexNumber.toString()+" ";
                        }
                        else
                        {
                            float min= 41.815080f;
                            float max= 41.829191f;
                            Float numar=random.nextFloat()*(max-min);
                            linie+=numar+" ";
                        }
                    }
                    linie+="\n";
                    bf.write(linie);
                }
                bf.close();
            }
            BufferedReader bf = new BufferedReader(new FileReader(file));
            linie=bf.readLine();
            int i=0;
            while(linie!=null) {
                int j=0;
                String numere[] = linie.split(" ");
                if(i<nrLinii)
                    v1.put(i,new ArrayList<>());
                else
                    v2.put(i-nrLinii,new ArrayList<>());
                for(String numar:numere){
                    Object numar2;
                    if(type instanceof Integer) {
                        numar2 = Float.parseFloat(numar);
                        numar2=((Float) numar2).intValue();
                    }
                    else if(type instanceof Double)
                        numar2=Double.parseDouble(numar);
                    else if(type instanceof Float)
                        numar2=Float.parseFloat(numar);
                    else
                    {
                        String complexArgs[]=numar.split("\\+");
                        numar2=new ComplexNumber(Double.parseDouble(complexArgs[0]),Double.parseDouble(complexArgs[1].replace("i","")));
                    }

                    if(i<nrLinii)
                        v1.get(i).add(j,(T)numar2);
                    else
                        v2.get(i-nrColoane).add(j,(T)numar2);
                    j++;
                }
                i++;
                linie=bf.readLine();
            }
            bf.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
