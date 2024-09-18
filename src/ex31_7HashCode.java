/*
Если вы переопределили equals, то переопределите и hashcode

Результат нескольких выполнений метода hashcode для одного и того же объекта
должен быть одинаковым

Если согласно методу equals два объекта равны, то и hashcode данных объектов
обязательно должен быть одинаковым

Если согласно методу equals два объекта НЕ равны, то hashcode данных объектов
НЕ обязательно должен быть разным

Ситуация, когда результат  метода hashcode для разных объектов одинаков, называется
коллизией. Чем ее меньше, тем лучше.

В основе HashMap лежит массив. Элементами данного массива являются структуры LinkedList.
Данные структуры LinkedList и заполняются элементами, которые мы добавляем в HashMap

При создании HashMap мы можем задать 2 параметра, которые очень влияют на производительность:
Initial capacity - начальный размер массива;
Load factor - коэффициент того, насколько массив должен быть заполнен, после чего
его размер будет увеличен вдвое

Ключи в хэш-коде должны быть immutable и поэтому класс-ключ и его поля-переменные должны быть final - неизменяемые!!!

HashMap не синхронизирован, его нельзя использовать при многопоточном программировании
Для этого есть ConcurrentHashMap


Объекты в HashMap сначала сравниваются по хэш-коду, а затем по equals
Если хэш-код одинаковый - это всего-лишь коллизия, по equals это будут разные объекты
Хэш-код возвращает int, у int есть предел, а объектов может быть бесконечное количество
Поэтому рано или поздно, даже при хорошо написанном хэщ-код метода, у разных объектов будет один хэш-код
Следовательно, коллизий не избежать
Правильно написанный метод хэш-код позволяет делать так, чтобы коллизия была нечастой
Но она все равно будет
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ex31_7HashCode {
    public static void main(String[] args) {
        Map<Student1, Double> map = new HashMap<>();
        Student1 st1 = new Student1("Zaur", "Tregulov", 3);
        Student1 st2 = new Student1("Mariya", "Ivanova", 1);
        Student1 st3 = new Student1("Sergey", "Petrov", 4);
        map.put(st1, 7.5);
        map.put(st2, 8.7);
        map.put(st3, 9.2);
        System.out.println(map);
        Student1 st4 = new Student1("Zaur", "Tregulov", 3);
        boolean result = map.containsKey(st4);
        System.out.println("result = " + result);
        System.out.println(st1.equals(st4));
        System.out.println(st1.hashCode());
        System.out.println(st2.hashCode());
        System.out.println(st3.hashCode());
        System.out.println(st4.hashCode());
        Student1 st5 = new Student1("Igor", "Sidorov", 4);
        System.out.println(st5.hashCode()); // когда у разных объектов возвращается одинаковый хэш-код, это называется коллизией
        // и чем меньше коллизий в нашем коде - тем лучше
        // надо улучшить реализацию расчета хэш-кода
        // при дефолтной реализации хэш-кода используется адрес нашего объекта
        // поэтому у каждого нового объекта будет уникальный хэш-код
        // поэтому у одинаковых объектов будет разный хэш-код, если они созданы в разное время
        // поэтому нужно не забывать переопределять хэш-код


        for(Map.Entry<Student1, Double> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

        Map<Integer, String> map2 = new HashMap<>(16, 0.75f);
        // Чем больше емкость, тем быстрее поиск, но памяти больше занимает
        // Чем больше загрузка, тем меньше памяти занимает, но тем больше количество и длиннее линкед-листы в бакетах и дольше поиск



    }
}
final class Student1 {
    final String name;
    final String surname;
    final int course;

    public Student1(String name, String surname, int course) {
        this.name = name;
        this.surname = surname;
        this.course = course;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", course=" + course +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student1 student = (Student1) o;
        return course == student.course &&
                Objects.equals(name, student.name) &&
                Objects.equals(surname, student.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, course); // лучше использовать готовую реализацию хэщ-кода - она хорошая
    }

//    @Override
//    public int hashCode() {
//        return name.length()*7 + surname.length()*11 + course*53; // обычно в хэш-коде какие-то значения умножаются на простые числа(делятся на 1 и себя),
//        // чтобы коллизий было меньше
//    }

}
