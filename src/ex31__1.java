/*
Реализуйте метод symmetricDifference, вычисляющий симметрическую разность двух множеств.
Метод должен возвращать результат в виде нового множества. Изменять переданные в него множества не допускается.

Пример ввода: [1, 2, 3} и {0, 1, 2]
Пример вывода: [0, 3]

Требования:
1. должен быть метод public static Set symmetricDifference(Set set1, Set set2)
2. метод symmetricDifference не должен изменять исходные множества
3. метод symmetricDifference должен возвращать корректный результат


Два сэта, в оба добавляем по коллекции.
Из первого сэта удаляем все повторяющиеся элементы со второй коллекцией
Из второго сэта удаляем все повторяющиеся элементы с первой коллекцией
Добавляем в первый сет все элементы из ВТОРОГО сэта

Аналог: баблсорт

https://www.youtube.com/watch?v=rWxC6ZkCtSg
https://www.youtube.com/watch?v=jcAiM1VVR_E

1. Создать 2 новых сета: сет11 = новый хешсет(сет1) и второй.
2. из н. первого удалитьВсё(второй) и наоборот.
3. в н. первый добавитьВсё(второй).
4. вернуть первый.

Очень помогло видео https://www.youtube.com/watch?v=izmLgjAg6co

Объявил 2 параметризированных сета, присвоил им хеш двух исходных сетов.
Из первого убрал второе.
Из второго убрал исходное первое.
Сложил и вернул.

https://www.youtube.com/watch?v=Gn5b3WDbSUE&ab_channel=%D0%9F%D1%80%D0%BE%D0%B3%D1%80%D0%B0%D0%BC%D0%B0%D0%BD%D0%B8%D1%8F
После просмотра сразу сделаете задание!
https://ru.stackoverflow.com/questions/591536/%D0%A7%D1%82%D0%BE-%D0%B7%D0%B0-%D0%BC%D0%B5%D1%82%D0%BE%D0%B4%D1%8B-removeall-%D0%B8-retainall
Заур Трегулов выручил. Курс "Java получи чёрный пояс" - урок 30. Введение в Set. HashSet. Инфа была дана доходчиво, дальше дело техники, сделал с первого раза )
1. Смысл какой:
A △ B = (A – B) ∪ (B – A)
или:
A △ B = (A ∪ B) - (A ∩ B)
2:https://www.geeksforgeeks.org/set-in-java/
Все тут наглядно показано как это работает. Только осталось посмотреть пункт 1й  и передалать :)
нужно помнить, что когда присваиваешь одну коллекцию другой, то все изменения в одной, скажутся и на другой. Мне знание этого помогло решить.
два созданных Сета, два removeAll, один addAll
В новый set кинь оба, затем итератором проверь если и в первом и во втором есть похожие, удаляй. Остаток возвращай в новом сете.

Мой вариант решения в 7 строчек, из которых последняя это return:
1) инициализируем передаваемые сеты;
2) дополнительно создаем копию сета 1;
3) пользуемся .removeAll два обоих сетов (при этом помним, что сеты перезаписываются после данной команды - для этого и нужна копия одного из сетов);
4) добавляем в один из сетов другой (помним основное назначение сета);

Циклы не нужны, используем методы HashSet
Помог вот этот видос https://www.youtube.com/watch?v=izmLgjAg6co
 */
import java.util.HashSet;
import java.util.Set;

public class ex31__1 {
    public static void main(String[] args) {

        Set<Integer> s1 = new HashSet<>();
        s1.add(1);
        s1.add(2);
        s1.add(3);

        HashSet<Integer> s2 = new HashSet<>();
        s2.add(0);
        s2.add(1);
        s2.add(2);

        Set<Integer> result = symmetricDifference(s1,s2);
        System.out.println(result);

    }
//    public static <T> Set<T> symmetricDifference(Set<? extends T> set1, Set<? extends T> set2) {
//        Set<T> commonSet = new HashSet<T>(set1);
//        commonSet.addAll(set2);
//        Set<T> temp = new HashSet<T>(set1);
//        temp.retainAll(set2);
//        commonSet.removeAll(temp);
//        return commonSet;
//    }

    public static <T> Set<T> symmetricDifference(Set<? extends T> set1, Set<? extends T> set2) {
        // Создаем новое множество, чтобы не изменять исходные
        Set<T> result = new HashSet<>(set1);
        // Добавляем элементы из второго множества
        result.addAll(set2);
        // Создаем множество для пересечения
        Set<T> intersection = new HashSet<>(set1);
        // Оставляем только элементы, которые есть и в первом, и во втором множестве
        intersection.retainAll(set2);
        // Удаляем элементы пересечения из результата
        result.removeAll(intersection);
        return result;
    }
//    public static <T> Set<T> symmetricDifference(Set<? extends T> set1, Set<? extends T> set2) {
//        A △ B = (A ∪ B) - (A ∩ B)
//        Set<T> symmetricDiff = new HashSet<T>(set1);
//        symmetricDiff.addAll(set2);
//        Set<T> tmp = new HashSet<T>(set1);
//        tmp.retainAll(set2);
//        symmetricDiff.removeAll(tmp);
//        return symmetricDiff;
//    }
}
