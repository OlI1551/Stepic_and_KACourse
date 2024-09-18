public class ex17_1 {
    public static void main(String[] args) {
        Line line = new Line();
        line.showId();

        Object g0 = new Properties();
        Object g1 = new Line(); // upcasting восходящее образование объекта старшего типа
        Object g2 = new Triange();
        Object g3 = new Eclipse();

        Line l1 = (Line) g1; // явное преобразование объекта старшего типа в младший downcasting
        l1.showId();
        Line l2 = (Line) g2; // будет ошибка g2 - это класс Triangle

    }
    public static class Properties {
        int width, colour;
        int id = 1;

        Properties() {
            System.out.println("Конструктор Properties()");
        }

        Properties(int width, int colour) {
            this.width = width;
            this.colour = colour;
            System.out.println("Конструктор Properties(width, colour)");
        }
        void showId() {
            System.out.println("id = " + id);
        }
    }
    public static class Line extends Properties {
        double x1, y1;
        double x2, y2;
        int id = 2;

        Line() {
            super(0, 0); // обращаемся к конструктору базового класса, по умолчанию к конструктору без параметров
            System.out.println("Конструктор Line()"); // если его нет, то выдает ошибку, для обращ к кон с парам надо их указать
        } // параметры указываем 0 или 0.0 или null или прямо пишем значение, которое надо поменять
        void showId() {
            System.out.println("id =" + id + ", super.id = " + super.id); // обращаемся к переменной (полю) базового  класса
            super.showId(); // обращаемся к методу базового класса
        }
    }
    public static class Triange extends Properties {
        double x1, y1;
        double x2, y2;
        double x3, y3;
    }
    public static class Rectangle extends Properties {
        double x1, y1;
        double x2, y2;
    }
    public static class Eclipse extends Properties {
        double x1, y1;
        double x2, y2;
    }
}
