public class ex30__4_NewDynamicArray {
    public static void main(String[] args) {
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
}
class DynamicArray<T> {
    private T[] array;
    private int size = 0;

    // Конструктор по умолчанию
    @SuppressWarnings("unchecked")
    public DynamicArray() {
        array = (T[]) new Object[10]; // Изначальный размер массива
    }

    // Метод для добавления элемента
    public void add(T el) {
        if (size == array.length) {
            // Увеличиваем размер массива в 1.5 раза, если он заполнен
            growArray();
        }
        array[size++] = el;
    }

    // Метод для получения элемента по индексу
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Некорректный индекс: " + index);
        }
        return array[index];
    }

    // Метод для удаления элемента по индексу
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Некорректный индекс: " + index);
        }
        // Сдвигаем элементы влево после удаления
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[--size] = null; // Уменьшаем размер и очищаем последний элемент
    }

    // Вспомогательный метод для увеличения массива
    @SuppressWarnings("unchecked")
    private void growArray() {
        int newSize = array.length + (array.length >> 1); // Увеличение в 1.5 раза
        T[] newArray = (T[]) new Object[newSize];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    // метод для получения длины массива
    public int length() {
        return size;
    }
}