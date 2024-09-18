/*
Класс File, определенный в пакете java.io, не работает напрямую с потоками.
Его задачей является управление информацией о файлах и каталогах.
Хотя на уровне операционной системы файлы и каталоги отличаются, но в Java они описываются одним классом File.

В зависимости от того, что должен представлять объект File - файл или каталог,
мы можем использовать один из конструкторов для создания объекта:
1) File(String путь_к_каталогу)
2) File(String путь_к_каталогу, String имя_файла)
3) File(File каталог, String имя_файла)

Например:
// создаем объект File для каталога
File dir1 = new File("C://SomeDir");
// создаем объекты для файлов, которые находятся в каталоге
File file1 = new File("C://SomeDir", "Hello.txt");
File file2 = new File(dir1, "Hello2.txt");



Класс File имеет ряд методов, которые позволяют управлять файлами и каталогами. Рассмотрим некоторые из них:
— boolean createNewFile(): создает новый файл по пути, который передан в конструктор. В случае удачного создания возвращает true, иначе false
— boolean delete(): удаляет каталог или файл по пути, который передан в конструктор. При удачном удалении возвращает true.
— boolean exists(): проверяет, существует ли по указанному в конструкторе пути файл или каталог. И если файл или каталог существует, то возвращает true, иначе возвращает false
— String getAbsolutePath(): возвращает абсолютный путь для пути, переданного в конструктор объекта
— String getName(): возвращает краткое имя файла или каталога
— String getParent(): возвращает имя родительского каталога
— boolean isDirectory(): возвращает значение true, если по указанному пути располагается каталог
— boolean isFile(): возвращает значение true, если по указанному пути находится файл
— boolean isHidden(): возвращает значение true, если каталог или файл являются скрытыми
— long length(): возвращает размер файла в байтах
— long lastModified(): возвращает время последнего изменения файла или каталога. Значение представляет количество миллисекунд, прошедших с начала эпохи Unix
— String[] list(): возвращает массив файлов и подкаталогов, которые находятся в определенном каталоге
— File[] listFiles(): возвращает массив файлов и подкаталогов, которые находятся в определенном каталоге
— boolean mkdir(): создает новый каталог и при удачном создании возвращает значение true
— boolean renameTo(File dest): переименовывает файл или каталог
 */

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class ex28File {
    public static void main(String[] args) throws IOException {
        File f = new File("C:\\JavaEx\\file1.txt");
        System.out.println(f.delete());
        System.out.println(f.createNewFile());
        System.out.println(f.exists());
        System.out.println(f.getAbsolutePath());
        System.out.println(f.getName());
        System.out.println(f.getParent());
        System.out.println(f.isDirectory());
        System.out.println(f.isFile());
        System.out.println(f.isHidden());
        System.out.println(f.length());
        System.out.println(f.lastModified());
        File dir = new File("C:\\JavaEx\\NewJavaEx");
        System.out.println(dir.mkdir());
        File f1 = new File("C:\\JavaEx\\NewJavaEx\\fileNew1.txt");
        File f2 = new File("C:\\JavaEx\\NewJavaEx\\fileNew2.bin");
        File dir1 = new File("C:\\JavaEx");
        System.out.println(Arrays.toString(dir.list()));
        System.out.println(Arrays.toString(dir1.list()));
        System.out.println(Arrays.toString(dir.listFiles()));
        File dir2 = new File("C:\\JavaEx\\NewJavaEx1");
        System.out.println(dir2.mkdir());
        File dir3 = new File("C:\\JavaEx\\NewJavaEx2");
        System.out.println(dir2.renameTo(dir3));


        // определяем объект для каталога
        File dir5 = new File("C:\\JavaEx");
        // если объект представляет каталог
        if (dir5.isDirectory()) {
            // получаем все вложенные объекты в каталоге
            for (File item : dir5.listFiles()) {
                if(item.isDirectory()) {
                    System.out.println(item.getName() + "  \t folder");
                } else {
                    System.out.println(item.getName() + "\t file");
                }
            }
        }

        // определяем объект для каталога
        File dir7 = new File("C:\\JavaEx\\NewJavaEx7");
        boolean created = dir7.mkdir();
        if (created) {
            System.out.println("Folder has been created");
        }
        // переименуем каталог
        File newDir7 = new File("C:\\JavEx\\NewJavaEx7Renamed");
        dir7.renameTo(newDir7);
        // удалим каталог
        boolean deleted = newDir7.delete();
        if (deleted) {
            System.out.println("Folder has been deleted");
        }

        // определяем объект для каталога
        File myFile = new File("C:\\JavaEx\\notes.txt");
        System.out.println("File name: " + myFile.getName());
        System.out.println("Parent folder: " + myFile.getParent());

        if (myFile.exists()) {
            System.out.println("File exists");
        } else {
            System.out.println("File not found");
        }

        System.out.println("File size: " + myFile.length());

        if (myFile.canRead()) {
            System.out.println("File can be read");
        } else {
            System.out.println("File can not be read");
        }

        if (myFile.canWrite()) {
            System.out.println("File can be written");
        } else {
            System.out.println("File can not be written");
        }

        // создадим новый файл
        File newFile = new File("C:\\JavaEx\\MyFile.txt");
        try {
            boolean created1 = newFile.createNewFile();
            if (created1) {
                System.out.println("File has been created");
            }
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        } // При создании нового файла метод createNewFile() в случае неудачи выбрасывает исключение IOException,
        // поэтому нам надо его отлавливать, например, в блоке try...catch, как делается в примере выше.
    }
}
