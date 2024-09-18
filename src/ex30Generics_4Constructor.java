/*
Обобщенные конструкторы
Конструкторы как и методы также могут быть обобщенными.
В этом случае перед конструктором также указываются в угловых скобках универсальные параметры:
В данном случае конструктор принимает параметр id, который представляет тип T.
В конструкторе его значение превращается в строку и сохраняется в локальную переменную.
 */

public class ex30Generics_4Constructor {

    public static void main(String[] args) {

        AnAccount acc1 = new AnAccount("cid2373", 5000);
        AnAccount acc2 = new AnAccount(53757, 4000);
        System.out.println(acc1.getId());
        System.out.println(acc2.getId());
        acc1.setSum(9000);
        System.out.println(acc2.getSum());
    }
}

class AnAccount{

    private String id;
    private int sum;

    <T>AnAccount(T id, int sum){
        this.id = id.toString();
        this.sum = sum;
    }

    public String getId() { return id; }
    public int getSum() { return sum; }
    public void setSum(int sum) { this.sum = sum; }
}