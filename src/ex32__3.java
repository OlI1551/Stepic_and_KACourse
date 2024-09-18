/*
Урок с кодом

Задание:
Дан предикат condition и две функции ifTrue и ifFalse.
Напишите метод ternaryOperator, который из них построит новую функцию,
возвращающую значение функции ifTrue, если предикат выполнен, и значение ifFalse иначе.

Рассмотрим пример использования метода.
Его можно было все свернуть в одну строчку, но для наглядности все элементы вынесены в отдельные переменные.
Predicate<Object> condition = Objects::isNull;
Function<Object, Integer> ifTrue = obj -> 0;
Function<CharSequence, Integer> ifFalse = CharSequence::length;
Function<String, Integer> safeStringLength = ternaryOperator(condition, ifTrue, ifFalse);
Результирующая функция будет для нулевых ссылок на String возвращать 0, а для ненулевых ссылок возвращать длину строки.

Все импорты объявлены за вас.

Требования:
1. Должен быть метод
public static <T, U> Function<T, U> ternaryOperator(
        Predicate<? super T> condition,
        Function<? super T, ? extends U> ifTrue,
        Function<? super T, ? extends U> ifFalse)
2. Метод должен возвращать функцию
3. Используйте лямбда-выражение
 */
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public class ex32__3 {
    public static void main(String[] args) {
        Predicate<Object> condition = Objects::isNull;
        Function<Object, Integer> ifTrue = obj -> 0;
        Function<CharSequence, Integer> ifFalse = CharSequence::length;
        Function<String, Integer> safeStringLength = ternaryOperator(condition, ifTrue, ifFalse);

//        Predicate<Object> condition = obj1 -> Objects.isNull(obj1);
//        Function<Object, Integer> ifTrue = obj -> 0;
//        Function<CharSequence, Integer> ifFalse = charSequence -> charSequence.length();

        System.out.println(safeStringLength.apply("Hello"));
        System.out.println(safeStringLength.apply(null));
    }
    public static <T, U> Function<T, U> ternaryOperator(
            Predicate<? super T> condition,
            Function<? super T, ? extends U> ifTrue,
            Function<? super T, ? extends U> ifFalse) {
        return x -> condition.test(x) ? ifTrue.apply(x) : ifFalse.apply(x);
    }
}
/*
Решила, не совсем поняла как. Попытаюсь расписать, чтобы стало понятнее самой и может еще кому другому.
1)Метод должен вернуть экземпляр Function, а значит можно создать экземпляр анонимного класса,
реализующего интерфейс Function, либо использовать лямбда выражение.
соответсвенно проще всего вернуть лямда выражение. Давайте это запомним.

2)У нас с вами есть интерфейс Predicate под названием condition,
который возвращент boolean значение при помощи метода test.
public interface Predicate<T>  {
    boolean test (Tt); // это из джавадока
У нас есть 2 интерффейса Function под названием ifTrue, ifFalse.
У интерфейса Function есть метод apply.Function принимает на вход переменную одного типа, а возвращает другого типа.

3)Помним про тернарный оператор (условие) ? (если true, то делаем это) : (если false, то делаем это).
Теперь подставляем (condition.test(t)) ? (ifTrue.apply(t)) : .........тут сами закончите.
А еще помним что надо вернуть в виде лямбда выражения.
По сути этот наш тернарный оператор будет правой частью лямбда выражения (после стрелочки).
 */
