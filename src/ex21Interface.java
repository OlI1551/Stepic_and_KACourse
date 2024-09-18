public class ex21Interface {
    public static void main(String[] args) {
        Book b1 = new Book("Java. Complete Referense.", "H. Shildt");
        b1.print();
        Printable.read();
    }
}
interface Searchable {

    // методы интерфейса
}
interface Printable {
    static void read(){

        System.out.println("Read printable");
    }
    void print();

    default void printAll(){

        System.out.println("Undefined printable");
    }
}

class Book implements Printable, Searchable {
    String name;
    String author;

    Book(String name, String author) {

        this.name = name;
        this.author = author;
    }

    public void print() {
        System.out.printf("%s (%s) \n", name, author);
    }
}
class Journal implements Printable {

    private String name;

    String getName() {
        return name;
    }

    Journal(String name) {
        this.name = name;
    }
    public void print() {
        System.out.println(name);
    }
}
