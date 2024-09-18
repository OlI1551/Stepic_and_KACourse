package newGenericsTregulov;

public class ParameterizedClass2 {
    public static void main(String[] args) {
        Pair<String, Integer> pair1 = new Pair<>("privet", 20);
        System.out.println("Znacheniya pari: value1 = " + pair1.getFirstValue() +
                ", value2 = " + pair1.getSecondValue());

        Pair<Integer, Double> pair2 = new Pair<>(156, 3.14);
        System.out.println("Znacheniya pari2: value1 = " + pair2.getFirstValue() +
                ", value2 = " + pair2.getSecondValue());

        AnotherPair<String> anotherPair = new AnotherPair<>("Hello!", "Java!");
        System.out.println("Znacheniya drugoi pari: value1 = " + anotherPair.getFirstValue() +
                ", value2 = " + anotherPair.getSecondValue());
    }
}
class Pair<V1, V2> {
    private V1 value1;
    private V2 value2;

    public Pair(V1 value1, V2 value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    public V1 getFirstValue() {
        return value1;
    }
    public V2 getSecondValue() {
        return value2;
    }
}
class AnotherPair<V> {
    private V value1;
    private V value2;

    public AnotherPair(V value1, V value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    public V getFirstValue() {
        return value1;
    }
    public V getSecondValue() {
        return value2;
    }

    // можно не прописывать V (плэйсхолдер) у параметризованного метода
    // в параметризованном классе
    public <V> V abc (V val) {
        return val;
    }
}
