public class ex18MethodOverriding {
    public static void main(String[] args) {
        Animal myAnimal = new Animal();
        myAnimal.voice();
        Cat murka = new Cat();
        murka.voice();
        PersianCat barsik = new PersianCat();
        barsik.voice();

    }
    public static class Animal {
        // ...
        void voice() {
            System.out.println("This is my voice!");
        }
    }
    public static class Cat extends Animal {
        // Переопределение метода voice()
        @Override  // аннотация
        public void voice() {
            System.out.println("Кошка мяукает: Мяу-мяу!");
        }
    }
    public static class PersianCat extends Cat {
        //...
    }
    public static class Dog extends Animal {
        // Переопределение метода voice()
        @Override  // аннтотация
        public void voice() {
            System.out.println("Собака гавкает: Гав-гав!");
        }
    }
    public static class Dobermann extends Dog {
        @Override
        public void voice()
        {
            super.voice(); // выполнится метод voice класса Dog
            System.out.println("Доберман гавкает: Bark!");
        }
    }
    public static class Cow extends Animal {
        // Переопределение метода voice()
        @Override
        public void voice() {
            System.out.println("Корова мычит: Муу!");
        }
    }
}
