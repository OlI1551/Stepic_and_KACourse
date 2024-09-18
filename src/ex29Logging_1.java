/*
System.err.println
Первым и самым примитивным способом логгирования был метод System.err.println.
Думаю, комментарии излишние, достаточно взглянуть на приведенный ниже код:
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class ex29Logging_1 {
    public static void main(String[] args) throws FileNotFoundException {
        // Определяем файл в который будем писать лог
        System.setErr(new PrintStream(new File("C:\\JavaEx\\log1.txt")));
// Выводим сообщения
        System.err.println("Сообщение 1");
        System.err.println("Сообщение 2");
// Выводим сообщение об ошибке
        try {
            throw new Exception("Сообщение об ошибке");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
