import java.util.Arrays;

public class ex22Exceptions_2 {
    public static void main(String[] args) {
        System.out.println(getCallerClassAndMethodName());
        anotherMethod();
    }

    private static void anotherMethod() {
        System.out.println(getCallerClassAndMethodName());
    }

    public static String getCallerClassAndMethodName() {
        StackTraceElement[] ste = new Exception().getStackTrace();
        System.out.println(Arrays.toString(ste));
        if (ste.length < 3 ) {
            return null;
        } else {
            return ste[2].getClassName()+"#"+ste[2].getMethodName();
        }
    }
}