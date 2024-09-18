/*
Применительно к работе с файлами и вводом-выводом -
потоке (stream) - абстракция, которая используется для чтения или записи информации (файлов, сокетов, текста консоли)

Поток связан с реальным физическим устройством с помощью системы ввода-вывода Java.
Поток может быть связан с файлом:  через него ведем чтение или запись файла.
Поток может быть связан с сетевым сокетом: через него получаем или отправляем данные в сети.
Такие задачи: чтение и запись различных файлов, обмен информацией по сети,
ввод-ввывод в консоли мы будем решать в Java с помощью потоков.

Объект, из которого можно считать данные, называется потоком ввода,
а объект, в который можно записывать данные, - потоком вывода.
Например, если надо считать содержание файла, то применяется поток ввода, а если надо записать в файл - то поток вывода.

В основе всех классов, управляющих потоками байтов, находятся два абстрактных класса:
InputStream (представляющий потоки ввода) и OutputStream (представляющий потоки вывода)

Класс InputStream
Класс InputStream является базовым для всех классов, управляющих байтовыми потоками ввода. Рассмотрим его основные методы:
int available(): возвращает количество байтов, доступных для чтения в потоке
void close(): закрывает поток
int read(): возвращает целочисленное представление следующего байта в потоке. Когда в потоке не останется доступных для чтения байтов, данный метод возвратит число -1
int read(byte[] buffer): считывает байты из потока в массив buffer. После чтения возвращает число считанных байтов. Если ни одного байта не было считано, то возвращается число -1
int read(byte[] buffer, int offset, int length): считывает некоторое количество байтов, равное length, из потока в массив buffer. При этом считанные байты помещаются в массиве, начиная со смещения offset, то есть с элемента buffer[offset]. Метод возвращает число успешно прочитанных байтов.
long skip(long number): пропускает в потоке при чтении некоторое количество байт, которое равно number

Класс OutputStream
Класс OutputStream является базовым классом для всех классов, которые работают с бинарными потоками записи.
Свою функциональность он реализует через следующие методы:
void close(): закрывает поток
void flush(): очищает буфер вывода, записывая все его содержимое
void write(int b): записывает в выходной поток один байт, который представлен целочисленным параметром b
void write(byte[] buffer): записывает в выходной поток массив байтов buffer.
void write(byte[] buffer, int offset, int length): записывает в выходной поток некоторое число байтов, равное length, из массива buffer, начиная со смещения offset, то есть с элемента buffer[offset].

При завершении работы с потоком его надо закрыть с помощью метода close(), который определен в интерфейсе Closeable.
Метод close имеет следующее определение:
void close() throws IOException
Этот интерфейс уже реализуется в классах InputStream и OutputStream, а через них и во всех классах потоков.
При закрытии потока освобождаются все выделенные для него ресурсы, например, файл.
В случае, если поток окажется не закрыт, может происходить утечка памяти.

Есть два способа закрытия файла. Первый традиционный заключается в использовании блока try..catch..finally.
Поскольку при открытии или считывании файла может произойти ошибка ввода-вывода, то код считывания помещается в блок try.
И чтобы быть уверенным, что поток в любом случае закроется, даже если при работе с ним возникнет ошибка,
вызов метода close() помещается в блок finally.
И, так как метод close() также в случае ошибки может генерировать исключение IOException,
то его вызов также помещается во вложенный блок try..catch

Начиная с Java 7 можно использовать еще один способ, который автоматически вызывает метод close.
Этот способ заключается в использовании конструкции try-with-resources (try-с-ресурсами).
Данная конструкция работает с объектами, которые реализуют интерфейс AutoCloseable.
Так как все классы потоков реализуют интерфейс Closeable, который в свою очередь наследуется от AutoCloseable,
то их также можно использовать в данной конструкции.
Синтаксис конструкции следующий: try(название_класса имя_переменной = конструктор_класса).
Данная конструкция также не исключает использования блоков catch.
После окончания работы в блоке try у ресурса (в данном случае у объекта FileInputStream) автоматически вызывается метод close().
Если нам надо использовать несколько потоков, которые после выполнения надо закрыть,
то мы можем указать объекты потоков через точку с запятой:

try (FileInputStream fin = new FileInputStream("C://SomeDir//Hello.txt");
        FileOutputStream fos = new FileOutputStream("C://SomeDir//Hello2.txt")) {
    //..................
}
 */

import java.io.*;

public class ex25FilesIO_IOStream {
    public static void main(String[] args) throws Exception {
        //создаем объект FileInputStream, привязанный к файлу «c:/data.txt».
        FileInputStream inputStream = new FileInputStream("C:\\JavaEx\\intNotes.txt");
        long sum = 0;

        while (inputStream.available() > 0) { //пока остались непрочитанные байты
            int data = inputStream.read(); //прочитать очередной байт (записанное число типа int, записано с помощью FileWriter!!! - в тексте кракозябры)
            System.out.println(data); //вывести его на печать
            sum += data; //добавить его к общей сумме
        }
        inputStream.close(); //закрываем поток ввода байтов
        System.out.println("сумма : " + sum);

        FileInputStream inputStream1 = new FileInputStream("C:\\JavaEx\\intNotes.txt"); //отсюда будем читать
        FileOutputStream outputStream = new FileOutputStream("C:\\JavaEx\\intNotes1.txt"); //сюда будем перезаписывать
        while (inputStream1.available() > 0) {
            int data1 = inputStream1.read();
            if (data1 % 2 == 0) {
                outputStream.write(data1);
            }
        }
        outputStream.flush(); //записываем весь блок в файл
        inputStream1.close();
        outputStream.close(); //flush можно было не вызывать - если использовать close, то он сам вызывает его, а потом закрывает файл


        FileInputStream inputStream2 = new FileInputStream("C:\\JavaEx\\intNotes1.txt");
        while (inputStream2.available() > 0) { //пока остались непрочитанные байты
            int data = inputStream2.read(); //прочитать очередной байт (записанное число типа int, записано с помощью FileWriter!!! - в тексте кракозябры)
            System.out.println(data); //вывести его на печать
        }
        inputStream2.close(); //

// Вариант считывания файла со всеми try-catch и finally - close
        // классический try-catch
        FileInputStream fin = null;
        try {
            fin = new FileInputStream("C:\\JavaEx\\intNotes.txt");

            int i = -1;
            while ((i = fin.read()) != -1) {
                System.out.print((char) i);
            }
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
        finally {
            try {
                if (fin != null) {
                    fin.close();
                }
            } catch(IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        // try-catch-with-resources
        try (FileInputStream fin1 = new FileInputStream("C:\\JavaEx\\intNotes.txt")) {
            int i =- 1;
            while ((i = fin1.read()) != -1) {
                System.out.print((char) i);
            }
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }


    }
}
