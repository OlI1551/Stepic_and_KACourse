/*
Но поскольку работать с байтами не очень удобно, то для работы с потоками символов были добавлены
абстрактные классы Reader (для чтения потоков символов) и Writer (для записи потоков символов).

Абстрактный класс Reader предоставляет функционал для чтения текстовой информации. Рассмотрим его основные методы:
absract void close(): закрывает поток ввода
int read(): возвращает целочисленное представление следующего символа в потоке. Если таких символов нет, и достигнут конец файла, то возвращается число -1
int read(char[] buffer): считывает в массив buffer из потока символы, количество которых равно длине массива buffer. Возвращает количество успешно считанных символов. При достижении конца файла возвращает -1
int read(CharBuffer buffer): считывает в объект CharBuffer из потока символы. Возвращает количество успешно считанных символов. При достижении конца файла возвращает -1
absract int read(char[] buffer, int offset, int count): считывает в массив buffer, начиная со смещения offset, из потока символы, количество которых равно count
long skip(long count): пропускает количество символов, равное count. Возвращает число успешно пропущенных символов

Класс Writer определяет функционал для всех символьных потоков вывода. Его основные методы:
Writer append(char c): добавляет в конец выходного потока символ c. Возвращает объект Writer
Writer append(CharSequence chars): добавляет в конец выходного потока набор символов chars. Возвращает объект Writer
abstract void close(): закрывает поток
abstract void flush(): очищает буферы потока
void write(int c): записывает в поток один символ, который имеет целочисленное представление
void write(char[] buffer): записывает в поток массив символов
absract void write(char[] buffer, int off, int len): записывает в поток только несколько символов из массива buffer. Причем количество символов равно len, а отбор символов из массива начинается с индекса off
void write(String str): записывает в поток строку
void write(String str, int off, int len): записывает в поток из строки некоторое количество символов, которое равно len, причем отбор символов из строки начинается с индекса off

Функционал, описанный классами Reader и Writer, наследуется непосредственно классами символьных потоков,
в частности классами FileReader и FileWriter соответственно, предназначенными для работы с текстовыми файлами.

Класс FileWriter является производным от класса Writer. Он используется для записи текстовых файлов.
Чтобы создать объект FileWriter, можно использовать один из следующих конструкторов:
1) FileWriter(File file)
2) FileWriter(File file, boolean append)
3) FileWriter(FileDescriptor fd)
4) FileWriter(String fileName)
5) FileWriter(String fileName, boolean append)
Так, в конструктор передается либо путь к файлу в виде строки, либо объект File,
который ссылается на конкретный текстовый файл.
Параметр append указывает, должны ли данные дозаписываться в конец файла
(если параметр равен true), либо файл должен перезаписываться.

Класс FileReader наследуется от абстрактного класса Reader и предоставляет функциональность для чтения текстовых файлов.
Для создания объекта FileReader мы можем использовать один из его конструкторов:
1) FileReader(String fileName)
2) FileReader(File file)
3) FileReader(FileDescriptor fd)

 */
import java.io.*;
import java.util.Arrays;

public class ex25FilesIO_2FileRW {
    public static void main(String[] args) throws IOException {
        // Запишем в файл какой-нибудь текст:
        try (FileWriter writer = new FileWriter("C:\\JavaEx\\notes3.txt", false)) {
            // запись всей строки
            String text = "Hello Gold!";
            writer.write(text);
            // запись по символам
            writer.append('\n');
            writer.append('E');

            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        // В конструкторе использовался параметр append со значением false - то есть файл будет перезаписываться.
        // Затем с помощью методов, определенных в базовом классе Writer производится запись данных.

        // Используя методы, определенные в базом классе Reader, произведем чтение файла:
        try (FileReader reader = new FileReader("C:\\JavaEx\\notes3.txt")) {
            // читаем посимвольно
            int c;
            while((c = reader.read()) != -1){
                System.out.print((char) c);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        // Также мы можем считывать в промежуточный буфер из массива символов:
        try (FileReader reader = new FileReader("notes3.txt")) {
            char[] buf = new char[256];
            int c;
            while ((c = reader.read(buf)) > 0) {
                if (c < 256) {
                    buf = Arrays.copyOf(buf, c);
                }
                System.out.print(buf);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        // В данном случае считываем последовательно символы из файла в массив из 256 символов,
        // пока не дойдем до конца файла в этом случае метод read возвратит число -1.
        // Поскольку считанная порция файла может быть меньше 256 символов (например, в файле всего 73 символа),
        // и если количество считанных данных меньше размера буфера (256),
        // то выполняем копирование массива с помощью метода Arrays.copy.
        // То есть фактически обрезаем массив buf, оставляя в нем только те символы, которые считаны из файла.
    }
}
