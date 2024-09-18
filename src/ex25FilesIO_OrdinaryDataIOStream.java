/*
Классы DataOutputStream и DataInputStream позволяют записывать и считывать данные примитивных типов.

Класс DataOutputStream представляет поток вывода и предназначен для записи данных примитивных типов,
таких, как int, double и т.д. Для записи каждого из примитивных типов предназначен свой метод:
writeBoolean(boolean v) : записывает в поток булевое однобайтовое значение
writeByte(int v): записывает в поток 1 байт, которые представлен в виде целочисленного значения
writeChar(int v): записывает 2-байтовое значение char
writeDouble(double v): записывает в поток 8-байтовое значение double
writeFloat(float v): записывает в поток 4-байтовое значение float
writeInt(int v): записывает в поток целочисленное значение int
writeLong(long v): записывает в поток значение long
writeShort(int v): записывает в поток значение short
writeUTF(String str): записывает в поток строку в кодировке UTF-8

Класс DataInputStream действует противоположным образом - он считывает из потока данные примитивных типов.
Соответственно для каждого примитивного типа определен свой метод для считывания:
boolean readBoolean(): считывает из потока булевое однобайтовое значение
byte readByte(): считывает из потока 1 байт
char readChar(): считывает из потока значение char
double readDouble(): считывает из потока 8-байтовое значение double
float readFloat(): считывает из потока 4-байтовое значение float
int readInt(): считывает из потока целочисленное значение int
long readLong(): считывает из потока значение long
short readShort(): считывает значение short
String readUTF(): считывает из потока строку в кодировке UTF-8
int skipBytes(int n): пропускает при чтении из потока n байтов

Здесь мы последовательно записываем в файл данные объекта Person.
Объект DataOutputStream в конструкторе принимает поток вывода:
DataOutputStream (OutputStream out). В данном случае в качестве потока вывода используется объект FileOutputStream,
поэтому вывод будет происходить в файл.
И с помощью выше рассмотренных методов типа writeUTF() производится запись значений в бинарный файл.

Затем происходит чтение ранее записанных данных. Объект DataInputStream в конструкторе принимает поток для чтения:
DataInputStream(InputStream in). Здесь таким потоком выступает объект FileInputStream
 */
import java.io.*;
import java.io.IOException;

public class ex25FilesIO_OrdinaryDataIOStream {
    public static void main(String[] args) throws IOException {
        Person1 tom = new Person1 ("Tom", 34, 1.68, false);
        // запись в файл
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("C:\\JavaEx\\data.bin"))) {
            // записываем значения
            dos.writeUTF(tom.name);
            dos.writeInt(tom.age);
            dos.writeDouble(tom.height);
            dos.writeBoolean(tom.married);
            System.out.println("File has been written");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        // обратное считывание из файла
        try (DataInputStream dos = new DataInputStream(new FileInputStream("C:\\JavaEx\\data.bin"))) {
            // записываем значения
            String name = dos.readUTF();
            int age = dos.readInt();
            double height = dos.readDouble();
            boolean married = dos.readBoolean();
            System.out.printf("Name: %s  Age: %d  Height: %f  Married: %b",
                    name, age, height, married);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
class Person1 {
    public String name;
    public int age;
    public double height;
    public boolean married;

    public Person1 (String n, int a, double h, boolean m)
    {
        this.name=n;
        this.height=h;
        this.age=a;
        this.married=m;
    }
}