/*
Интерфейс Set и класс HashSet
Последнее обновление: 24.04.2018


Интерфейс Set расширяет интерфейс Collection и представляет набор уникальных элементов.
Set не добавляет новых методов, только вносит изменения в унаследованные.
В частности, метод add() добавляет элемент в коллекцию и возвращает true, если в коллекции еще нет такого элемента.

Обобщенный класс HashSet представляет хеш-таблицу. Он наследует свой функционал от класса AbstractSet,
а также реализует интерфейс Set.
Хеш-таблица представляет такую структуру данных, в которой все объекты имеют уникальный ключ или хеш-код.
Данный ключ позволяет уникально идентифицировать объект в таблице.

Для создания объекта HashSet можно воспользоваться одним из следующих конструкторов:
1) HashSet(): создает пустой список
2) HashSet(Collection<? extends E> col): создает хеш-таблицу, в которую добавляет все элементы коллекции col
3) HashSet(int capacity): параметр capacity указывает начальную емкость таблицы, которая по умолчанию равна 16
4) HashSet(int capacity, float koef): параметр koef или коэффициент заполнения, значение которого должно быть в пределах от 0.0 до 1.0, указывает, насколько должна быть заполнена емкость объектами прежде чем произойдет ее расширение. Например, коэффициент 0.75 указывает, что при заполнении емкости на 3/4 произойдет ее расширение.

Класс HashSet не добавляет новых методов, реализуя лишь те, что объявлены в родительских классах и применяемых интерфейсах:
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.concurrent.CopyOnWriteArrayList;

public class ex31_4HashSet {
    public static void main(String[] args) {

        HashSet<String> states = new HashSet<String>();

        // добавим в список ряд элементов
        states.add("Germany");
        states.add("France");
        states.add("Italy");
        // пытаемся добавить элемент, который уже есть в коллекции
        boolean isAdded = states.add("Germany");
        System.out.println(isAdded);    // false

        System.out.printf("Set contains %d elements \n", states.size());    // 3

        for(String state : states){

            System.out.println(state);
        }
        // удаление элемента
        states.remove("Germany");

        // хеш-таблица объектов Person
        HashSet<Person_4> people = new HashSet<Person_4>();
        people.add(new Person_4("Mike"));
        people.add(new Person_4("Tom"));
        people.add(new Person_4("Nick"));
        for(Person_4 p : people){

            System.out.println(p.getName());
        }
/*
Синтаксис
Обратите внимание - как и в списках, мы указываем тип данных, которые находятся в множестве:
1HashSet<String> myHashSet = new HashSet<String>();

Операции с множествами

1. add() - добавляет элемент в множество

2. remove() - удаляет элемент из множества

3. contains() - определяет, есть ли элемент в множестве

4. size() - возвращает размер множества

5. clear() - удаляет все элементы из коллекции

6. isEmpty() - возвращает true если множество пустое, и false если там есть хотя бы 1 элемент
 */
        HashSet<Integer> myHashSet = new HashSet<Integer>();

        myHashSet.add(1);
        myHashSet.add(2);
        myHashSet.add(3);

        System.out.println("Before remove:");
        for (int i : myHashSet)
            System.out.println(i);

        myHashSet.remove(1);
        System.out.println("After remove:");
        for (int i : myHashSet)
            System.out.println(i);

        System.out.println("Does myHashSet contain '1'? " + myHashSet.contains(1));
        System.out.println("Does myHashSet contain '11'? " + myHashSet.contains(11));

        System.out.println("Before we add anything myHashSet size is " + myHashSet.size());
        myHashSet.add(4);
        myHashSet.add(5);

        System.out.println("Now myHashSet size is " + myHashSet.size());

        System.out.println("After adding elements myHashSet is empty: " + myHashSet.isEmpty());
        myHashSet.clear();
        System.out.println("After clear myHashSet size is " + myHashSet.size());
        System.out.println("After clear myHashSet is empty: " + myHashSet.isEmpty());

        HashSet<Integer> myNum1 = new HashSet<>(new ArrayList<>(Arrays.asList(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})));
        HashSet<Integer> myNum2 = new HashSet<>(new ArrayList<>(Arrays.asList(new Integer[]{1, 3, 5, 20})));
        myNum1.addAll(myNum2);  // добавить все элементы коллекции myNum2
        myNum1.retainAll(myNum2);  // оставить в myNum1 только элементы, указанные в коллекции myNum2
        for (int i : myNum1) {
            System.out.println(i);
        } // должно быть 1, 3, 5, 20

        System.out.println("----------------");
        HashSet<Integer> myNum3 = new HashSet<>(new ArrayList<>(Arrays.asList(new Integer[]{1})));
        myNum1.retainAll(myNum3);  // оставить в myNum1 только элементы, указанные в коллекции myNum3
        for (int i : myNum1) {
            System.out.println(i);
        } // должно быть 1
    }
}
class Person_4 {

    private final String name;
    public Person_4(String personName){
        name = personName;
    }
    String getName(){return name;}
}

