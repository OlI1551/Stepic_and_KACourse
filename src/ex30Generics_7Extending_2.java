/*
При этом класс-наследник может добавлять и использовать какие-то свои параметры типов:
 */
public class ex30Generics_7Extending_2 {
    public static void main(String[] args) {
        DepositAccount1<Integer, String> dAccount1 = new DepositAccount1(20, "Tom");
        System.out.println(dAccount1.getId() + " : " + dAccount1.getName());

        DepositAccount1<String, Integer> dAccount2 = new DepositAccount1("12345", 23456);
        System.out.println(dAccount2.getId() + " : " + dAccount2.getName());
    }
}
class OrdinaryAccount1<T>
{
    private T _id;
    T getId(){return _id;}
    OrdinaryAccount1(T id)
    {
        _id = id;
    }
}

class DepositAccount1<T, S> extends OrdinaryAccount1<T>{

    private S _name;
    S getName(){return _name;}
    DepositAccount1(T id, S name){
        super(id);
        this._name=name;
    }
}