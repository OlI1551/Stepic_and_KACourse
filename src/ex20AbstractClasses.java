public class ex20AbstractClasses {
    public static void main(String[] args) {
        Salary s = new Salary ("Петров П.П.", "Москва, Россия", 3, 3600.00);
        Employee e = new Salary ("Смирнов О.И.", "Смоленск, Россия", 2, 2400.00);
        System.out.println("Вызываем mailCheck, используя ссылку Salary --");
        s.mailCheck();
        System.out.println("\n Вызываем mailCheck, используя ссылку Employee --");
        e.mailCheck();

    }
    public static abstract class Employee {
        private String name;
        private String address;
        private int number;

        public Employee(String name, String address, int number) {
            System.out.println("Собираем данные о работнике");
            this.name = name;
            this.address = address;
            this.number = number;
        }

        public double computePay() {
            System.out.println("Внутри Employee computePay");
            return 0.0;
        }

        public void mailCheck() {
            System.out.println("Отправляем чек " + this.name + " " + this.address);
        }

        public String toString() {
            return name + " " + address + " " + number;
        }

        public String getName() {
            return name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String newAddress) {
            address = newAddress;
        }

        public int getNumber() {
            return number;
        }
    }
    public static class Salary extends Employee {
        private double salary;   // Годовая заработная плата

        public Salary(String name, String address, int number, double salary) {
            super(name, address, number);
            setSalary(salary);
        }

        public void mailCheck() {
            System.out.println("Внутри mailCheck класса Salary ");
            System.out.println("Отправляем чек" + getName() + " с зарплатой " + salary);
        }

        public double getSalary() {
            return salary;
        }

        public void setSalary(double newSalary) {
            if(newSalary >= 0.0) {
                salary = newSalary;
            }
        }

        public double computePay() {
            System.out.println("Вычисляем заработную плату для " + getName());
            return salary/52;
        }
    }
}
