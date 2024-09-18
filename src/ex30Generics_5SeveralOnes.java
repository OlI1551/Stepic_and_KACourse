/*
Использование нескольких универсальных параметров
Мы можем также задать сразу несколько универсальных параметров:
В данном случае тип String будет передаваться на место параметра T, а тип Double - на место параметра S.
 */
public class ex30Generics_5SeveralOnes {

    public static void main(String[] args) {

        NewAccount<String, Double> acc1 = new NewAccount<String, Double>("354", 5000.87);
        String id = acc1.getId();
        Double sum = acc1.getSum();
        System.out.printf("Id: %s  Sum: %f \n", id, sum);
        acc1.setSum(acc1.getSum() + 2500.00);
        System.out.println(acc1.getSum());
        System.out.printf("Id: %s  Sum: %f \n", id, sum);
        sum = acc1.getSum();
        System.out.printf("Id: %s  Sum: %f \n", id, sum);
    }
}
class NewAccount<T, S> {

    private T id;
    private S sum;

    NewAccount(T id, S sum){
        this.id = id;
        this.sum = sum;
    }

    public T getId() { return id; }
    public S getSum() { return sum; }
    public void setSum(S sum) { this.sum = sum; }
}