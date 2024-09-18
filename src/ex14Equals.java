import java.util.Objects;

public class ex14Equals {
    public static void main(String[] args) {
        Student a = new Student (1, "Bob");
        Student b = a;
        System.out.println(a.equals(b));  // равен сам себе на 1 шаге
        System.out.println(b.equals(a));
        Student b1 = new Student(1, "Bob");
        System.out.println(a.equals(b1));  // параметры одинаковые на 4 шаге
        System.out.println(b1.equals(a));
        Employee e = new Employee(1, "Bob");
        System.out.println("_______________________________________");
        System.out.println(a.equals(e));  // Классы разные - выдает false на 3 этапе, сам себе не равен на 1 шаге, не пустой на 2 шаге, параметры одинаковые на 4 шаге
        System.out.println(e.equals(a));
        System.out.println("________________________________________");
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
        System.out.println(b1.hashCode());
        System.out.println(e.hashCode());

    }
}

class Student {
    int id;
    String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override  // переопределяем метод equals
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id && name.equals(student.name);
    }

    @Override  // переопределяем метод hashCode
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

class Employee extends Student {

    public Employee(int id, String name) {
        super(id, name);
    }
}
