package newGenericsTregulov.igra;

public class Test {
    public static void main(String[] args) {
        Schoolar schoolar1 = new Schoolar("Ivan", 13);
        Schoolar schoolar2 = new Schoolar("Mariya", 15);
        Schoolar schoolar3 = new Schoolar("Sergey", 12);
        Schoolar schoolar4 = new Schoolar("Olya", 14);

        Student student1 = new Student("Nikolay", 20);
        Student student2 = new Student("Ksenya", 18);

        Employee employee1 = new Employee("Zaur", 32);
        Employee employee2 = new Employee("Mikhail", 47);

        Team<Schoolar> scholarTeam = new Team<>("Drakoni");
        scholarTeam.addNewParticipant(schoolar1);
        scholarTeam.addNewParticipant(schoolar2);
//        scholarTeam.addNewParticipant(student1);
//        scholarTeam.addNewParticipant(employee1);

        Team<Student> studentTeam = new Team<>("Vpered");
        studentTeam.addNewParticipant(student1);
        studentTeam.addNewParticipant(student2);

        Team<Employee> employeeTeam = new Team<>("Rabotyagi");
        employeeTeam.addNewParticipant(employee1);
        employeeTeam.addNewParticipant(employee2);

//        Team<String> anotherTeam = new Team<>("Fantaziya");
//        anotherTeam.addNewParticipant("Hello");

        Team<Schoolar> scholarTeam2 = new Team<>("Mudreci");
        scholarTeam2.addNewParticipant(schoolar3);
        scholarTeam2.addNewParticipant(schoolar4);

        scholarTeam.playWith(scholarTeam2);
//        scholarTeam2.playWith(employeeTeam);




    }
}
