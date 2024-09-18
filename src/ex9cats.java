import org.w3c.dom.ls.LSOutput;

public class ex9cats {
    public static void main(String[] args) {
        Dog Jack = new Dog();
        Cat Barsic = new Cat();
        Barsic.sayHello();
        Jack.sayHello();
        Jack.catchCat(Barsic);
    }
}
class Cat {
    public void sayHello() {
        System.out.println("Мяу!");
    }
}
class Dog {
    public void sayHello() {
        System.out.println("Гав!");
    }
    public void catchCat(Cat cat) {
        System.out.println("Кошка поймана");
        this.sayHello();
        cat.sayHello();
    }
}
