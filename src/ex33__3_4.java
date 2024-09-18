import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ex33__3_4 {
    public static void main(String[] args) {
        // Чтение данных с консоли с кодировкой UTF-8
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8))) {
            // Чтение всего текста в строку
            String text = reader.lines().collect(Collectors.joining(" "));

            // Использование стримов для анализа слов
            List<String> topWords = Arrays.stream(text.toLowerCase().split("[^\\p{L}\\p{N}]+")) // Разбиваем по не-буквам и не-цифрам
                    .filter(word -> !word.isEmpty()) // Отфильтровываем пустые строки
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())) // Подсчитываем частоту слов
                    .entrySet().stream()
                    .sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder()) // Сортируем по убыванию частоты
                            .thenComparing(Map.Entry.comparingByKey())) // Сортируем лексикографически, если частота одинаковая
                    .limit(10) // Ограничиваем результат 10 элементами
                    .map(Map.Entry::getKey) // Получаем только ключи (слова)
                    .collect(Collectors.toList()); // Собираем в список

            // Выводим результат
            topWords.forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
