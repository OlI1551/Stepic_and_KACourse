/*
Магазину нужно сохранять информацию о продажах для каждого сотрудника.
Напишите метод Map getSalesMap (Reader reader) который принимает Reader содержащий строки вида:

Алексей 3000
Дмитрий 9000
Антон 3000
Алексей 7000
Антон 8000
Метод должен получить все строки из Readera и заполнить и вернуть карту
где ключом будет имя сотрудника, а значением сумма всех его продаж.

Пример ввода:
Алексей 3000
Дмитрий 9000
Антон 3000
Алексей 7000
Антон 8000
Пример вывода:
{Алексей=[10000], Дмитрий=[9000], Антон=[11000]}

Требования:
1. Должен быть метод public static Map<String, Long> getSalesMap(Reader reader)
2. Работа метода getSalesMap должна удовлетворять условию
*/

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ex31__2 {
    public static void main(String[] args) throws IOException {
        Reader reader = new FileReader("C:\\JavaEx\\Sales.txt");
        System.out.println(Sales.getSalesMap(reader));
    }
    public static class Sales {
        public Sales() {
        }

        public static Map<String, Long> getSalesMap(Reader reader) throws IOException {
            Map<String, Long> map = new HashMap<>();
            Scanner sc = new Scanner(reader);
            while (sc.hasNext()) {
                map.merge(sc.next(), sc.nextLong(), (a, b) -> a + b);
//                String[] pair = sc.nextLine().split(" ");
//                String name = pair[0];
//                long sales = Long.valueOf(pair[1]);
//                if (map.containsKey(name)) {
//                    map.put(name, map.get(name) + sales);
//                } else {
//                    map.put(name, sales);
//                }
            }
            return map;
        }
    }
}
/*
Решаю задачу на степике. Gри вводе решения платформа выдает ошибку: Неожиданное исключение:
java.lang.ArrayIndexOutOfBoundsException: Index 1 out of bounds for length 1.
Не понимаю, почему так происходит? Можете подсказать, что делаю не так?

Условие задачи: Магазину нужно сохранять информацию о продажах для каждого сотрудника.
Напишите метод Map getSalesMap(Reader reader) который принимает Reader содержащий строки вида:
Алексей 3000 Дмитрий 9000 Антон 3000 Алексей 7000 Антон 8000.
метод должен получить все строки из Readera,
заполнить и вернуть карту, где ключом будет имя сотрудника, а значением сумма всех его продаж.

//public class Sales {
//    public static Map<String, Long> getSalesMap(Reader reader) throws IOException {
//        Map<String, Long> map = new HashMap<>();
//        int fromReader = reader.read();
//        String line = String.valueOf(fromReader);
//        while(line != null) {
//            String name = line.trim().split(" ")[0];
//            Long salary = Long.parseLong(line.trim().split(" ")[1]);
//            if (map.containsKey(name)) {
//                map.put(name, map.get(name) + salary);
//            } else {
//                map.put(name, salary);
//            }
//        }
//        return map;
//    }
//}
        задан 13 мар 2021 в 20:01
        Похоже что проблема в этой строке Long salary = Long.parseLong(line.trim().split(" ")[1]); –

        Во-первых, зачем сплитить одну и ту же строку дважды. @PerfectVoyage так trim() только крайние пробелы убирает, это не должно влиять. –

        Неправильно работаете с Reader. Метод read согласно документации вычитывает только первый символ. Используйте, например, BufferedReader, чтобы считывать данные построчно:

        BufferedReader br = new BufferedReader(reader);
        String line = br.readLine();

        В вашем случае будет удобно воспользоваться классом Scanner для считывания данных, например:
        Scanner scanner = new Scanner(reader);
        String name = scanner.next();
        long salary = scanner.nextLong();
/*
package com.javarush.test.level19.lesson10.home02;
Самый богатый
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид: имя значение где [имя] - String,
[значение] - double. [имя] и [значение] разделены пробелом
Для каждого имени посчитать сумму всех его значений Вывести в консоль имена, у которых максимальная сумма
Имена разделять пробелом либо выводить с новой строки
Закрыть потоки
         Пример входного файла:
         Петров 0.501
         Иванов 1.35
         Петров 0.85
         Пример вывода:
         Петров


// import java.io.BufferedReader;
//         import java.io.FileReader;
//         import java.io.IOException;
//         import java.util.Map;
//         import java.util.TreeMap;
//
//public class Solution {
//    public static void main(String[] args) throws IOException {
//        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
//        Map<String,Double> map = new TreeMap<String, Double>();
//        Double max = Double.MIN_VALUE;
//        while (reader.ready())
//        {
//            String[] tmp = reader.readLine().split(" ");
//            if (!map.containsKey(tmp[0])) map.put(tmp[0], Double.parseDouble(tmp[1]));
//            else map.put(tmp[0],map.get(tmp[0])+Double.parseDouble(tmp[1]));
//        }
//        for (Map.Entry<String,Double> pair : map.entrySet())
//        {
//            max = Math.max(max,pair.getValue());
//        }
//        for (Map.Entry<String,Double> pair : map.entrySet())
//        {
//            if (pair.getValue().equals(max)) System.out.println(pair.getKey());
//        }
//        reader.close();
//    }
//}
*/
