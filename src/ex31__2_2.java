import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ex31__2_2 {
    public static void main(String[] args) {
        // Пример использования метода с тестовыми данными
        String data = "Алексей 3000\nДмитрий 9000\nАнтон 3000\nАлексей 7000\nАнтон 8000\n";
        Reader reader = new StringReader(data);

        Map<String, Long> salesMap = getSalesMap(reader);
        System.out.println(salesMap); // Ожидаемый вывод: {Алексей=10000, Дмитрий=9000, Антон=11000}
    }
    public static Map<String, Long> getSalesMap(Reader reader) {
        Map<String, Long> salesMap = new HashMap<>();
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;

        try {
            // Чтение каждой строки
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split("\\s+"); // Разделяем строку по пробелам
                String name = parts[0];
                long sale = Long.parseLong(parts[1]);

                // Суммируем продажи для каждого сотрудника
                salesMap.put(name, salesMap.getOrDefault(name, 0L) + sale);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return salesMap;
    }
//    public static Map<String, Long> getSalesMap(Reader reader) {
//        Map<String, Long> salesMap = new HashMap<>();
//        Scanner sc = new Scanner(reader);
//        while (sc.hasNext()) {
//            salesMap.merge(sc.next(), sc.nextLong(), (a, b) -> a + b);
//        }
//        return salesMap;
//    }
}
