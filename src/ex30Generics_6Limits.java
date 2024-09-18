/*
Ограничения обобщений
Когда мы указываем универсальный параметр у обобщений, то по умолчанию он может представлять любой тип.
Однако иногда необходимо, чтобы параметр соответствовал только некоторому ограниченному набору типов. В этом случае применяются ограничения, которые позволяют указать базовый класс, которому должен соответствовать параметр.

Для установки ограничения после универсального параметра ставится слово extends,
после которого указывается базовый класс ограничения:

class Account { }
class Transaction<T extends Account> { }
К примеру, в данном случае для параметра T в Transaction ограничением является класс Account.
То есть на место параметра T мы можем передать либо класс Account, либо один из его классов-наследников.

В данном случае класс Transaction представляет операцию перевода средств между двумя счетами.
Он типизирован параметром T, у которого в качестве ограничения установлен класс Account.
При создании объекта Transaction в его конструктор передаются два объекта Account
- два счета, между которыми надо осуществить перевод, и сумма перевода.
При этом важно понимать, что поскольку мы установили подобное ограничение, то компилятор будет распознавать
объекты типа T как объекты типа Account. И в этом случае мы можем вызывать у объектов типа T методы класса Account.
И мы не смогли бы это сделать, если бы мы не задали подобного ограничения:
class Transaction<T> {
    // остальное содержимое
}
В этом случае была бы ошибка.
 */
public class ex30Generics_6Limits {

    public static void main(String[] args) {

        TheAccount acc1 = new TheAccount("1876", 4500);
        TheAccount acc2 = new TheAccount("3476", 1500);

        Transaction<TheAccount> tran1 = new Transaction<TheAccount>(acc1, acc2, 4000);
        tran1.execute();
        tran1 = new Transaction<TheAccount>(acc1, acc2, 4000);
        tran1.execute();
    }
}

class Transaction<T extends TheAccount> {

    private T from;     // с какого счета перевод
    private T to;       // на какой счет перевод
    private int sum;    // сумма перевода

    Transaction(T from, T to, int sum) {
        this.from = from;
        this.to = to;
        this.sum = sum;
    }

    public void execute() {

        if (from.getSum() > sum) {
            from.setSum(from.getSum() - sum);
            to.setSum(to.getSum() + sum);
            System.out.printf("Account %s: %d \nAccount %s: %d \n",
                    from.getId(), from.getSum(), to.getId(), to.getSum());
        } else {
            System.out.printf("Operation is invalid");
        }
    }
}

class TheAccount {

    private String id;
    private int sum;

    TheAccount(String id, int sum) {
        this.id = id;
        this.sum = sum;
    }

    public String getId() {
        return id;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}