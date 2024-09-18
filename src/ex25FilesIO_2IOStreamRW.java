/*
OutputStreamWriter — мост между классом OutputStream и классом Writer.
Символы, записанные в поток, преобразовываются в байты.

OutputStream outputStream = new FileOutputStream("c:\\data\\output.txt");
Writer outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
outputStreamWriter.write("Hello World");
outputStreamWriter.close();

InputStreamReader — аналог для чтения. При помощи методов класса Reader читаются байты из потока InputStream
и далее преобразуются в символы.
InputStream inputStream = new FileInputStream("c:\\data\\input.txt");
Reader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
int data = inputStreamReader.read();
while(data != -1){
    char theChar = (char) data;
    data = inputStreamReader.read();
}
inputStreamReader.close();
 */

/*
Класс InputStreamReader считывает символы из байтового входного потока.
Он считывает байты и декодирует их на символы с использованием указанной кодировки.
Уровень декодирования преобразует байты в символы в соответствии со стандартом кодирования.
Есть много доступных кодировок на выбор.

Класс InputStreamReader выполняет две задачи:
1) Прочитать входной поток клавиатуры.
2) Преобразовать потоки байтов в потоки символов.

Как преобразовать String в InputStream в Java?
ByteArrayInputStream делает трюк от Java 1.4
   InputStream is = new ByteArrayInputStream(inputString.getBytes(StandardCharsets.UTF_8));
Начиная с Java 1.7, StandardCharsets определяет константы для Charset, включая UTF-8.
Вы должны включить импорт
   Java.nio.charset.StandardCharsets; в вашем файле Java.
 Обратите внимание, что это предполагает, что вы хотите, чтобы InputStream представлял собой поток байтов,
представляющий исходную строку, кодированную как UTF-8.
 */

import java.io.*;
import java.util.*;
import java.nio.charset.StandardCharsets;

public class ex25FilesIO_2IOStreamRW {
    public static void main(String[] args) throws IOException {
        // Следующая программа Java получает InputStreamReader с клавиатуры
        try {
            InputStreamReader isReader = new InputStreamReader(System.in);
            BufferedReader bReader = new BufferedReader(isReader);
            System.out.println("Enter anything......");
            String data = bReader.readLine();
            System.out.println("You Entered.... " + data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Следующая программа Java получает InputStreamReader из файла
        try {
            InputStream is = new FileInputStream("C:\\JavaEx\\filePoem.txt");
            Reader isr = new InputStreamReader(is);
            int data = isr.read();
            while (data != -1) {
                data = isr.read();
                char chr = (char) data;
                System.out.print(chr);
            }
            isr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Следующая программа Java читает строку как InputStream
        try {
            String inputString = "This is a test !!";
            InputStream is = new ByteArrayInputStream(inputString.getBytes(StandardCharsets.UTF_8));
            Reader isr = new InputStreamReader(is);
            int data = isr.read();
            while (data != -1) {
                data = isr.read();
                char chr = (char) data;
                System.out.print(chr);
            }
            isr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
/*
OutputStreamWriter записывает символы в выходной поток, переводя символы в байты
в соответствии с указанной кодировкой символов.
Каждый OutputStreamWriter имеет собственный CharToByteConverter и, таким образом,
является мостом от потоков символов к потокам байтов.
   OutputStream  os = new FileOutputStream("d:\\test.txt");
   Writer osr = new OutputStreamWriter(os);
Персонажи, написанные на нем, кодируются в байты с использованием указанной кодировки.
Кодировка, используемая OutputStreamWriter, может быть указана по имени, путем предоставления CharToByteConverter
или путем принятия кодировки по умолчанию, которая определяется файлом system.encoding.
Он имеет альтернативные конструкторы, которые позволяют вам указать набор символов
(например: ISO-Latin1, UTF-8, UTF-16), чтобы использовать их для преобразования написанных символов в байты,
записанные в базовый OutputStream.

Когда вы используете Reader/Writer и когда поток?
Если вы имеете дело с символами Unicode, отличными от ASCII, например.
Если вы работаете с двоичными данными (например, с изображением), используйте Streams.
Если вы имеете дело с обычным текстом ASCII (традиционные 0-127 символов ) вы также можете использовать поток.
 */
        try {
            OutputStream  os = new FileOutputStream("C:\\JavaEx\\test.txt");
            Writer osr = new OutputStreamWriter(os);
            osr.write("Java Stream handling !!");
            osr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
