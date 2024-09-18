/*
При работе со сложными объектами удобно использовать сериализацию - запись состояния объекта в поток.
Соответственно, процесс извлечения или восстановления состояния объекта из потока называется десериализацией.
Сериализация - это процесс преобразования объектов в последовательность байт!!!

Сериализовать можно только те объекты, которые реализуют интерфейс Serializable.
Этот интерфейс не определяет никаких методов, просто он служит указателем системе,
что объект, реализующий его, может быть сериализован.

Класс ObjectOutputStream используется для сериализации объектов в поток. Он записывает объект в поток.
Для создания объекта ObjectOutputStream в конструктор передается поток, в который производится запись:
1) ObjectOutputStream(OutputStream out)

Для записи данных ObjectOutputStream использует ряд методов, среди которых можно выделить следующие:
void close(): закрывает поток
void flush(): очищает буфер и сбрасывает его содержимое в выходной поток
void write(byte[] buf): записывает в поток массив байтов
void write(int val): записывает в поток один младший байт из val
void writeBoolean(boolean val): записывает в поток значение boolean
void writeByte(int val): записывает в поток один младший байт из val
void writeChar(int val): записывает в поток значение типа char, представленное целочисленным значением
void writeDouble(double val): записывает в поток значение типа double
void writeFloat(float val): записывает в поток значение типа float
void writeInt(int val): записывает целочисленное значение int
void writeLong(long val): записывает значение типа long
void writeShort(int val): записывает значение типа short
void writeUTF(String str): записывает в поток строку в кодировке UTF-8
void writeObject(Object obj): записывает в поток отдельный объект
Эти методы охватывают весь спектр данных, которые можно сериализовать.

Класс ObjectInputStream отвечает за обратный процесс - чтение ранее сериализованных данных из потока.
В конструкторе он принимает ссылку на поток ввода:
1) ObjectInputStream(InputStream in)
Функционал ObjectInputStream сосредоточен в методах, предназначенных для чтения различных типов данных:
void close(): закрывает поток
int skipBytes(int len): пропускает при чтении несколько байт, количество которых равно len
int available(): возвращает количество байт, доступных для чтения
int read(): считывает из потока один байт и возвращает его целочисленное представление
boolean readBoolean(): считывает из потока одно значение boolean
byte readByte(): считывает из потока один байт
char readChar(): считывает из потока один символ char
double readDouble(): считывает значение типа double
float readFloat(): считывает из потока значение типа float
int readInt(): считывает целочисленное значение int
long readLong(): считывает значение типа long
short readShort(): считывает значение типа short
String readUTF(): считывает строку в кодировке UTF-8
Object readObject(): считывает из потока объект

По умолчанию сериализуются все переменные объекта.
Однако, возможно, мы хотим, чтобы некоторые поля были исключены из сериализации.
Для этого они должны быть объявлены с модификатором transient.
Например, исключим из сериализации объекта Person переменные height и married:

class Person implements Serializable{

    private String name;
    private int age;
    private transient double height;
    private transient boolean married;

    Person(String n, int a, double h, boolean m){

        name=n;
        age=a;
        height=h;
        married=m;
    }
    String getName() {return name;}
    int getAge(){ return age;}
    double getHeight(){return height;}
    boolean getMarried(){return married;}
}
 */
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ex27Serialization {
    //@SuppressWarnings("unchecked")
    public static void main(String[] args) {
        // Например, сохраним в файл один объект класса Person:
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\JavaEx\\person.dat"))) {
            myPerson p = new myPerson("Sam", 33, 178, true);
            oos.writeObject(p);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        // Извлечем выше сохраненный объект Person из файла:
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\JavaEx\\person.dat"))) {
            myPerson p = (myPerson) ois.readObject();
            System.out.printf("Name: %s \t Age: %d \n", p.getName(), p.getAge());
        } catch (Exception ex) {

            System.out.println(ex.getMessage());
        }

        // запишем и восстановим из файла целый список одинаковых объектов
        String filename = "C:\\JavaEx\\people.dat";
        // создадим список объектов, которые будем записывать
        ArrayList<myPerson> people = new ArrayList<myPerson>();
        people.add(new myPerson("Tom", 30, 175, false));
        people.add(new myPerson("Sam", 33, 178, true));

        try (ObjectOutputStream oos1 = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos1.writeObject(people);
            System.out.println("File has been written");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        // десериализация в новый список
        ArrayList<myPerson> newPeople = new ArrayList<myPerson>();
        try (ObjectInputStream ois1 = new ObjectInputStream(new FileInputStream(filename))) {

            newPeople = ((ArrayList<myPerson>) ois1.readObject());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        for (myPerson p : newPeople)
            System.out.printf("Name: %s \t Age: %d \n", p.getName(), p.getAge());
    }
}

class myPerson implements Serializable {
    private String name;
    private int age;
    private double height;
    private boolean married;

    myPerson(String n, int a, double h, boolean m) {
        name = n;
        age = a;
        height = h;
        married = m;
    }

    String getName() {
        return name;
    }

    int getAge() {
        return age;
    }

    double getHeight() {
        return height;
    }

    boolean getMarried() {
        return married;
    }
}
