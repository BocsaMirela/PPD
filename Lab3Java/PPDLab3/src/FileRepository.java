import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileRepository {
    private String filename;
    FileWriter fileWriter;
    BufferedWriter bufferedWriter;

    public BufferedWriter getBufferedWriter() {
        return bufferedWriter;
    }

    public void setBufferedWriter(BufferedWriter bufferedWriter) {
        this.bufferedWriter = bufferedWriter;
    }

    public FileRepository(String filename) throws IOException {
        this.filename=filename;
         fileWriter=new FileWriter(filename,false);
         bufferedWriter=new BufferedWriter(fileWriter);
    }


    public void writeToFile(String stringToWrite) throws IOException {
        bufferedWriter.write(stringToWrite);
    }
}
