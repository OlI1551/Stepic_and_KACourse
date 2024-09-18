import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;


public class ex25NewClassFile {
    public static void main(String[] args) throws IOException {
        /////////////////// РАБОТА С КЛАССОМ File /////////////////////////////////
        // определяем объект для каталога
        File dir = new File("C://Users//Пользователь//IdeaProjects");
        // если объект представляет каталог
        if (dir.isDirectory()) {
            // получаем все вложенные объекты в каталоге
            for (File item : dir.listFiles()) {
                if (item.isDirectory()) {
                    System.out.println(item.getName() + "  \t folder");
                } else{
                    System.out.println(item.getName() + "\t file");
                }
            }
        }
        File folder = new File("C:\\Users\\Пользователь\\Documents\\GitHub");
        for (File file : folder.listFiles()) {
            System.out.println(file.getName());
            System.out.println(file.getAbsolutePath());
        }
        // определяем объект для каталога
        File someDir = new File("C://Users//Пользователь//IdeaProjects//SomeDir");
        boolean created = someDir.mkdir();
        if (created) {
            System.out.println("Folder has been created");
        }
        // переименуем каталог
        File newDir = new File("C:\\Users\\Пользователь\\IdeaProjects\\NewDirRenamed");
        boolean renamed = someDir.renameTo(newDir);
        if (renamed) {
            System.out.println("Folder has been renamed");
        }
        // удалим каталог
        boolean deleted = newDir.delete();
        if (deleted) {
            System.out.println("Folder has been deleted");
        }



        // определяем объект для каталога и создаем каталог на диске
        File myFile = new File("C://SomeDir//notes.txt");
        if (myFile.mkdir()) {
            System.out.println("File myFile has been created");
        }

        System.out.println("File name: " + myFile.getName());
        System.out.println("Parent folder: " + myFile.getParent());

        if (myFile.exists())
            System.out.println("File exists");
        else
            System.out.println("File not found");

        System.out.println("File size: " + myFile.length());

        if (myFile.canRead())
            System.out.println("File can be read");
        else
            System.out.println("File can not be read");

        if (myFile.canWrite())
            System.out.println("File can be written");
        else
            System.out.println("File can not be written");

        // создадим новый файл на диске
        File newFile = new File("C://SomeDir//MyFile");
        try {
            boolean fileCreated = newFile.createNewFile();
            if (fileCreated) {
                System.out.println("File has been created");
            }
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        //////////////////////////////////////////////////////////////////////////////////////////////////////

        // Класс Paths нужен исключительно для того,
        // чтобы из переданной строки или URI получить объект типа Path.

        Path testFilePath = Paths.get("C:\\Users\\Username\\Desktop\\testFile.txt");
        // затем применяем методы интерфейса Path
        Path fileName = testFilePath.getFileName();
        System.out.println(fileName);

        Path parent = testFilePath.getParent();
        System.out.println(parent);

        Path root = testFilePath.getRoot();
        System.out.println(root);

        boolean endWithTxt = testFilePath.endsWith("Desktop\\testFile.txt");
        System.out.println(endWithTxt);
        boolean startsWithLalala = testFilePath.startsWith("lalalala");
        System.out.println(startsWithLalala);

        Path testFilePath1 = Paths.get("C:\\Users\\Username\\Desktop\\testFile.txt");
        System.out.println(testFilePath1.isAbsolute());

        Path path5 = Paths.get("C:\\Users\\Java\\.\\examples");
        System.out.println(path5.normalize());
        Path path6 = Paths.get("C:\\Users\\Java\\..\\examples");
        System.out.println(path6.normalize());

        Path testFilePath3 = Paths.get("C:\\Users\\Users\\Users\\Users");
        Path testFilePath4 = Paths.get("C:\\Users\\Users\\Users\\Users\\Username\\Desktop\\testFile.txt");
        System.out.println(testFilePath3.relativize(testFilePath4));

        //////////////////////////////////////////////////////////////////////////////////////////////////////

        // Работа с классом Files
        //создание файла
        Path testFile1 = Files.createFile(Paths.get("C:\\Users\\Пользователь\\Desktop\\testFile1.txt"));
        System.out.println("Был ли файл успешно создан?");
        System.out.println(Files.exists(Paths.get("C:\\Users\\Пользователь\\Desktop\\testFile1.txt")));

        //создание директории
        Path testDirectory = Files.createDirectory(Paths.get("C:\\Users\\Пользователь\\Desktop\\testDirectory1"));
        System.out.println("Была ли директория успешно создана?");
        System.out.println(Files.exists(Paths.get("C:\\Users\\Пользователь\\Desktop\\testDirectory1")));

        //перемещаем файл с рабочего стола в директорию testDirectory. Перемещать надо с указанием имени файла в папке!
        testFile1 = Files.move(testFile1, Paths.get("C:\\Users\\Пользователь\\Desktop\\testDirectory1\\testFile1.txt"), REPLACE_EXISTING);

        System.out.println("Остался ли наш файл на рабочем столе?");
        System.out.println(Files.exists(Paths.get("C:\\Users\\Пользователь\\Desktop\\testFile1.txt")));

        System.out.println("Был ли наш файл перемещен в testDirectory?");
        System.out.println(Files.exists(Paths.get("C:\\Users\\Пользователь\\Desktop\\testDirectory1\\testFile1.txt")));

        //удаление файла
        Files.delete(testFile1);
        System.out.println("Файл все еще существует?");
        System.out.println(Files.exists(Paths.get("C:\\Users\\Пользователь\\Desktop\\testDirectory1\\testFile1.txt")));



        //копирование файла
        //создание файла
        Path testFile2 = Files.createFile(Paths.get("C:\\Users\\Пользователь\\Desktop\\testFile2.txt"));
        System.out.println("Был ли файл успешно создан?");
        System.out.println(Files.exists(Paths.get("C:\\Users\\Пользователь\\Desktop\\testFile2.txt")));

        //создание директории
        Path testDirectory2 = Files.createDirectory(Paths.get("C:\\Users\\Пользователь\\Desktop\\testDirectory2"));
        System.out.println("Была ли директория успешно создана?");
        System.out.println(Files.exists(Paths.get("C:\\Users\\Username\\Пользователь\\Desktop\\testDirectory2")));

        //копируем файл с рабочего стола в директорию testDirectory2.
        testFile2 = Files.copy(testFile2, Paths.get("C:\\Users\\Пользователь\\Desktop\\testDirectory2\\testFile2.txt"), REPLACE_EXISTING);

        System.out.println("Остался ли наш файл на рабочем столе?");
        System.out.println(Files.exists(Paths.get("C:\\Users\\Пользователь\\Desktop\\testFile2.txt")));

        System.out.println("Был ли наш файл скопирован в testDirectory2?");
        System.out.println(Files.exists(Paths.get("C:\\Users\\Пользователь\\Desktop\\testDirectory2\\testFile2.txt")));

        //////////////////////////////////////////////////////////////////////////////////////////////
        // для русского текста тут чет надо поправить в кодировке??? потом доделать
        List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\Пользователь\\Desktop\\pushkin.txt"), UTF_8);
        List<String> result = new ArrayList<>();
        for (String s : lines) {
            if (s.startsWith("bla")) {
                String upper = s.toUpperCase();
                result.add(upper);
            }
        }
        for (String s : result) {
            System.out.println(s);
        }



        Stream<String> stream = Files.lines(Paths.get("C:\\Users\\Пользователь\\Desktop\\pushkin.txt"));
        List<String> res = stream
                .filter(line -> line.startsWith("bla"))
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        res.forEach(System.out::println);
    }
}


