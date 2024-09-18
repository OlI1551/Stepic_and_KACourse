public class Days {
    // Метод проверяет, является ли переданный день выходным (Saturday или Sunday)
    public static boolean isWeekend(String dayName) {
        return "Saturday".equalsIgnoreCase(dayName) || "Sunday".equalsIgnoreCase(dayName);
    }

    // Метод подсчитывает количество выходных дней в массиве строк
    public static int weekendCount(String[] days) {
        int count = 0;
        for (String day : days) {
            if (isWeekend(day)) {
                count++;
            }
        }
        return count;
    }

    // Метод подсчитывает количество будних дней в массиве строк
    public static int weekdayCount(String[] days) {
        return (days.length - weekendCount(days));
    }

    public static void main(String[] args) {
        String[] days = {"Monday", "Tuesday", "Wednesday", "Saturday", "Sunday", "Friday"};

        System.out.println("Is Saturday a weekend? " + isWeekend("Saturday")); // true
        System.out.println("Weekend count: " + weekendCount(days)); // 2
        System.out.println("Weekday count: " + weekdayCount(days)); // 4
    }
}
