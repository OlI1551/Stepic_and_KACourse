import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ex3Email {
    public static void main(String[] args) {
        String email = "fdfddsfsdf@gmail.com";

        Pattern pattern = Pattern.compile("\\w*(@gmail.com|@outlook.com)");
        Matcher matcher = pattern.matcher(email);
        boolean result = matcher.matches();
        System.out.println(result);
        System.out.println(email.matches("\\w*(@gmail.com|@outlook.com)"));

    }
}
