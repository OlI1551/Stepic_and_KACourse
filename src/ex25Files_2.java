import java.io.*;

public class ex25Files_2 {

    public static void main(String[] args) {

        try(FileInputStream fin = new FileInputStream("notes.txt")) // или =Files.newInputStream(path), если надо из объекта path получить
        {
            int i;
            while((i=fin.read())!=-1){

                System.out.print((char)i); // просто выводим содержимое на экран, а можно сохранить в буфер для дальнейших манипуляций
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}