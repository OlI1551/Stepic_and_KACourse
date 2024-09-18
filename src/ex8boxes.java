public class ex8boxes {
    public static void main(String[] args) {
        Box myBox1 = new Box();
        Box myBox2 = new Box();

        myBox1.setDim(10, 20, 15);
        myBox2.setDim(1, 5, 5);

        System.out.println("Объем: " + myBox1.getVolume());
        System.out.println("Объем: " + myBox2.getVolume());
    }
}
class Box {
    double width;
    double height;
    double depth;

    /**
     * Подсчитать объем коробки
     *
     * @return Объем
     */
    double getVolume() {
        return width * height * depth;
    }

    /**
     * Установить размер коробки
     *
     * @param w - ширина
     * @param h - высота
     * @param d - глубина
     */
    void setDim(double w, double h, double d) {
        width = w;
        height = h;
        depth = d;
    }
}