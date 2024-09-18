import java.sql.SQLOutput;
import java.util.Arrays;

public class ex4Roles {
    public static void main(String[] args) {
        String[] roles = {"Городничий", "Аммос Федорович", "Артемий Филиппович", "Лука Лукич"};
        String[] textLines = {"Городничий: Я пригласил вас, господа, с тем, чтобы сообщить вам пренеприятное известие: к нам едет ревизор.", "Аммос Федорович: Как ревизор?", "Артемий Филиппович: Как ревизор?", "Городничий: Ревизор из Петербурга, инкогнито. И еще с секретным предписаньем.", "Аммос Федорович: Вот те на!", "Артемий Филиппович: Вот не было заботы, так подай!", "Лука Лукич: Господи боже! еще и с секретным предписаньем!"};

        StringBuilder sb = new StringBuilder("");
        sb.append("\n");
        for (String role : roles) {
            sb.append(role);
            sb.append(":");
            sb.append("\n");
            for (int id = 0; id < textLines.length; id++) {
                if (textLines[id].substring(0, textLines[id].indexOf(":")).equals(role)) {
                    sb.append(id + 1);
                    sb.append(")");
                    sb.append(textLines[id].substring(textLines[id].indexOf(":") + 1));
                    sb.append("\n");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
