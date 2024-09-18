/*
Для оптимизации операций ввода-вывода используются буферизуемые потоки.
Эти потоки добавляют к стандартным специальный буфер в памяти,
с помощью которого повышается производительность при чтении и записи потоков.

Класс BufferedInputStream накапливает вводимые данные в специальном буфере
без постоянного обращения к устройству ввода. Класс BufferedInputStream определяет два конструктора:
1) BufferedInputStream(InputStream inputStream)
2) BufferedInputStream(InputStream inputStream, int bufSize)
Первый параметр - это поток ввода, с которого данные будут считываться в буфер. Второй параметр - размер буфера.

Класс BufferedInputStream в конструкторе принимает объект InputStream.
В данном случае таким объектом является экземпляр класса ByteArrayInputStream.
Как и все потоки ввода BufferedInputStream обладает методом read(),
который считывает данные. И здесь мы считываем с помощью метода read каждый байт из массива buffer.
Фактические все то же самое можно было сделать и с помощью одного ByteArrayInputStream,
не прибегая к буферизированному потоку.
Класс BufferedInputStream просто оптимизирует производительность при работе с потоком ByteArrayInputStream.
Естественно вместо ByteArrayInputStream может использоваться любой другой класс, который унаследован от InputStream.
 */
import java.io.*;

public class ex25FilesIO_OrdinaryBufferedIOStream {
    public static void main(String[] args) throws IOException {
        String text = "Happy New Year!";
        byte[] buffer = text.getBytes(); // считываем строку в буфер
        ByteArrayInputStream in = new ByteArrayInputStream(buffer); // считываем буфер в объект InputStream

        try (BufferedInputStream bis = new BufferedInputStream(in)) { // загружаем объект в дополнительный буфер
            int c;
            while ((c = bis.read()) != -1){ // из дополнительного буфера считываем объект и посимвольно выводим в консоль
                System.out.print((char) c);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

/*
Класс BufferedOutputStream аналогично создает буфер для потоков вывода.
Этот буфер накапливает выводимые байты без постоянного обращения к устройству.
И когда буфер заполнен, производится запись данных.
BufferedOutputStream определяет два конструктора:
1) BufferedOutputStream(OutputStream outputStream)
2) BufferedOutputStream(OutputStream outputStream, int bufSize)
Первый параметр - это поток вывода, который унаследован от OutputStream, а второй параметр - размер буфера.

Класс BufferedOutputStream в конструкторе принимает в качестве параметра объект OutputStream
- в данном случае это файловый поток вывода FileOutputStream.
И также производится запись в файл.
Опять же BufferedOutputStream не добавляет много новой функциональности, он просто оптимизирует действие потока вывода.
 */
        String text1 = "Hello world!"; // строка для записи
        try (FileOutputStream out = new FileOutputStream("notes.txt"); BufferedOutputStream bos = new BufferedOutputStream(out)) {
            // перевод строки в байты
            byte[] buffer1 = text1.getBytes();
            bos.write(buffer1, 0, buffer1.length);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
