import java.lang.Class;

public class Char {
    public static void main(String[] args) {
        for (int i = -128; i < 128; i++) {
            char c = (char) i;
            System.out.println(i + ") " + c);
        }
        Integer x = 5;
        System.out.println(x.getClass());
    }
}
