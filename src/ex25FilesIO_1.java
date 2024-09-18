import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class ex25FilesIO_1 {
    public static void main(String[] args) {
        byte[] original = {1, -2, -3, 4, 5};
        InputStream inputStream = new ByteArrayInputStream(original);
        int answer = 0;
        try {
            answer = sumOfStream(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(answer);
    }
    public static int sumOfStream(InputStream inputStream) throws IOException {

        int result = 0;
        int buff = inputStream.read();
        while(buff != -1) {
            result += (byte) buff;
            buff = inputStream.read();
        }
        return result;
    }
}
