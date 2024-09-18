package newGenericsTregulov;
import java.util.Objects;

public class NewPairUsage {
    public static void main(String[] args) {
        NewPair<String, Integer> myPair = NewPair.of("privet", 20);
        System.out.println("Znacheniya pari: value1 = " + myPair.getFirst() +
                ", value2 = " + myPair.getSecond());

        NewPair<Integer, String> pair = NewPair.of(1, "hello");
        Integer i = pair.getFirst(); // 1
        System.out.println(i);
        String s = pair.getSecond(); // "hello"
        System.out.println(s);

        NewPair<Integer, String> pair2 = NewPair.of(1, "hello");
        boolean mustBeTrue = pair.equals(pair2); // true!
        System.out.println(mustBeTrue);
        boolean mustAlsoBeTrue = pair.hashCode() == pair2.hashCode(); // true!
        System.out.println(mustAlsoBeTrue);
    }
}
class NewPair<V1, V2> {
    private V1 value1;
    private V2 value2;

    private NewPair(V1 value1, V2 value2) {
        this.value1 = value1;
        this.value2 = value2;
    }
    public static <V1, V2> NewPair<V1, V2> of(V1 value1, V2 value2) {
        return new NewPair<>(value1, value2);
    }

    public V1 getFirst() {
        return value1;
    }
    public V2 getSecond() {
        return value2;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewPair<?, ?> newPair = (NewPair<?, ?>) o;
        return Objects.equals(value1, newPair.value1) && Objects.equals(value2, newPair.value2);
    }
    @Override
    public int hashCode() {
        return Objects.hash(value1, value2);
    }

//    @Override
//    public boolean equals(Object other) {
//        if (this == other) {
//            return true;
//        }
//        if (Pair.class.isInstance(other)) {
//            return Objects.equals(firstValue, ((Pair<?,?>)other).firstValue) &&
//                    Objects.equals(secondValue, ((Pair<?,?>)other).secondValue);
//        }
//        return false;
//    }
//    @Override
//    public int hashCode() {
//        return Objects.hashCode(this.getFirst()) ^ Objects.hashCode(this.getSecond());
//    }

}
