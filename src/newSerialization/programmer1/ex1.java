package newSerialization.programmer1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ex1 {
    public static void main(String[] args) throws NotSerializableException {
        List<String> employees = new ArrayList<>();
        employees.add("Olga");
        employees.add("Ivan");
        employees.add("Elena");

        try (ObjectOutputStream oos = new ObjectOutputStream
                (new FileOutputStream("employees.bin"))) {
            oos.writeObject(employees);
            System.out.println("Done!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
