import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
public class ex24Example {
    public static void main(String[] args) {
//        Reader reader = null;
//        try {
//            reader = new FileReader(new File("someFileName")); // Reader имплементит интерфейс closable, его еще и закрыть потом надо в finally
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } finally { // но finally не знает, что такое reader, поэтому нам еще нужно reader вынести за блоки try-catch и приравнять его к null и потом в него помещать чтение файла
//            try {
//                reader.close(); // дальше close выбрасывает ошибки, еще надо уже его в try-catch - дополнительные блоки
//            } catch (IOException e) {
//                e.printStackTrace(); // одна строчка превращется в несколько блоков try-catch - сложная конструкция (12 строчек)
//            }
//        }
        // В 7 java решили убрать блок finally и закрывать все открытые ресурсы автоматически, добавили интерфейс AutoClosable, try-catch с ресурсами
        // в эту конструкцию можно помещать любые классы, которые имплементят AutoCloseable
//        try(Reader reader = new FileReader(new File("someFileName")), Writer writer = new Writer(new File("bla"))) {
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try(MyLaptop myClass = new MyLaptop()) { // ресурс будет закрываться, как только мы вышли за try
        } // try без catch и без finally (где ресурс закрывается)

        //streams
        Reader reader;
        Writer writer;
        InputStream inputStream;
        OutputStream outputStream;
        //jdbc
        Connection connection;
        Statement statement;
        ResultSet resultSet;
    }

    static class MyLaptop implements AutoCloseable {
        @Override
//        public void close() throws Exception {
        public void close() { // можно убрать выброс исключения

        }
    }
}
