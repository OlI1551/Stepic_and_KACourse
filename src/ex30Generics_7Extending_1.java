/*
Обобщенные классы могут участвовать в иерархии наследования: могут наследоваться от других,
либо выполнять роль базовых классов. Рассмотрим различные ситуации.

Базовый обобщенный класс
При наследовании от обобщенного класса класс-наследник должен передавать данные о типе в конструкции базового класса:
В конструкторе DepositAccount идет обращение к конструктору базового класса, в который передаются данные о типе.
 */
public class ex30Generics_7Extending_1 {
    public static void main(String[] args) {
        DepositAccount dAccount1 = new DepositAccount(20);
        System.out.println(dAccount1.getId());

        DepositAccount dAccount2 = new DepositAccount("12345");
        System.out.println(dAccount2.getId());
    }
}
class OrdinaryAccount<T>
{
    private T _id;
    T getId(){return _id;}
    OrdinaryAccount(T id)
    {
        _id = id;
    }
}

class DepositAccount<T> extends OrdinaryAccount<T>{

    DepositAccount(T id){
        super(id);
    }
}