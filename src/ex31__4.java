/*
Напишите программу, которая прочитает из System.in последовательность целых чисел, разделенных пробелами,
затем удалит из них все числа, стоящие на четных позициях,
и затем выведет получившуюся последовательность в обратном порядке в System.out.

Все числа влезают в int. Позиции чисел в последовательности нумеруются с нуля.

Все import объявлены за вас.

Пример ввода: 1 2 3 4 5 6 7 8 9 10
Пример вывода: 10 8 6 4 2

Требования:
1. Необходимо наличие метода public static void main(String[] args)
2. Программа должна читать данные из консоли
3. Программа должна выводить текст в консоль


Ох, ребятки, плюсую Maxim Maxim, также убил на эту задачу полтора дня.
Пробовал через счетчик для определения четного элемента, не смог.

Распишу алгоритм, делал через ArrayDeque, как и советуют в коментах, чтобы не пришлось переделывать после ревью.

1. Объявляем Scanner, объявляем ArrayDeque.
2. Пока(Scanner.есть_след_число()) {
              Scanner.некст;

              если(Scanner.есть_след_число()) {
                       ArrayDeque.addFirst(Scanner.след_число());
              }
    }
3.  Объявляем итератор, стрингбилдер.
4.  Пока(итератор.есть_след) {
               стрингбилдер.append(итератор.след));
               стрингбилдер.append(" "));
     }
5. Убираем последний пробел в стрингбилдере, загуглите, легко)
6. System.out.println(стрингбилдер.toString());

//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.LinkedList;
//
//public class ex31__3 {
//    public static void main(String[] args) {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        try {
//            String[] line = br.readLine().split(" ");
//            int i = line.length;
//            LinkedList<String> buff = new LinkedList<>();
//            for (int j = 1; j < i; ++j) {
//                if ((j & 1) != 0) {
//                    buff.addFirst(line[j]);
//                }
//            }
//            buff.forEach(e -> System.out.print(e + " "));
//        } catch (IOException e) {
//
//        } catch (NullPointerException e) {
//
//        }
//    }
//}
 */

import java.util.*;

public class ex31__4 {
    public static void main(String[] args) {
//        Deque<Integer> deque = new ArrayDeque<>();
//        Scanner scanner = new Scanner(System.in);
//        for (int i = 0; scanner.hasNextInt(); i++) {
//            if (i % 2 != 0) {
//                deque.add(scanner.nextInt());
//            } else {
//                scanner.nextInt();
//            }
//        }
//        System.out.println(Arrays.toString(deque.toArray())); // проверка - убрать эту стр
//
//        int size = deque.size();
//        for (int i = 0; i < size; i++) {
//            System.out.print(deque.removeLast() + " ");
//        }
//
        // Вариант2 от гпт
        // Чтение данных из консоли
        Scanner sc = new Scanner(System.in);

        // Считываем всю строку и делим на отдельные числа
        String input = sc.nextLine();
        String[] numbers = input.split("\\s+");

        // Список для хранения чисел, стоящих на нечетных позициях
        List<Integer> result = new ArrayList<>();

        // Добавляем только числа с нечетных позиций
        for (int i = 1; i < numbers.length; i += 2) {
            result.add(Integer.parseInt(numbers[i]));
        }

        // Выводим список в обратном порядке
        for (int i = result.size() - 1; i >= 0; i--) {
            System.out.print(result.get(i) + " ");
        }
    }
}