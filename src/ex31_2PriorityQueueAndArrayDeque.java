/*
Методы коллекций Queue

Очереди представляют структуру данных, работающую по принципу FIFO (first in - first out).
То есть чем раньше был добавлен элемент в коллекцию, тем раньше он из нее удаляется.
Это стандартная модель однонаправленной очереди.
Однако бывают и двунаправленные - то есть такие, в которых мы можем добавить элемент не только в начало, но и в конец.
И соответственно удалить элемент не только из конца, но и из начала.

Особенностью классов очередей является то, что они реализуют специальные интерфейсы Queue или Deque.

Интерфейс Queue
Обобщенный интерфейс Queue<E> расширяет базовый интерфейс Collection и определяет поведение класса в качестве
однонаправленной очереди.
Свою функциональность он раскрывает через следующие методы:
void add(E obj): добавляет элемент в конец очереди
boolean offer(E obj): добавляет элемент obj в конец очереди. Если элемент удачно добавлен, возвращает true, иначе - false
E element(): возвращает, но не удаляет, элемент из начала очереди. Если очередь пуста, генерирует исключение NoSuchElementException
E peek(): возвращает без удаления элемент из начала очереди. Если очередь пуста, возвращает значение null
E remove(): возвращает с удалением элемент из начала очереди. Если очередь пуста, генерирует исключение NoSuchElementException
E poll(): возвращает с удалением элемент из начала очереди. Если очередь пуста, возвращает значение null

Таким образом, у всех классов, которые реализуют данный интерфейс, будет метод offer для добавления в очередь,
метод poll для извлечения элемента из головы очереди, и методы peek и element, позволяющие просто получить элемент из головы очереди.

Операции с Queue
1. add() - добавляет элемент в конец очереди.
Поправка: если очередь с приоритетом - т.е. PriorityQueue - элемент ставится не обязательно в конец, а в соответствии со своим приоритетом

2. remove() и poll() - удаляет верхний элемент из очереди.

3. offer() - пытается вставить элемент в конец очереди.

4. peek() и element() - показывают верхний элемент очереди

remove() и poll() - удаляем верхний элемент из очереди. Так сказать, "Три часа жду - надоело, иду домой!" 🙂
Но в чем между ними разница?
У метода remove() есть две формы - remove() и remove(Object o), а у poll() - только одна.
В первой форме оба метода одинаковые - они убирают "голову" (первый элемент) очереди. Обезглавливают так сказать 🙂
Но remove(Object o) позволяет убирать любой элемент, не только сверху. Например, попробуем убрать двойку - она лежит в середине очереди
Методы будут действовать по-разному если у нас пустая очередь.
- хотите Exception (т.е. если пустая очередь - недопустимое состояние) - берите remove() - программа заканчивается с 1
- не хотите - берите poll() (возвращает null и программа никак не реагирует - заканчивается с 0

offer() - пытается вставить в конец очереди. С английского "offer" переводится как "предложить".
Почему "предлагаем"? Потому что в очередях с фиксированным размером
у нас может не получится вставить элемент в очередь.

peek() (от англ. "подсматривать"), и element() - показывают верхний элемент очереди.



Интерфейс Deque
Интерфейс Deque расширяет вышеописанный интерфейс Queue и определяет поведение двунаправленной очереди,
которая работает как обычная однонаправленная очередь, либо как стек, действующий по принципу LIFO (последний вошел - первый вышел).
Интерфейс Deque определяет следующие методы:

void addFirst(E obj): добавляет элемент в начало очереди
void addLast(E obj): добавляет элемент obj в конец очереди
boolean offerFirst(E obj): добавляет элемент obj в самое начало очереди. Если элемент удачно добавлен, возвращает true, иначе - false
boolean offerLast(E obj): добавляет элемент obj в конец очереди. Если элемент удачно добавлен, возвращает true, иначе - false

E getFirst(): возвращает без удаления элемент из головы очереди. Если очередь пуста, генерирует исключение NoSuchElementException
E getLast(): возвращает без удаления последний элемент очереди. Если очередь пуста, генерирует исключение NoSuchElementException
E peekFirst(): возвращает без удаления элемент из начала очереди. Если очередь пуста, возвращает значение null
E peekLast(): возвращает без удаления последний элемент очереди. Если очередь пуста, возвращает значение null

E removeFirst(): возвращает с удалением элемент из начала очереди. Если очередь пуста, генерирует исключение NoSuchElementException
E removeLast(): возвращает с удалением элемент из конца очереди. Если очередь пуста, генерирует исключение NoSuchElementException
E pollFirst(): возвращает с удалением элемент из начала очереди. Если очередь пуста, возвращает значение null
E pollLast(): возвращает с удалением последний элемент очереди. Если очередь пуста, возвращает значение null

E pop(): возвращает с удалением элемент из начала очереди. Если очередь пуста, генерирует исключение NoSuchElementException
void push(E element): добавляет элемент в самое начало очереди

boolean removeFirstOccurrence(Object obj): удаляет первый встреченный элемент obj из очереди. Если удаление произшло, то возвращает true, иначе возвращает false.
boolean removeLastOccurrence(Object obj): удаляет последний встреченный элемент obj из очереди. Если удаление произшло, то возвращает true, иначе возвращает false.

Таким образом, наличие методов pop и push позволяет классам, реализующим этот элемент, действовать в качестве стека.
В то же время имеющийся функционал также позволяет создавать двунаправленные очереди, что делает классы,
применяющие данный интерфейс, довольно гибкими.

Класс ArrayDeque
В Java очереди представлены рядом классов. Один из них - класс ArrayDeque<E>.
Этот класс представляют обобщенную двунаправленную очередь, наследуя функционал от класса AbstractCollection и применяя интерфейс Deque.
В классе ArrayDeque определены следующие конструкторы:
1) ArrayDeque(): создает пустую очередь
2) ArrayDeque(Collection<? extends E> col): создает очередь, наполненную элементами из коллекции col
3) ArrayDeque(int capacity): создает очередь с начальной емкостью capacity. Если мы явно не указываем начальную емкость, то емкость по умолчанию будет равна 16
 */

import java.util.*;

public class ex31_2PriorityQueueAndArrayDeque {
    public static void main(String[] args) {
        // Создать
        Queue queueA = new LinkedList();
        Queue queueB = new PriorityQueue();

        // Добавить элементы
        queueA.add("element 1");
        queueA.add("element 2");
        queueA.add("element 3");

        // Посмотреть первый элемент
        Object firstElement2 = queueA.element();
        Object firstElement = queueA.peek();

        // После выполнения этого кода переменная firstElement будет содержать элемент value 1, который является первым элементом в очереди.
        // Peek() работает так же, как метод element (), за исключением того, что он не создает исключение, если очередь пуста.
        // Вместо этого он просто возвращает null

        //access via Iterator
        Iterator iterator = queueA.iterator();
        while (iterator.hasNext()) {
            String element =(String) iterator.next();
            System.out.println(element);
        }
        Iterator iterator1 = queueA.iterator(); // итератор надо создавать заново после каждого полного прохождения
        for (int i = 0; i < queueA.size(); i++) {
            String element = (String) iterator1.next();
            System.out.println(element);
        }
        //access via new for-loop
        for (Object object : queueA) {
            String element = (String) object;
            System.out.println(element);
        }
        // Источник: https://java-blog.ru/collections/queue-java

        // Если очередь с приоритетом, то элементы будут добавляться в соответствии с ним - числа по возрастанию по умолчанию
        PriorityQueue<Integer> myPriorityQueue = new PriorityQueue<Integer>();
        myPriorityQueue.add(1);
        myPriorityQueue.add(2);
        myPriorityQueue.add(3);
        myPriorityQueue.add(0);
        myPriorityQueue.add(30);
        for(int pq : myPriorityQueue) {
            System.out.println(pq);
        }
//        queueB.addAll(Arrays.asList(new Integer[] {20, 30, 40, 50, 60, 70, 80, 90}));
//        myPriorityQueue.addAll(queueB); // две PriorityQue будут соединены как попало
//        myPriorityQueue.addAll(Arrays.asList(new Integer[] {1, 2, 3, 4, 5})); // можно добавить целую коллекцию или массив - запись будет как попало
        for(int pq : myPriorityQueue) {
            System.out.println(pq);
        }
        System.out.println(myPriorityQueue.toString());
        String str = Arrays.toString(myPriorityQueue.toArray());
        System.out.println(str);


        ArrayDeque<String> states = new ArrayDeque<String>();
        // стандартное добавление элементов
        states.add("Germany");
        states.addFirst("France"); // добавляем элемент в самое начало
        states.push("Great Britain"); // добавляем элемент в самое начало
        states.addLast("Spain"); // добавляем элемент в конец коллекции
        states.add("Italy");

        // получаем первый элемент без удаления
        String sFirst = states.getFirst();
        System.out.println(sFirst);     // Great Britain
        // получаем последний элемент без удаления
        String sLast = states.getLast();
        System.out.println(sLast);      // Italy

        System.out.printf("Queue size: %d \n", states.size());  // 5

        // перебор коллекции
        while(states.peek()!=null){
            // извлечение c начала
            System.out.println(states.pop());
        }

        // очередь из объектов Person
        ArrayDeque<Person_1> people = new ArrayDeque<Person_1>();
        people.addFirst(new Person_1 ("Tom"));
        people.addLast(new Person_1 ("Nick"));
        // перебор без извлечения
        for(Person_1 p : people){

            System.out.println(p.getName());
        }
    }
}
class Person_1 {

    private final String name;
    public Person_1 (String value) {
        name=value;
    }
    String getName() {
        return name;
    }
}

