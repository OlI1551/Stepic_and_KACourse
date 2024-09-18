/*
Урок с кодом
Напишите метод, находящий в стриме минимальный и максимальный элементы в соответствии порядком,
заданным Comparator'ом.

Найденные минимальный и максимальный элементы передайте в minMaxConsumer следующим образом:
minMaxConsumer.accept(min, max);

Если стрим не содержит элементов, то вызовите:
minMaxConsumer.accept(null, null);

Требования:
1. Должен быть метод public <T> void findMinMax(Stream<? extends T> stream, Comparator<? super T> order, BiConsumer<? super T, ? super T> minMaxConsumer)
2. Метод должен находить минимум и максимум в потоке, с помощью order.
3. Полученные данные должны быть записаны minMaxConsumer, согласно условию
 */

import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ex33__2 {
    public static void main(String[] args) {
        Stream<Integer> stream = Stream.of(123,54325,456,567,768,7689,78,35,132);
        Comparator<? super Integer> comparator = Integer::compare;
        BiConsumer<? super Integer, ? super Integer> biConsumer = (x1, x2) -> System.out.println("min: " + x1 + "\nmax: " + x2);
        findMinMax(stream, comparator, biConsumer);

    }
    public static <T> void findMinMax(
            Stream<? extends T> stream,
            Comparator<? super T> order,
            BiConsumer<? super T, ? super T> minMaxConsumer) {
/*
        List<T> list = stream.collect(Collectors.toList());
        if (list.isEmpty()) {
            minMaxConsumer.accept(null, null);
        } else {
            minMaxConsumer.accept(list.stream().min(order).get(), list.stream().max(order).get());
        }
*/
        stream.collect(Collectors.teeing(
                // первый коллектор
                Collectors.minBy(order),
                // второй коллектор
                Collectors.maxBy(order),
                // 3 элемент - слияние - требует и принимает BiFunction, автоматический вывод типа здесь не работает
                // merger = это функция, которая должна вернуть потом результат
                (min, max) -> {
                    minMaxConsumer.accept(min.orElse(null), max.orElse(null));
                    return minMaxConsumer;
                }
        ));
    }
}
/*
Для тех, кто хочет решить через teeing:
Вы работаете с изначальным стримом, преобразовывая его в коллекцию самым прямым его методом,
сразу в скобках после вызова метода вы используете teeing (статический метод),
ему в параметры передаете поиск минимального и максимального значения, используя стат. методы от класса Collectors (IDEA подскажет).
Третьим же параметром teeing просит "merger". Этот самый merger принимает BiFunction.
На какую-то удачу я подумал, что к значению BiFunction подойдет наш BiConsumer и с помощью функции (лямбда-выражения) я его туда запихнул.
Для лямбда-выражения используется два значения min и max, которые мы ищем, в теле лямбды мы можем использовать то, что нам дано в условии.
Надеюсь, я что-то сам понял, а кто-то сэкономит драгоценное время, прочитав мой комментарий.
 */
/*
У стрима есть методы min() и max(), но воспользоваться ими «в лоб» нельзя, т.к. оба являются терминальными операциями.
Использовав одну из них, вторую уже вызвать нельзя — стрим бросит IllegalStateException.

Некоторые обходили это ограничение, собирая элементы стрима в коллекцию,
из которой можно было получить новый стрим столько раз, сколько нужно.
Это решение проходит тесты, но его большой недостаток — необходимость хранить в памяти все элементы стрима,
которых может быть очень много. Мы заранее не знаем, сколько их будет.

Оптимальным решением является нахождение минимума и максимума за один проход по стриму
без использования промежуточного хранилища элементов.
Обратите внимание, что решение не использует приведение типа к (T).
Благодаря этому отсутствуют предупреждения компилятора о небезопасном приведении типов.
В других решениях, где Consumer реализован как лямбда-выражение или как анонимный класс,
избежать предупреждений было бы гораздо сложнее.

P.S. В лекциях были рассмотрены только последовательные стримы,
однако в Java бывают еще и параллельные стримы, обрабатывающие свои элементы одновременно в нескольких потоках.
Это решение для параллельных стримов не подходит. Но это уже совсем другая история...

public static <T> void findMinMax(
        Stream<? extends T> stream,
        Comparator<? super T> order,
        BiConsumer<? super T, ? super T> minMaxConsumer) {

    MinMaxFinder<T> minMaxFinder = new MinMaxFinder<>(order);
    stream.forEach(minMaxFinder);
    minMaxConsumer.accept(minMaxFinder.min, minMaxFinder.max);
}


private static class MinMaxFinder<T> implements Consumer<T> {

    private final Comparator<? super T> order;
    T min;
    T max;

    private MinMaxFinder(Comparator<? super T> order) {
        this.order = order;
    }

    @Override
    public void accept(T t) {
        if (min == null || order.compare(t, min) < 0) {
            min = t;
        }
        if (max == null || order.compare(max, t) < 0) {
            max = t;
        }
    }
}
 */

