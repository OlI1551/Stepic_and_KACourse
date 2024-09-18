/*
Класс ByteArrayInputStream представляет входной поток,
использующий в качестве источника данных массив байтов. Он имеет следующие конструкторы:
1) ByteArrayInputStream(byte[] buf)
2) ByteArrayInputStream(byte[] buf, int offset, int length)

В качестве параметров конструкторы используют массив байтов buf,
из которого производится считывание, смещение относительно начала массива offset
и количество считываемых символов length.

В отличие от других классов потоков для закрытия объекта ByteArrayInputStream не требуется вызывать метод close.
 */

import java.io.*;

public class ex25FilesIO_OrdinaryByteArrayIOStream {
    public static void main(String[] args) throws IOException {
        byte[] array1 = new byte[] {1, 3, 5, 7}; // будем считывать из этого массива
        ByteArrayInputStream byteStream1 = new ByteArrayInputStream(array1);
        int b;
        while((b = byteStream1.read()) != -1) { // пока остались непрочитанные байты
            System.out.println(b);
        }

        String text = "Hello world!";
        byte[] array2 = text.getBytes(); // превратили в массив байтов строку, из этого массива будем считывать 5 символов, начиная с 0
        // считываем 5 символов
        ByteArrayInputStream byteStream2 = new ByteArrayInputStream(array2, 0, 5);
        int c;
        while((c = byteStream2.read()) != -1) { // пока остались непрочитанные байты
            System.out.println((char) c);
        }
/*
Класс ByteArrayOutputStream представляет поток вывода,
использующий массив байтов в качестве места вывода.
Чтобы создать объект данного класса, мы можем использовать один из его конструкторов:
1) ByteArrayOutputStream()
2) ByteArrayOutputStream(int size)

Первая версия создает массив для хранения байтов длиной в 32 байта, а вторая версия создает массив длиной size.
 */
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String text1 = "Hello World!";
        byte[] buffer = text1.getBytes();
        try {
            baos.write(buffer);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("baos : " + baos.toString()); // превращаем массив байтов в строку и выводим в консоль

        byte[] array = baos.toByteArray(); // получаем массив байтов и выводим посимвольно в консоль
        for (byte b1 : array) {
            System.out.print((char) b1);
        }
        System.out.println();

/*
Как и в других потоках вывода в классе ByteArrayOutputStream определен метод write,
который записывает в поток некоторые данные. В данном случае мы записываем в поток массив байтов.
Этот массив байтов записывается в объекте ByteArrayOutputStream в защищенное поле buf,
которое представляет также массив байтов (protected byte[] buf).
Так как метод write может сгенерировать исключение, то вызов этого метода помещается в блок try..catch.
Или не помещается, если в сигнатуре метода прописать throws IOException.
Используя методы toString() и toByteArray(),
можно получить массив байтов buf в виде текста или непосредственно в виде массива байт.
С помощью метода writeTo мы можем вывести массив байт в другой поток.
Данный метод в качестве параметра принимает объект OutputStream, в который производится запись массива байт

После выполнения этой программы в папке с программой появится файл hello.txt,
который будет содержать строку "Hello Wolrd!".
И в заключении также надо сказать, что как и для объектов ByteArrayInputStream,
для ByteArrayOutputStream не надо явным образом закрывать поток с помощью метода close.
 */
        ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
        String text2 = "Hello World!";
        byte[] buffer2 = text2.getBytes();
        try {
            baos1.write(buffer2);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        try (FileOutputStream fos = new FileOutputStream("hello.txt")) {
            baos.writeTo(fos);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}