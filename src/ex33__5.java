import java.math.BigInteger;
import java.util.stream.IntStream;

public class ex33__5 {
    public static void main(String[] args) {
        System.out.println(factorial(7));
    }
    public static BigInteger factorial(int value) {
        return IntStream.rangeClosed(1, value)
                .mapToObj(i -> BigInteger.valueOf(i))
                .reduce(BigInteger.ONE, BigInteger::multiply);
    }
}
