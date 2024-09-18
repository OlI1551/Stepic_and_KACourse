/*
Обобщенные методы
Кроме обобщенных типов можно также создавать обобщенные методы,
которые точно также будут использовать универсальные параметры.

Особенностью обобщенного метода является использование универсального параметра
в объявлении метода после всех модификаторов и перед типом возвращаемого значения.

public <T> void print(T[] items)
Затем внутри метода все значения типа T будут представлять данный универсальный параметр.

При вызове подобного метода перед его именем в угловых скобках указывается,
какой тип будет передаваться на место универсального параметра:

printer.<String>print(people);
printer.<Integer>print(numbers);
 */
public class ex30Generics_3Methods {

    public static void main(String[] args) {

        Printer printer = new Printer();
        String[] people = {"Tom", "Alice", "Sam", "Kate", "Bob", "Helen"};
        Integer[] numbers = {23, 4, 5, 2, 13, 456, 4};
        printer.<String>print(people);
        printer.<Integer>print(numbers);
    }
}

class Printer {

    public <T> void print(T[] items){
        for(T item: items){
            System.out.println(item);
        }
    }
}