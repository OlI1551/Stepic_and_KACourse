import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ex1 {
    public static void main(String[] args) {
        String str = "127";
        System.out.println(Integer.parseInt(str, 8));

        String text = "А роза? упала, на лапу Азора";

        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
        Matcher matcher = pattern.matcher(text);
        String clear = matcher.replaceAll("");

        StringBuffer sb = new StringBuffer(clear);
        sb.reverse();
        String reversed = sb.toString();

        boolean flag = clear.equalsIgnoreCase(reversed);
        System.out.println(flag);
    }
}
