import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ex25FilesIO_1_1 {
    public static void main(String[] args) {
        byte[] original = {1, -2, -3, 4, 5};
        InputStream inputStream = new ByteArrayInputStream(original);
        int ans = 0;
        try {
            ans = sumOfStream(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(ans);
        try {
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static int sumOfStream(InputStream inputStream) throws IOException {

        int result = 0;
        int buff;
        while ((buff = inputStream.read()) != -1) {
            result += (byte) buff;
        }
        return result;
    }
}
