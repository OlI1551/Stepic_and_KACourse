/*
И еще одна ситуация - класс-наследник вообще может не быть обобщенным:
Здесь при наследовании явным образом указывается тип, который будет использоваться конструкциями базового класса,
то есть тип Integer. Затем в конструктор базового класса передается значение именно этого типа - в данном случае число 5.
 */
public class ex30Generics_7Extending_3 {
    public static void main(String[] args) {
        DepositAccount2 dAccount1 = new DepositAccount2();
        System.out.println(dAccount1.getId());

    }
}
class OrdinaryAccount2<T>
{
    private T _id;
    T getId(){return _id;}
    OrdinaryAccount2(T id)
    {
        _id = id;
    }
}

class DepositAccount2 extends OrdinaryAccount2<Integer>{

    DepositAccount2(){
        super(5);
    }
}
/*
ОБОБЩЕННЫЙ КЛАСС-НАСЛЕДНИК
Также может быть ситуация, когда базовый класс является обычным необобщенным классом. Например:

class Account
{
    private String _name;
    String getName(){return _name;}
    Account(String name)
    {
        _name=name;
    }
}

class DepositAccount<T> extends Account{

    private T _id;
    T getId(){return _id;}
    DepositAccount(String name, T id){
        super(name);
        _id = id;
    }
}
В этом случае использование конструкций базового класса в наследнике происходит как обычно.

ПРЕОБРАЗОВАНИЕ ОБОБЩЕННЫХ ТИПОВ
Объект одного обобщенного типа можно привести к другому типу, если они используют один и тот же тип.
Рассмотрим преобразование типов на примере следующих двух обобщенных классов:

class Account<T>
{
    private T _id;
    T getId(){return _id;}
    Account(T id)
    {
        _id = id;
    }
}

class DepositAccount<T> extends Account<T>{

    DepositAccount(T id){
        super(id);
    }
}
Мы можем привести объект DepositAccount<Integer> к Account<Integer> или DepositAccount<String> к Account<String>:

DepositAccount<Integer> depAccount = new DepositAccount(10);
Account<Integer> account = (Account<Integer>)depAccount;
System.out.println(account.getId());
Но сделать то же самое с разнотипными объектами мы не можем. Например, следующий код не будет работать:

DepositAccount<Integer> depAccount = new DepositAccount(10);
Account<String> account = (Account<String>)depAccount;
 */