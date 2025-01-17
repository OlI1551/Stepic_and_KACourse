/*
SortedSet
Интерфейс SortedSet предназначен для создания коллекций, который хранят элементы в отсортированном виде (сортировка по возрастанию).
SortedSet расширяет интерфейс Set, поэтому такая коллекция опять же хранит только уникальные значения.
SortedSet предоставляет следующие методы:
E first(): возвращает первый элемент набора
E last(): возвращает последний элемент набора
SortedSet<E> headSet(E end): возвращает объект SortedSet, который содержит все элементы первичного набора до элемента end
SortedSet<E> subSet(E start, E end): возвращает объект SortedSet, который содержит все элементы первичного набора между элементами start и end
SortedSet<E> tailSet(E start): возвращает объект SortedSet, который содержит все элементы первичного набора, начиная с элемента start

NavigableSet
Интерфейс NavigableSet расширяет интерфейс SortedSet и позволяет извлекать элементы на основании их значений.
NavigableSet определяет следующие методы:
E ceiling(E obj): ищет в наборе наименьший элемент e, который больше obj (e >=obj). Если такой элемент найден, то он возвращается в качестве результата. Иначе возвращается null.
E floor(E obj): ищет в наборе наибольший элемент e, который меньше элемента obj (e <=obj). Если такой элемент найден, то он возвращается в качестве результата. Иначе возвращается null.
E higher(E obj): ищет в наборе наименьший элемент e, который больше элемента obj (e >obj). Если такой элемент найден, то он возвращается в качестве результата. Иначе возвращается null.
E lower(E obj): ищет в наборе наибольший элемент e, который меньше элемента obj (e <obj). Если такой элемент найден, то он возвращается в качестве результата. Иначе возвращается null.
E pollFirst(): возвращает первый элемент и удаляет его из набора
E pollLast(): возвращает последний элемент и удаляет его из набора
NavigableSet<E> descendingSet(): возвращает объект NavigableSet, который содержит все элементы первичного набора NavigableSet в обратном порядке
NavigableSet<E> headSet(E upperBound, boolean incl): возвращает объект NavigableSet, который содержит все элементы первичного набора NavigableSet до upperBound. Параметр incl при значении true, позволяет включить в выходной набор элемент upperBound
NavigableSet<E> tailSet(E lowerBound, boolean incl): возвращает объект NavigableSet, который содержит все элементы первичного набора NavigableSet, начиная с lowerBound. Параметр incl при значении true, позволяет включить в выходной набор элемент lowerBound
NavigableSet<E> subSet(E lowerBound, boolean lowerIncl, E upperBound, boolean highIncl): возвращает объект NavigableSet, который содержит все элементы первичного набора NavigableSet от lowerBound до upperBound.

TreeSet
Обобщенный класс TreeSet<E> представляет структуру данных в виде дерева, в котором все объекты
хранятся в отсортированном виде по возрастанию.
TreeSet является наследником класса AbstractSet и реализует интерфейс NavigableSet, а следовательно, и интерфейс SortedSet.

В классе TreeSet определены следующие конструкторы:
1) TreeSet(): создает пустое дерево
2) TreeSet(Collection<? extends E> col): создает дерево, в которое добавляет все элементы коллекции col
3) TreeSet(SortedSet <E> set): создает дерево, в которое добавляет все элементы сортированного набора set
4) TreeSet(Comparator<? super E> comparator): создает пустое дерево, где все добавляемые элементы впоследствии будут отсортированы компаратором.

TreeSet поддерживает все стандартные методы для вставки и удаления элементов:
 */
import java.util.NavigableSet;
import java.util.SortedSet;
import java.util.TreeSet;

public class ex31_5TreeSet {
    public static void main(String[] args) {

        TreeSet<String> states = new TreeSet<String>();

        // добавим в список ряд элементов
        states.add("Germany");
        states.add("France");
        states.add("Italy");
        states.add("Great Britain");

        System.out.printf("TreeSet contains %d elements \n", states.size());

        // удаление элемента
        states.remove("Germany");
        for(String state : states){
            System.out.println(state);
        }
//        И поскольку при вставке объекты сразу же сортируются по возрастанию, то при выводе в цикле for мы получим отсортированный набор:
//        TreeSet contains 4 elements
//        France
//        Great Britain
//        Italy
//        Так как TreeSet реализует интерфейс NavigableSet, а через него и SortedSet, то мы можем применить к структуре дерева различные методы:
        TreeSet<String> states1 = new TreeSet<String>();

        // добавим в список ряд элементов
        states1.add("Germany");
        states1.add("France");
        states1.add("Italy");
        states1.add("Spain");
        states1.add("Great Britain");

        System.out.println(states1.first()); // получим первый - самый меньший элемент
        System.out.println(states1.last()); // получим последний - самый больший элемент
        // получим поднабор от одного элемента до другого
        SortedSet<String> set = states1.subSet("Germany", "Italy");
        System.out.println(set);
        // элемент из набора, который больше текущего
        String greater = states1.higher("Germany");
        // элемент из набора, который меньше текущего
        String lower = states1.lower("Germany");
        // возвращаем набор в обратном порядке
        NavigableSet<String> navSet = states1.descendingSet();
        // возвращаем набор в котором все элементы меньше текущего
        SortedSet<String> setLower=states1.headSet("Germany");
        // возвращаем набор в котором все элементы больше текущего
        SortedSet<String> setGreater=states1.tailSet("Germany");
        System.out.println(navSet);
        System.out.println(setLower);
        System.out.println(setGreater);
    }
}
