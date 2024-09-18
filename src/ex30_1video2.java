public class ex30_1video2 {
    public static void main(String[] args) {
        Cell<String> stringCell = new Cell<>(); // создали дженерик-объект и сказали, что мы хотим там хранить String-и
        stringCell.setT("Hello"); // и можем туда даже что-то поместить
        String s = stringCell.getT();  // не надо явного приведения типов - раньше надо было писать (String) или toString вызывать
        // а сейчас мы сказали, что работаем со стрингами

        // перепишем без дженериков
        /*
        Cell stringCell = new Cell();
        stringCell.setT("Hello");
        String s = (String) stringCell.getT();
         */
        // это все работало, дженерики ввели, чтобы мы не могли неправильные данные добавлять и ошибка на этапе компиляции была видна

        Cell cell = new Cell(); // можем создать и не дженерик - да он будет старый и небезопасный
        // он будет примерно тем, что после компиляции
        cell.setT("Java");
        cell.setT(123);
        cell.setT(new Cell()); // мы можем впихивать в него все, что угодно (хоть сам Cell) - это не имеет значения
//        Cell cell = new Cell<String>(); // Можно даже сделать так - в правой части оно не имеет никакого значения - просто теряется - создастся не дженерик тип
//        Cell<String> cll = new Cell<Integer>();  // что еще важно - разные типы - так быть не должно!!!
//        Cell<Number> num = new Cell<Integer>();  // даже наследование не прокатит!!!
//        Cell<String> box = new Cell(); // Нельзя опускать скобки в правой части? Можно!!! Запускается и работает безупречно. Но это смешение 2 стилей - нового и старого
        // Дженерик-классы сами по себе бесполезны, методы у них те же, что и у Object.
        // Используются в коллекциях для обозначения хранимых в них типах

    }
}
class Cell <T> {  // типов может быть множество - class Cell <T, Y, X> {}
    T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
/*
после байт-кода
class Cell {
    Object object;
    public Object getObject() {
        return object;
    }
    public void setObject(Object object) {
        this.object = object;
    }
}
 */