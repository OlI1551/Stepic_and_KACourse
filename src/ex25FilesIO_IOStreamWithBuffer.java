import java.io.*;

public class ex25FilesIO_IOStreamWithBuffer {
    public static void main(String[] args) throws Exception {
        FileInputStream bufferInputStream = new FileInputStream("C:\\JavaEx\\intNotes1.txt");
        FileOutputStream bufferOutputStream = new FileOutputStream("C:\\JavaEx\\intNotesWithBuffer.txt");

        byte[] buffer = new byte[1000]; //такими кусками по 1000байт будем считывать и записывать
        while(bufferInputStream.available() > 0) {
            int count = bufferInputStream.read(buffer); //метод read считывает и возвращает в int count количество считанных байт - последний кусок будет меньше 1000 байт
            bufferOutputStream.write(buffer, 0, count); //записали весь кусок
        }
        bufferInputStream.close();
        bufferOutputStream.close();

    }
}
