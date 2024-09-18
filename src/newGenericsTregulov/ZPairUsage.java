package newGenericsTregulov;
import java.util.Objects;

public class ZPairUsage {
    public static void main(String[] args) {
        ZPair<String, Integer> myPair = ZPair.of("privet", 20);
        System.out.println("Znacheniya pari: value1 = " + myPair.getFirst() +
                ", value2 = " + myPair.getSecond());

        ZPair<Integer, String> pair = ZPair.of(1, "hello");
        Integer i = pair.getFirst(); // 1
        System.out.println(i);
        String s = pair.getSecond(); // "hello"
        System.out.println(s);

        ZPair<Integer, String> pair2 = ZPair.of(1, "hello");
        boolean mustBeTrue = pair.equals(pair2); // true!
        System.out.println(mustBeTrue);
        boolean mustAlsoBeTrue = pair.hashCode() == pair2.hashCode(); // true!
        System.out.println(mustAlsoBeTrue);
    }
}
class ZPair<Z1, Z2> {
    private Z1 znacheniye1;
    private Z2 znacheniye2;

    private ZPair(Z1 znacheniye1, Z2 znacheniye2) {
        this.znacheniye1 = znacheniye1;
        this.znacheniye2 = znacheniye2;
    }
    public static <Z1, Z2> ZPair<Z1, Z2> of(Z1 z1, Z2 z2) {
        return new ZPair<>(z1, z2);
    }

    public Z1 getFirst() {
        return znacheniye1;
    }
    public Z2 getSecond() {
        return znacheniye2;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZPair<?, ?> zPair = (ZPair<?, ?>) o;
        return Objects.equals(znacheniye1, zPair.znacheniye1) && Objects.equals(znacheniye2, zPair.znacheniye2);
    }
    @Override
    public int hashCode() {
        return Objects.hash(znacheniye1, znacheniye2);
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
