public class ex1_1 {
    public static void main(String[] args) {
        String text = "А роза? упала, на лапу Азора";

        System.out.println(text.replaceAll("[^a-zA-Z0-9]", "")
                .equalsIgnoreCase(new StringBuffer(text
                .replaceAll("[^a-zA-Z0-9]", ""))
                .reverse().toString()));
    }
}
