/*
Друзья всегда со мной!
+ Scanner!!!
 */
import java.io.FileReader;
import java.util.Scanner;

public class ex25FilesIO_2Reader {
    public static void main(String[] args) throws Exception {
        FileReader fr = new FileReader("c:\\JavaEx\\filePoem.txt");
        Scanner sc = new Scanner(fr); // Друзья всегда со мной!

        int i = 1;
        while (sc.hasNextLine()) {
            System.out.println(i + " : " + sc.nextLine()); // легко считывает текст в виде типа String
            i++;
        }
        sc.close();
        fr.close();

        FileReader intReader = new FileReader("C:\\JavaEx\\intNotes.txt");
        Scanner intScanner = new Scanner(intReader);

        int j = 1;
        while (intScanner.hasNext()) {
            System.out.println(j + ":" + intScanner.nextInt()); // не считывает через Reader и Scanner значения типа int!!! записанные Writer в виде кракозябр, а через IStream легко считывает
            j++;
        }
        intScanner.close();
        intReader.close();
    }
}
