import java.io.FileInputStream;
import java.io.IOException;

public class ex25NewClassesIOStream {
    public static void main(String[] args) {
        FileInputStream fin = null;
        try {
            fin = new FileInputStream("C://Users//Пользователь//Desktop//pushkin.txt");
            int i = -1;
            while ((i = fin.read()) != -1) {
                System.out.print((char) i);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (fin != null) {
                    fin.close();
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }

        System.out.println("---------------------------------------------------");

        try(FileInputStream fin1 = new FileInputStream("C://Users//Пользователь//Desktop//pushkin.txt")) {
            int i=-1;
            while((i = fin1.read()) != -1) {
                System.out.print((char) i);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
