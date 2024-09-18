public class ex17InheritanceConstructors {
    public static void main(String[] args) {
        Student vasya = new Student();
        vasya.study();
        JavaStudent ivan = new JavaStudent();
        ivan.study();
        LazyStudent kolyan = new LazyStudent();
        kolyan.study();
    }
    public static class Student {
        protected final String studying;
        protected Student(String work) {
            this.studying = work;
        }
        public Student() {
            this.studying = "Прохожу тестовое задание.";
        }
        public void study() {
            System.out.println("Я очень занят. " + studying);
        }
    }
    public static class JavaStudent extends Student {
        public JavaStudent() {
            super("Прохожу курс по Java"); // обращаемся к полям базового класса
        }
    }
    public static class LazyStudent extends Student {
        @Override
        public void study() {
            System.out.println("Сегодня не учусь, мне лень.");
        }
    }

}
