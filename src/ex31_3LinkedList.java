/*
Класс LinkedList
Последнее обновление: 24.04.2018

Обобщенный класс LinkedList<E> представляет структуру данных в виде связанного списка.
Он наследуется от класса AbstractSequentialList и реализует интерфейсы List, Dequeue и Queue.
То есть он соединяет функциональность работы со списком и фукциональность очереди.

Класс LinkedList имеет следующие конструкторы:
1) LinkedList(): создает пустой список
2) LinkedList(Collection<? extends E> col): создает список, в который добавляет все элементы коллекции col

LinkedList содержит все те методы, которые определены в интерфейсах List, Queue, Deque. Некоторые из них:
addFirst() / offerFirst(): добавляет элемент в начало списка
addLast() / offerLast(): добавляет элемент в конец списка
removeFirst() / pollFirst(): удаляет первый элемент из начала списка
removeLast() / pollLast(): удаляет последний элемент из конца списка
getFirst() / peekFirst(): получает первый элемент
getLast() / peekLast(): получает последний элемент
 */
import java.util.LinkedList;
public class ex31_3LinkedList {
    public static void main(String[] args) {

        LinkedList<String> states = new LinkedList<String>();

        // добавим в список ряд элементов
        states.add("Germany");
        states.add("France");
        states.addLast("Great Britain"); // добавляем на последнее место
        states.addFirst("Spain"); // добавляем на первое место
        states.add(1, "Italy"); // добавляем элемент по индексу 1

        System.out.printf("List has %d elements \n", states.size());
        System.out.println(states.get(1));
        states.set(1, "Portugal");
        for(String state : states){

            System.out.println(state);
        }
        // проверка на наличие элемента в списке
        if(states.contains("Germany")){

            System.out.println("List contains Germany");
        }

        states.remove("Germany");
        states.removeFirst(); // удаление первого элемента
        states.removeLast(); // удаление последнего элемента

        LinkedList<Person_2> people = new LinkedList<Person_2>();
        people.add(new Person_2("Mike"));
        people.addFirst(new Person_2("Tom"));
        people.addLast(new Person_2("Nick"));
        people.remove(1); // удаление второго элемента

        for(Person_2 p : people){

            System.out.println(p.getName());
        }
        Person_2 first = people.getFirst();
        System.out.println(first.getName()); // вывод первого элемента
    }
}
class Person_2 {

    private final String name;
    public Person_2 (String value){

        name=value;
    }
    String getName(){return name;}
}
/*
Здесь создаются и используются два списка: для строк и для объектов класса Person.
При этом в дополнение к методам addFirst/removeLast и т.д., нам также доступны стандартные методы,
определенные в интерфейсе Collection: add(), remove, contains, size и другие.
Поэтому мы можем использовать разные методы для одного и того же действия.
Например, добавление в самое начало списка можно сделать так: states.addFirst("Spain");, а можно сделать так: states.add(0, "Spain");
 */
