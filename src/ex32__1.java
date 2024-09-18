/*
Урок с кодом
Цель: практика функциональных интерфейсов
Задание:
Напишите метод с названием sqrt, который возвращает
реализацию функционального интерфейса UnaryOperator,
который принимает целое число(тип int) и возвращает его квадрат.

Требования:
1. Должен быть метод public UnaryOperator sqrt()
2. Метод должен возвращать реализацию интерфейса UnaryOperator.
3. Реализация должна соответствовать условию
 */
import java.util.function.UnaryOperator;

public class ex32__1 {
    public static void main(String[] args) {
        Integer num = 27;
        System.out.println(num + " -> " + sqrt().apply(num));
    }

    public static UnaryOperator<Integer> sqrt() {
        return x -> x * x;
    }
}
