import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLOutput;

public class ex25Files {
    public static void main(String[] args) throws IOException {
//        String canonical_path_string = "C:\\Windows\\System32\\";
//        String absolute_path_string = "C:\\Windows\\System32\\drivers\\..\\";
//
//        System.out.println(Paths.get(canonical_path_string).getParent());
//        System.out.println(Paths.get(absolute_path_string).getParent());

        String path1 = "a\\b\\..\\file.txt";
        String path2 = "a\\b\\c\\file.txt";
        String path3 = ".\\a\\b\\..\\b\\c\\.\\file.txt";
        String path4 = "a\\..\\b\\c\\file.txt";
        String path5 = "a\\.\\b\\..\\c\\.\\file.txt";
        Path path11 = Path.of(path1);

        File file1 = new File (path1);
        File file2 = new File (path2);
        File file3 = new File (path3);
        File file4 = new File (path4);
        File file5 = new File (path5);

        try {
            System.out.println(file1.getCanonicalPath());
            System.out.println(file2.getCanonicalPath());
            System.out.println(file3.getCanonicalPath());
            System.out.println(file4.getCanonicalPath());
            System.out.println(file5.getCanonicalPath());
        } catch (IOException e) {
            throw new IOException(e);
        }
//        System.out.println(Paths.get(path2));
//        System.out.println(Paths.get(path3));
//        System.out.println(Paths.get(path4));
//        System.out.println(Paths.get(path5));
    }
}
