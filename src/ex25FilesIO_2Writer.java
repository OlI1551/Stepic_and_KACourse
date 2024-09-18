/*
Я сильный и независимый!
 */
import java.io.*;

public class ex25FilesIO_2Writer {
    public static void main(String[] args) throws Exception {
        FileWriter fw = new FileWriter("C:\\JavaEx\\notes.txt");
        fw.close();

//        FileReader fr = new FileReader("C:\\JavaEx\\notes.txt");
//        fr.close();

        int k1 = 2;
        int k2 = 9;
        newFile(k1, k2);
        newIntFile(k1, k2);

        newPoem("Хокку \nПодобен лучу самурайский клинок \nИ тот затупился \nПроклятая килька в томате!!");
    }

    public static void newFile(int k1, int k2) throws Exception {
        FileWriter nFile = new FileWriter("C:\\JavaEx\\file1.txt");
        for (int i = k1; i < k2; i++) {
            nFile.write(Integer.toString(i) + " "); // можно при записи переделать int в тип String, тогда будут записаны не кракозябры
        }
        nFile.close();
    }
    public static void newIntFile(int k1, int k2) throws Exception {
        FileWriter intFile = new FileWriter("C:\\JavaEx\\intNotes.txt"); // легко записываем int в виде кракозябр в файл
        for (int i = k1; i < k2; i++) {
            intFile.write(i);
        }
        intFile.close();
    }
    public static void newPoem(String str) throws Exception {
        FileWriter np = new FileWriter("C:\\JavaEx\\filePoem.txt");
        np.write(str);
        np.close();
    }
}
