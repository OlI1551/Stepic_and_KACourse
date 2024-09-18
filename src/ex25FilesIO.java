import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class ex25FilesIO {

    public static void main(String args[]) {
        System.out.println(sumBytesFromKeyboardStream(System.in, 3));
    }

    static long sumBytesFromKeyboardStream(InputStream inputStream, int arraySize){
        int [] buff = new int[arraySize];
        int curReadidByte = 1;
        int byteC;
        int indexNuff = 0;
        long sum = 0;
        try {
            while ((byteC = inputStream.read()) != -1) {
                if (curReadidByte % buff.length != 0 && byteC != '\n')
                    buff[indexNuff++] = byteC;
                else {
                    if (byteC != '\n')
                        buff[indexNuff++] = byteC;
                    sum += Arrays.stream(buff).sum();
                    indexNuff = 0;
                    Arrays.fill(buff,0);
                }
                curReadidByte++;
            }
        } catch (IOException ex) {ex.printStackTrace();}
        sum += Arrays.stream(buff).sum();
        return sum;
    }
}