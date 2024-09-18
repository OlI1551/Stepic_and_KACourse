/*
Класс ArrayList и интерфейс List
Последнее обновление: 24.04.2018

Для создания простых списков применяется интерфейс List, который расширяет функцональность интерфейса Collection.
Некоторые наиболее часто используемые методы интерфейса List:
void add(int index, E obj): добавляет в список по индексу index объект obj
boolean addAll(int index, Collection<? extends E> col): добавляет в список по индексу index все элементы коллекции col. Если в результате добавления список был изменен, то возвращается true, иначе возвращается false
E get(int index): возвращает объект из списка по индексу index
int indexOf(Object obj): возвращает индекс первого вхождения объекта obj в список. Если объект не найден, то возвращается -1
int lastIndexOf(Object obj): возвращает индекс последнего вхождения объекта obj в список. Если объект не найден, то возвращается -1
ListIterator<E> listIterator (): возвращает объект ListIterator для обхода элементов списка
static <E> List<E> of(элементы): создает из набора элементов объект List
E remove(int index): удаляет объект из списка по индексу index, возвращая при этом удаленный объект
E set(int index, E obj): присваивает значение объекта obj элементу, который находится по индексу index
void sort(Comparator<? super E> comp): сортирует список с помощью компаратора comp
List<E> subList(int start, int end): получает набор элементов, которые находятся в списке между индексами start и end

По умолчанию в Java есть встроенная реализация этого интерфейса - класс ArrayList.
Класс ArrayList представляет обобщенную коллекцию, которая наследует свою функциональность от класса AbstractList
и применяет интерфейс List.
Проще говоря, ArrayList представляет простой список, аналогичный массиву, за тем исключением, что количество элементов в нем не фиксировано.
ArrayList имеет следующие конструкторы:
1) ArrayList(): создает пустой список
2) ArrayList(Collection <? extends E> col): создает список, в который добавляются все элементы коллекции col.
3) ArrayList (int capacity): создает список, который имеет начальную емкость capacity

Емкость в ArrayList представляет размер массива, который будет использоваться для хранения объектов.
При добавлении элементов фактически происходит перераспределение памяти - создание нового массива
и копирование в него элементов из старого массива.
Изначальное задание емкости ArrayList позволяет снизить подобные перераспределения памяти, тем самым повышая производительность.

Создание
// Создаем новый список
ArrayList<String> arrayList = new ArrayList<>();
// Создается новый список и указывается начальный размер внутреннего массива
ArrayList<String> arrayListLarge = new ArrayList<>(100000);

// Создаем новый LinkedList
LinkedList<String> linkedList = new LinkedList<>();
Добавление элемента
// Новый элемент добавляется в конец
arrayList.add("Johhny");
// Новый элемент добавляется в указанную позицию (в данном случае — в начало)
arrayList.add(0, "Watson");

// Новый элемент добавляется в конец двусвязного списка
linkedList.add("Java");
// Новый элемент добавляется в нулевую позицию списка:
linkedList.addFirst("I think");
// Новый элемент добавляется в конец списка
linkedList.addLast("language");
// Новый элемент добавляется в указанную позицию
linkedList.add(2, "is a terrific");

// Получение размера списков
int arraySize = arrayList.size(); // 2
int linkedSize = linkedList.size(); // 4


Удаление элемента
// Удаление элемента по индексу
arrayList.remove(0);
// Удаление элемента по значению
arrayList.remove("Johnny");

// Удаление первого элемента в списке
linkedList.removeFirst();
// Удаление первого элемента в списке, фактически вызов предыдущего метода
linkedList.remove();
// Удаление последнего элемента в списке
linkedList.removeLast();
// Удаление первого вхождения элемента в список
linkedList.removeFirstOccurrence("language");
// Удаление последнего вхождения элемента в список
linkedList.removeLastOccurrence("Java");
// Удаление по индексу
linkedList.remove(2);


Если происходит удаление объекта по индексу, метод возвращает удаленный объект.

Если объект удаляется по значению (или у LinkedList удаляется первый или последний элементы), метод возвращает true, если объект найден и удален, false — в ином случае.
Доступ к элементу и поиск в списке
// Доступ к элементу по индексу
String arrayElement = arrayList.get(2);
// Поиск элемента по значению
int arrayIndex = arrayList.indexOf("Watson");
// Поиск последнего индекса вхождения элемента в список
int lastArrayIndex = arrayList.lastIndexOf("Watson");

// Доступ по индексу
String linkedElement = linkedList.get(3);
// Получение первого элемента
String firstLinkedElement = linkedList.getFirst();
// Получение последнего элемента
String lastLinkedElement = linkedList.getLast();

// Поиск элемента по значению
int linkedIndex = linkedList.indexOf("Java");
// Поиск последнего индекса вхождения элемента в список
int lastLinkedIndex = linkedList.lastIndexOf("Java");
Обход в цикле
// Использование обычного цикла
for(int i = 0; i<arrayList.size(); i++) {
  String value = arrayList.get(i);
  System.out.println(value);
}

for(int i = 0; i<linkedList.size(); i++) {
  String value = linkedList.get(i);
  System.out.println(value);
}

// Использование цикла for-each
for(String s : arrayList) {
  System.out.println(s);
}

for(String s : linkedList) {
  System.out.println(s);
}

Здесь стоит сказать пару слов о поиске. Многие начинающие разработчики при поиске элемента в списке начинают поиск в цикле,
сравнивая все элементы с искомым, несмотря на наличие методов indexOf() и lastIndexOf().

Также можно использовать метод contains() для получения факта наличия элемента в списке:

boolean isContainsSherlock = arrayList.contains("Sherlock");
boolean isContainsPhp = linkedList.contains("Php");

 */
import java.util.*;
import java.util.function.DoubleToIntFunction;

import static java.util.List.of;

public class ex31_1ArrayList {
    public static void main(String[] args) {

        ArrayList<String> people = new ArrayList<String>();
        // добавим в список ряд элементов
        people.add("Tom");
        people.add("Alice");
        people.add("Kate");
        people.add("Sam");
        people.add(1, "Bob"); // добавляем элемент по индексу 1

        System.out.println(people.get(1));// получаем 2-й объект
        people.set(1, "Robert"); // установка нового значения для 2-го объекта

        System.out.printf("ArrayList has %d elements \n", people.size());
        for(String person : people){

            System.out.println(person);
        }
        // проверяем наличие элемента
        if(people.contains("Tom")){

            System.out.println("ArrayList contains Tom");
        }

        // удалим несколько объектов
        // удаление конкретного элемента
        people.remove("Robert");
        // удаление по индексу
        people.remove(0);

        Object[] peopleArray = people.toArray();
        for(Object person : peopleArray){

            System.out.println(person);
        }
        List<Integer> numList = List.of (1,2,3);
        System.out.println(numList);
        LinkedList<Integer> numList1 = new LinkedList<>(List.of(1, 2, 3, 4, 5));
        System.out.println(numList1);

        PriorityQueue<Integer> myQueue = new PriorityQueue<>(List.of(20, 0, 5, 0, 1));
        System.out.println(myQueue);
        PriorityQueue<Integer> myQueueHandmade = new PriorityQueue<>();
        myQueueHandmade.add(20);
        myQueueHandmade.add(0);
        myQueueHandmade.add(5);
        myQueueHandmade.add(0);
        myQueueHandmade.add(1);
        System.out.println(myQueueHandmade);

        HashSet<String> strHashSet = new HashSet<>(List.of("one", "two", "three", "three"));
        System.out.println(strHashSet);
    }
}
/*
Консольный вывод программы:
Bob
ArrayList has 5 elements
Tom
Robert
Alice
Kate
Sam
ArrayList contains Tom
Alice
Kate
Sam
Здесь объект ArrayList типизируется классом String, поэтому список будет хранить только строки.


Поскольку класс ArrayList применяет интерфейс Collection<E>, то мы можем использовать методы данного интерфейса для управления объектами в списке.

Для добавления вызывается метод add. С его помощью мы можем добавлять объект в конец списка: people.add("Tom").
Также мы можем добавить объект на определенное место в списке, например, добавим объект на второе место
(то есть по индексу 1, так как нумерация начинается с нуля): people.add(1, "Bob")

Метод size() позволяет узнать количество объектов в коллекции.

Проверку на наличие элемента в коллекции производится с помощью метода contains. А удаление с помощью метода remove.
И так же, как и с добавлением, мы можем удалить либо конкретный элемент people.remove("Tom");,
либо элемент по индексу people.remove(0); - удаление первого элемента.

Получить определенный элемент по индексу мы можем с помощью метода get(): String person = people.get(1);,
а установить элемент по индексу с помощью метода set: people.set(1, "Robert");

С помощью метода toArray() мы можем преобразовать список в массив объектов.

И поскольку класс ArrayList реализует интерфейс Iterable,
то мы можем пробежаться по списку в цикле аля for-each: for(String person : people).

Хотя мы можем свободно добавлять в объект ArrayList дополнительные объекты, в отличие от массива,
однако в реальности ArrayList использует для хранения объектов опять же массив.
По умолчанию данный массив предназначен для 10 объектов. Если в процессе программы добавляется гораздо больше,
то создается новый массив, который может вместить в себя все количество.
Подобные перераспределения памяти уменьшают производительность. Поэтому если мы точно знаем, что у нас список
не будет содержать больше определенного количества элементов, например, 25, то мы можем сразу же явным образом
установить это количество, либо в конструкторе: ArrayList<String> people = new ArrayList<String>(25);,
либо с помощью метода ensureCapacity: people.ensureCapacity(25);



Методы коллекций List
List — интерфейс, который традиционно рассматривают первым и которым пользуются чаще всего.
Название переводится как «список»: интерфейс представляет собой упорядоченную коллекцию данных, похожую на массив.
Это значит, что у его элементов есть порядковые номера, показывающие их расположение в списке, — индексы.
Но, в отличие от массива, List динамический, о чем мы говорили выше, — в нем можно изменять количество элементов.

Реализации.
Классический динамический массив, или ArrayList, — не единственная реализация List.
Сейчас их как минимум четыре, но основной признак всех — упорядоченность элементов.

Вторая распространенная реализация — LinkedList, связанный список.
Он отличается от ArrayList наличием связности: в каждом элементе есть указатели на предыдущий и следующий элемент.
Методы у него такие же, как у динамического массива, но действия с таким списком различаются сложностью выполнения и скоростью.
Методы add() и remove() в связанном списке имеют фиксированную скорость выполнения, поэтому оптимальнее.
А вот обращение к элементу по индексу быстрее в ArrayList.

Пример использования ArrayList

import java.util.ArrayList;

public class ArrayListExample {
    public static void main(String[] args){
        System.out.println("java.util.ArrayList<E> Example ");
        ArrayList<String>  listA = new ArrayList<>();

        //---------------------------------
        System.out.println("Добавление: ");

        // Устанавливает емкость массива, для хранения заданного количества элементов без изменения
        // внутреннего массива для хранения данных в памяти.
        listA.ensureCapacity(10);

        listA.add("a0");
        listA.add("a1");
        System.out.println("- в конец: " + listA);

        listA.add(1, "a2");
        System.out.println("- по индексу: " + listA);

        //---------------------------------
        System.out.println("Получение элементов и информации: ");

        System.out.print("- каждого элемента (for each): ");
        for (String el:listA){
            System.out.print(el+" ");
        }
        System.out.println();

        System.out.println("- элемента по индексу: " + listA.get(1));

        System.out.println("- размер: " + listA.size());

        //---------------------------------
        System.out.println("Изменение: ");

        listA.set(1, "c1");
        System.out.println("- по индексу: " + listA);

        // Сокращает емкость списочного массива до его текущего размера
        listA.trimToSize();

        //---------------------------------
        System.out.println("Удаление: ");

        listA.remove(1);
        System.out.println("- по индексу: " + listA);
    }
}
Результат

java.util.ArrayList<E> Example
Добавление:
- в конец: [a0, a1]
- по индексу: [a0, a2, a1]
Получение элементов и информации:
- каждого элемента (for each): a0 a2 a1
- элемента по индексу: a2
- размер: 3
Изменение:
- по индексу: [a0, c1, a1]
Удаление:
- по индексу: [a0, a1]

LinkedList — реализует интерфейс List. Является представителем двунаправленного списка,
где каждый элемент структуры содержит указатели на предыдущий и следующий элементы. Итератор поддерживает обход в обе стороны.
Реализует методы получения, удаления и вставки в начало, середину и конец списка. Позволяет добавлять любые элементы в том числе и null.
 */