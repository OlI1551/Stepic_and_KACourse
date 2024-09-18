public class ex19_1 {
    public static void main(String[] args) {

    }
    public static abstract class Figure {

        float x; // x-координата точки
        float y; // y-координата точки

        Figure(float x, float y) {
            this.x=x;
            this.y=y;
        }
        public abstract float getPerimeter();
        public abstract float getArea();
    }

    public static class Rectangle extends Figure {
        private float width;
        private float height;

        // конструктор с обращением к конструктору класса Figure
        Rectangle(float x, float y, float width, float height) {
            super(x,y);
            this.width = width;
            this.height = height;
        }

        public float getPerimeter() {
            return width * 2 + height * 2;
        }

        public float getArea() {
            return width * height;
        }
    }
}
