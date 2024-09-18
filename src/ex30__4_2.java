import java.util.Arrays;

public class ex30__4_2 {
    public static void main(String[] args) {

    }
    public static class DynamicArray<T> {
        private Object[] array = new Object[5];
        private int currentIndex = 0;

        public void add(T el) {
            array[currentIndex] = el;
            currentIndex++;
            if (array.length == currentIndex) {
                array = Arrays.copyOf(array, array.length * 2);
            }
        }

        public void remove(int index) {
            System.arraycopy(array, index + 1, array, index, array.length - index - 1);
            currentIndex--;
        }

        public T get(int index) {
            if (index < currentIndex && index >= 0) {
                return (T) array[index];
            } else {
                throw new ArrayIndexOutOfBoundsException();
            }
        }
    }
}
