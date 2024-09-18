public class DuckOOP {
    public static void main(String[] args) {
        Duck greyDuck = new Duck();
        greyDuck.age = 4;
        greyDuck.breed = "anasPlatyrhynchos";
        greyDuck.color = "grey";
        greyDuck.flies();
        greyDuck.swims();
        greyDuck.eats();
        greyDuck.quacks();
    }
}
class Duck {
    String breed;		// порода утки
    int age;			// возраст утки
    String color;		// раскраска утки
    // утка плавает, утка крякает, утка летает, утка ест
    void swims() {
        System.out.println("Duck swims");
    }
    void quacks() {
        System.out.println("Duck quacks");
    }
    void flies() {
        System.out.println("I CAN FLY!!!");
        System.out.println("I fly!");
    }
    void eats() {
        System.out.println("Duck eats");
    }
}
