/*
Рассмотрим следующий код:
Writer writer = new OutputStreamWriter(outputStream, StandardCharsets.US_ASCII);
Что будет фактически записано в outputStream, если мы попытаемся вывести через writer символ,
не представимый в указанной ему кодировке ASCII?
Например, какой-нибудь иероглиф или символ кириллицы.

1) Байт 63 (ASCII-код символа '?')
2) Ни одного байта выведено не будет
3) Байт 32 (ASCII-код пробела)
4) Будет брошено исключение
5) Байт 0
 */
import java.io.*;
import java.nio.charset.StandardCharsets;

public class ex26_2 {
    public static void main(String[] args) throws IOException {
        // первый вариант
        OutputStream outputStream = new FileOutputStream("C:\\JavaEx\\IOStreamRWex26_2.txt");
        Writer writer = new OutputStreamWriter(outputStream, StandardCharsets.US_ASCII);
        writer.write("Щ");
        writer.flush();

        // второй вариант
        Writer writer1 = new OutputStreamWriter(System.out, StandardCharsets.US_ASCII);
        writer1.write("Щ");
        writer1.close();


    }
}
