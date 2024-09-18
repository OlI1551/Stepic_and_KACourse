public class MyClass implements Cloneable {
    private final String immutableString;
    private int[] mutableArray;

    public MyClass(String immutableString, int[] mutableArray) {
        this.immutableString = immutableString;
        this.mutableArray = mutableArray.clone(); // Клонируем массив для глубокого клонирования
    }

    // Геттеры
    public String getImmutableString() {
            return immutableString;
    }
    public int[] getMutableArray() {
        return mutableArray.clone(); // Возвращаем клонированный массив
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        MyClass cloned = (MyClass) super.clone(); // Поверхностное клонирование объекта
        cloned.mutableArray = this.mutableArray.clone(); // Глубокое клонирование массива
        // прям прописываем, что клонируем массив, а не только поле, как вверху
        return cloned;
    }

    public void setMutableArray(int[] mutableArray) {
        this.mutableArray = mutableArray;
    }
}
