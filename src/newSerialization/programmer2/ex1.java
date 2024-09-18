package newSerialization.programmer2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ex1 {
    public static void main(String[] args) {
        List<String> employees;

        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("employees.bin"))) {
            employees = (ArrayList) ois.readObject();
            System.out.println(employees);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
