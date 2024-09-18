public class ex10days {
    public static void main(String[] args) {
        EnumsDays_EnumDay day = EnumsDays_EnumDay.MONDAY;
        System.out.println(day.isWeekend());
        System.out.println(day.getRusName());
        System.out.println(day.name());
    }
    public enum EnumsDays_EnumDay {
        MONDAY(false, "понедельник"),
        TUESDAY(false, "вторник"),
        WEDNESDAY(false, "среда"),
        THURSDAY(false, "четверг"),
        FRIDAY(false, "пятница"),
        SATURDAY(true, "суббота"),
        SUNDAY(true, "воскресенье");

        private boolean isWeekend;
        private String rusName;

        EnumsDays_EnumDay(boolean isWeekend, String rusName) {
            this.isWeekend = isWeekend;
            this.rusName = rusName;
        }

        public boolean isWeekend() {
            return isWeekend;
        }

        public String getRusName() {
            return rusName;
        }
    }
}
