public class days_2_1 {
    public static void main(String[] args) {
        String day = "Sunday";
        System.out.println(isWeekend(day));
    }

    public static boolean isWeekend(String weekday) {
        return switch (weekday) {
            case "Saturday", "Sunday" -> true;
            default -> false;
        };
    }
}
