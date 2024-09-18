import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ex33__3_4_v2 {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8))) {
            String text = reader.lines().collect(Collectors.joining(" "));

            List<String> topWords = Arrays.stream(text.toLowerCase().split("[^\\p{L}\\p{N}]+"))
                    .filter(word -> !word.isEmpty())
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                    .entrySet().stream()
                    .sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder())
                            .thenComparing(Map.Entry.comparingByKey()))
                    .limit(10)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());

            topWords.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
