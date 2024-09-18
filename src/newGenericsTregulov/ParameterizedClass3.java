package newGenericsTregulov;

public class ParameterizedClass3 {
    public static void main(String[] args) {


    }
//    public void abc(Info3<String> info) {
//        String s = info.getValue();
//    }
//
//    public void abc(Info3<Integer> info) {
//        Integer i = info.getValue();
//    }
    // public void abc(Info info)

}

class Info3 <T> { // можно писать T - type placeholder, I - item, K - key, V - value
    private T value; // переменная не может быть static
    public Info3(T value) {
        this.value = value;
    }
    public String toString() {
        return "{[" + value + "]}";
    }

    public T getValue() {
        return value;
    }
}

class Parent {
    public void abc(Info3<String> info3) {
        String s = info3.getValue();
    }
}
//class Child extends Parent {
//    public void abc(Info3<Integer> info3) {
//        int i = info3.getValue();
//    }
//}


