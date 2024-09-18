package newGenericsTregulov;

public class ParameterizedClass {
    public static void main(String[] args) {
//        Info<String> info1 = new Info<>("privet");
//        System.out.println(info1);
//        String s = info1.getValue();

//        Info<Integer> info2 = new Info<>(5);
//        System.out.println(info2);
//        Integer i1 = info2.getValue();
//
//        Info<Double> info4 = new Info<>(5.2);
//        System.out.println(info4);
//        Double d1 = info4.getValue();

//        Info<Bus> info3 = new Info<>(new Bus());
//        System.out.println(info3);
//        Bus b1 = info3.getValue();

    }
}

class Info <T extends Number&I1&I2> { // можно писать T - type placeholder, I - item, K - key, V - value
    private T value; // переменная не может быть static
    public Info(T value) {
        this.value = value;
    }
    public String toString() {
        return "{[" + value + "]}";
    }

    public T getValue() {
        return value;
    }
}

interface I1 {}
interface I2 {}
class Bus{}
