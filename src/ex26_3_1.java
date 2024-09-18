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
import java.util.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class ex26_3_1 {
    public static void main(String[] args) throws IOException {
        byte[] bb = new byte[] {48, 49, 50, 51}; //0, 1, 2, 3
        ByteArrayInputStream bis = new ByteArrayInputStream(bb);
        System.out.println(readAsString(bis, StandardCharsets.US_ASCII));
    }

    public static String readAsString(InputStream inputStream, Charset charset) throws IOException {
        // вариант 2
/*
Рисуем InputStreamReader ему передаём inputStream и charset (в этот момент автоматически используется кодировка,
не пытайтесь её куда-нибудь пихать). Создаём переменную и билдер.
Читаем по байтам, прочитанное приводим к чар, апендим это с билдером,
переводим билдер в строку и эту строку возвращаем. Всё просто.

Можно и без StringBuilder, через ByteArrayOutputStream
*/
        InputStreamReader reader = new InputStreamReader(inputStream, charset); // это дерьмо будет считывать байты (числа типа byte в переменную типа int,
        int c = 0; // автоматически преобразовывая byte к int!!!
        StringBuilder sb = new StringBuilder();
        while ((c = reader.read()) != -1) {
            sb.append((char) c); // а этот сраный StringBuilder принимает только char или String!!! подтому int преобразуем в char явным образом, "сужаем"
        }
        return(sb.toString());
    }
}
