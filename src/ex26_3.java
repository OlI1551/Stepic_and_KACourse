/*
Реализуйте метод, который зачитает данные из InputStream и преобразует их в строку, используя заданную кодировку.
Пример:
InputStream последовательно возвращает четыре байта: 48 49 50 51.
Метод, вызванный для такого InputStream и кодировки ASCII, должен вернуть строку: "0123".
Требования:
1. Метод должен быть публичным.
2. Сигнатура метода должна быть: readAsString(InputStream inputStream, Charset charset)
 */

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class ex26_3 {
    public static void main(String[] args) throws IOException {
        OutputStream outputStream = new FileOutputStream("C:\\JavaEx\\IOStreamRWex26_2.txt");
        Writer writer = new OutputStreamWriter(outputStream, StandardCharsets.US_ASCII);
        writer.write("48 49 50 51");
        writer.flush();

        InputStream inputStream = new FileInputStream("C:\\JavaEx\\IOStreamRWex26_2.txt");
        Charset charset = StandardCharsets.US_ASCII;
        System.out.println(readAsString(inputStream, charset));
    }

    public static String readAsString(InputStream inputStream, Charset charset) throws IOException {
        // вариант 1
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line).append("\n");
        }
        bufferedReader.close();
        return stringBuilder.toString();
    }
}
