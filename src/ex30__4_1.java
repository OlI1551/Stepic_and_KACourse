/*
Задача:
Массивы в Java имеют фиксированную длину. Попробуем обойти это ограничение.
Создайте класс DynamicArray, который хранит в себе массив, и имеет методы:
для добавления void add(T el),
для удаления void remove(int index),
и извлечения T get(int index) из него элементов,
при переполнении текущего массива, должен создаваться новый, большего размера.

Для возможности работы с различными классами DynamicArray должен быть дженериком.
Для решения задачи можно воспользоваться методами из класса java.util.Arrays.
Импорт этого класса уже объявлен в тестирующей системе. Добавлять его явно не нужно

Требования:
1. должен быть класс public static class DynamicArray
2. класс DynamicArray должен иметь методы публичные void add(T el), void remove(int index) и T get(int index)
3. В случае подбора некорректного индекса ожидается, что метод get выбросит ArrayIndexOutOfBoundsException
4. класс DynamicArray должен иметь публичный конструктор по умолчанию
5. работа методов должна соответствовать условию


Для теста, должен вернуть следующее:
1 2 3 4 5 6 7 0 1 2 3 4
 */

import java.util.Arrays;

public class ex30__4_1 {
    public static void main(String[] args) {
        DynamicArray<Integer> array = new DynamicArray<>();
        array.add(1);
        array.add(2);
        array.add(3);
        array.add(4);
        array.add(5);
        array.add(6);
        array.add(7);
        array.add(8);
        array.add(9);
        array.add(10);
        array.remove(7);
        array.remove(7);
        array.remove(7);
        array.add(0);
        array.add(1);
        array.add(2);
        array.add(3);
        array.add(4);

        for (int i = 0; i < array.size; i++) {
            System.out.print(array.get(i) + " ");
        }

        System.out.println();
        System.out.println("------------------");


        DynamicArray<String> strings = new DynamicArray<>();
        strings.add("one");
        strings.add("two");
        strings.add("three");

        System.out.println("After add");
        System.out.println("the length is: " + strings.length());
        for (int i = 0; i < strings.length(); i++) {
            System.out.println(strings.get(i));
        }
        System.out.println();
        strings.remove(2);
        System.out.println("After remove");
        System.out.println("the length is: " + strings.length());
        for (int i = 0; i < strings.length(); i++) {
            System.out.println(strings.get(i));
        }
    }

    public static class DynamicArray<T> {
        private T[] elements = (T[]) new Object[10];
        private int size;

        public T get(int index) {
            if (index >= size || index < 0) {
                throw new ArrayIndexOutOfBoundsException();
            } else {
                return elements[index];
            }
        }

        public void remove(int index) {
            if (index >= size || index < 0) {
                throw new ArrayIndexOutOfBoundsException();
            } else {
                System.arraycopy(elements, index + 1, elements, index, elements.length - index - 1);
                size--;
            }
        }

        public void add(T el) {
            if (size == elements.length) {
                elements = Arrays.copyOf(elements, elements.length * 2);
            }
            elements[size++] = el;
        }

        public int length() {
            return size;
        }
    }
}
/*
конструктор только дефолтный
private int isIndexExist(int index) {
    if (index >= size || index < 0) {
         throw new ArrayIndexOutOfBoundsException();
    }
    return index;
    }
private T[] increaseCapacity() {
    T[] temp = (T[]) new Object[(elements.length * 2)];
    System.arraycopy(elements, 0, temp, 0, elements.length);
    return temp;
    } убрать
     ремув в 2 строки инкрементация без отдельной строки
 */