public class days_2 {
    public static void main(String[] args) {
        String day = "Sunday";
        System.out.println(isWeekend(day));
    }

    public static boolean isWeekend(String weekday) {
        switch (weekday) {
            case "Saturday", "Sunday" :
                return true;
            default:
                return false;
        }
    }
}
