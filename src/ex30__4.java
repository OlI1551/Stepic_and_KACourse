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

public class ex30__4 {
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
    }

    public static class DynamicArray<T> { // создаем класс динамического массива
        private T[] elements; // массив имеет атрибут/свойство - поле - элементы - массив элементов типа T
        private int size;  // массив имеет атрибут/свойство - поле - счетчик количества элементов - переменная типа int

        public DynamicArray() {
            this.elements = (T[]) new Object[10]; // конструктор переменной элементы создаваемого объекта класса присваивает значение
            // - массив объектов размером на 10 ячеек и этот массив явно приводит к типу - массив элементов T
        }

        private int isIndexExist(int index) {  // создаем метод для проверки - есть ли ячейка с таким индексом
            if (index >= size || index < 0) {
                throw new ArrayIndexOutOfBoundsException(); // и выбрасываем исключение, если индекс вне диапазона счетчика элементов на текущий момент
            }
            return index;  // или возвращаем индекс
        }

        private T[] increaseCapacity() {
            T[] temp = (T[]) new Object[(elements.length * 2)];  // заводим временный массив объектов Object, увеличив наш массив в 2 раза, явно кастомим его до типа T
            System.arraycopy(elements, 0, temp, 0, elements.length);  // !!!Какой интересный метод копирования массива старого в новый!!!
            return temp;  // меняем старый массив на новый
        }

        public T get(int index) {  // метод для извлечения элемента по индексу
            isIndexExist(index); // выбрасываем исключение
            return elements[index];  // или возвращаем элемент
        }

        public void remove(int index) {  // метод для удаления элемента
            isIndexExist(index); // выбрасываем исключение
            T[] temp = elements;  // создаем временный массив, равный нашему
            elements = (T[]) new Object[temp.length - 1];  // наш массив превращаем в меньший на 1 ячейку
            T value = temp[index];
            System.arraycopy(temp, 0, elements, 0, index);  // копируем временный массив в наш до удаляемого индекса
            System.arraycopy(temp, index + 1, elements, index, temp.length - index - 1);  // продолжаем копировать временный массив от
            // следующего после индекса элемента на длину за вычетом уже скопированного и за вычетом 1 ячейки с удаляемым элементом
            size--; // отмечаем, что счетчик элементов объекта динамического массива уменьшился (состояние изменилось)
        }

        public void add(T el) {  // метод для добавления элемента
            if (size == elements.length) { // проверяем, не равен ли счетчик элементов размеру массива
                elements = increaseCapacity();  // увеличиваем наш массив, заменив его на новый - в 2 раза больше
            }
            elements[size++] = el; // добавляем элемент в конечную ячейку, индекс которой равен счетчику элементов - ячейка под номером 0 заполнена, но в счетчике не отражена
            // и одновременно увеличиваем счетчик на 1 добавленный элемент
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
    } убрать ремув в 2 строки инкрементация без отдельной строки
 */