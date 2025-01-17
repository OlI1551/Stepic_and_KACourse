public class ex19 {
    public static void main(String[] args) {
        Employee sam = new Employee("Sam", "Leman Brothers");
        sam.display();
        Client bob = new Client("Bob", "Leman Brothers");
        bob.display();
    }
    public static abstract class Person {

        private String name;

        public String getName() {
            return name;
        }

        public Person(String name) {
            this.name = name;
        }

        public abstract void display();
    }

    public static class Employee extends Person {

        private String bank;

        public Employee(String name, String company) {
            super(name);
            this.bank = company;
        }

        public void display() {
            System.out.printf("Employee Name: %s \t Bank: %s \n", super.getName(), bank);
        }
    }

    public static class Client extends Person {
        private String bank;

        public Client(String name, String company) {
            super(name);
            this.bank = company;
        }

        public void display() {
            System.out.printf("Client Name: %s \t Bank: %s \n", super.getName(), bank);
        }
    }

}
