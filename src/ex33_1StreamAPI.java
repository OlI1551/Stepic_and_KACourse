/*
В основе Stream API лежит интерфейс BaseStream. Его полное определение:
interface BaseStream<T , S extends BaseStream<T , S>>
Здесь параметр T означает тип данных в потоке, а S - тип потока, который наследуется от интерфейса BaseStream.

BaseStream определяет базовый функционал для работы с потоками, которые реализуется через его методы:
void close(): закрывает поток
boolean isParallel(): возвращает true, если поток является параллельным
Iterator<Т> iterator(): возвращает ссылку на итератор потока
Spliterator<Т> spliterator(): возвращает ссылку на сплитератор потока
S parallel(): возвращает параллельный поток (параллельные потоки могут задействовать несколько ядер процессора в многоядерных архитектурах)
S sequential(): возвращает последовательный поток
S unordered(): возвращает неупорядоченный поток
От интерфейса BaseStream наследуется ряд интерфейсов, предназначенных для создания конкретных потоков:

Stream<T>: используется для потоков данных, представляющих любой ссылочный тип
IntStream: используется для потоков с типом данных int
DoubleStream: используется для потоков с типом данных double
LongStream: используется для потоков с типом данных long

При работе с потоками, которые представляют определенный примитивный тип - double, int, long
проще использовать интерфейсы DoubleStream, IntStream, LongStream.
Но в большинстве случаев, как правило, работа происходит с более сложными данными,
для которых предназначен интерфейс Stream<T>. Рассмотрим некоторые его методы:
boolean allMatch(Predicate<? super T> predicate): возвращает true, если все элементы потока удовлетворяют условию в предикате. Терминальная операция
boolean anyMatch(Predicate<? super T> predicate): возвращает true, если хоть один элемент потока удовлетворяют условию в предикате. Терминальная операция
<R,A> R collect(Collector<? super T,A,R> collector): добавляет элементы в неизменяемый контейнер с типом R. T представляет тип данных из вызывающего потока, а A - тип данных в контейнере. Терминальная операция
long count(): возвращает количество элементов в потоке. Терминальная операция.
Stream<T> concat(Stream<? extends T> a, Stream<? extends T> b): объединяет два потока. Промежуточная операция
Stream<T> distinct(): возвращает поток, в котором имеются только уникальные данные с типом T. Промежуточная операция
Stream<T> dropWhile(Predicate<? super T> predicate): пропускает элементы, которые соответствуют условию в predicate, пока не попадется элемент, который не соответствует условию. Выбранные элементы возвращаются в виде потока. Промежуточная операция.
Stream<T> filter(Predicate<? super T> predicate): фильтрует элементы в соответствии с условием в предикате. Промежуточная операция
Optional<T> findFirst(): возвращает первый элемент из потока. Терминальная операция
Optional<T> findAny(): возвращает первый попавшийся элемент из потока. Терминальная операция
void forEach(Consumer<? super T> action): для каждого элемента выполняется действие action. Терминальная операция
Stream<T> limit(long maxSize): оставляет в потоке только maxSize элементов. Промежуточная операция
Optional<T> max(Comparator<? super T> comparator): возвращает максимальный элемент из потока. Для сравнения элементов применяется компаратор comparator. Терминальная операция
Optional<T> min(Comparator<? super T> comparator): возвращает минимальный элемент из потока. Для сравнения элементов применяется компаратор comparator. Терминальная операция
<R> Stream<R> map(Function<? super T,? extends R> mapper): преобразует элементы типа T в элементы типа R и возвращает поток с элементами R. Промежуточная операция
<R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper): позволяет преобразовать элемент типа T в несколько элементов типа R и возвращает поток с элементами R. Промежуточная операция
boolean noneMatch(Predicate<? super T> predicate): возвращает true, если ни один из элементов в потоке не удовлетворяет условию в предикате. Терминальная операция
Stream<T> skip(long n): возвращает поток, в котором отсутствуют первые n элементов. Промежуточная операция.
Stream<T> sorted(): возвращает отсортированный поток. Промежуточная операция.
Stream<T> sorted(Comparator<? super T> comparator): возвращает отсортированный в соответствии с компаратором поток. Промежуточная операция.
Stream<T> takeWhile(Predicate<? super T> predicate): выбирает из потока элементы, пока они соответствуют условию в predicate. Выбранные элементы возвращаются в виде потока. Промежуточная операция.
Object[] toArray(): возвращает массив из элементов потока. Терминальная операция.

Несмотря на то, что все эти операции позволяют взаимодействовать с потоком как неким набором данных наподобие коллекции,
важно понимать отличие коллекций от потоков:
1) Потоки не хранят элементы. Элементы, используемые в потоках, могут храниться в коллекции,
либо при необходимости могут быть напрямую сгенерированы.
2) Операции с потоками не изменяют источника данных.
Операции с потоками лишь возвращают новый поток с результатами этих операций.
3) Для потоков характерно отложенное выполнение.
То есть выполнение всех операций с потоком происходит лишь тогда,
когда выполняется терминальная операция и возвращается конкретный результат, а не новый поток.

Создание стримов

Поток элементов может быть получен разными способами, например:
Stream из List
List<String> list = new ArrayList<>();
list.stream();
list.parallelStream();           // параллельный поток

Stream из Map
Map<String, String> map = new HashMap<>();
map.entrySet().stream();
map.values().stream();

Stream из массива, используя статический метод класса Arrays
String[] array = new String[10];
Arrays.stream(array);

Stream из элементов, используя статические методы Классов-потоков
Stream.of("a", "b", "c");        // поток из элементов
Stream.of(array);                // поток из элементов массива
Stream.of(list);                 // поток из элементов списка List
Stream.generate(Math::random);   // генерация потока рандомных чисел
Stream.concat(stream1, stream2); // объединяет два потока в один
IntStream.range(1, 10);          // поток диапазона чисел от 1 до 9
IntStream.rangeClosed(1, 10);    // поток диапазона чисел от 1 до 10
Stream из строк буфера BufferedReader
BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
bufferedReader.lines();

Stream из строк файла через статический метод класса Files
Path path = Path.of("/root/test.txt");
Files.lines(path);

Stream из случайных чисел Random
Random random = new Random();
random.ints();
random.longs();
random.doubles();

//////////////////////////////// ПРОМЕЖУТОЧНЫЕ ОПЕРАЦИИ ///////////////////////////////////////////
Промежуточные операции
Для перебора элементов потока применяется метод forEach(), который представляет терминальную операцию.
В качестве параметра он принимает объект Consumer<? super String>, который представляет действие,
выполняемое для каждого элемента набора. Например:

Stream<String> citiesStream = Stream.of("Париж", "Лондон", "Мадрид","Берлин", "Брюссель");
citiesStream.forEach(s -> System.out.println(s));
Фактически это будет аналогично перебору всех элементов в цикле for и выполнению с ними действия,
а именно вывод на консоль. В итоге консоль выведет:

Париж
Лондон
Мадрид
Берлин
Брюссель

Кстати мы можем сократить в данном случае применение метода forEach следующим образом:

Stream<String> citiesStream = Stream.of("Париж", "Лондон", "Мадрид","Берлин", "Брюссель");
citiesStream.forEach(System.out::println);

Фактически здесь переадается ссылка на статический метод, который выводит строку на консоль.

Фильтрация. Метод filter
Для фильтрации элементов в потоке применяется метод filter(), который представляет промежуточную операцию.
Он принимает в качестве параметра некоторое условие в виде объекта Predicate<T> и возвращает новый поток из элементов,
которые удовлетворяют этому условию:


Stream<String> citiesStream = Stream.of("Париж", "Лондон", "Мадрид","Берлин", "Брюссель");
citiesStream.filter(s -> s.length() == 6).forEach(s -> System.out.println(s));
Здесь условие s.length()==6 возвращает true для тех элементов, длина которых равна 6 символам.
То есть в итоге программа выведет:
Лондон
Мадрид
Берлин
Рассмотрим еще один пример фильтрации с более сложными данными. Допустим, у нас есть следующий класс Phone:

class Phone {

    private String name;
    private int price;

    public Phone(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

Отфильтруем набор телефонов по цене:

Stream<Phone> phoneStream = Stream.of(new Phone("iPhone 6 S", 54000), new Phone("Lumia 950", 45000),
                new Phone("Samsung Galaxy S 6", 40000));

phoneStream.filter(p -> p.getPrice() < 50000).forEach(p -> System.out.println(p.getName()));

Отображение. Метод map
Отображение или маппинг позволяет задать функцию преобразования одного объекта в другой,
то есть получить из элемента одного типа элемент другого типа.
Для отображения используется метод map, который имеет следующее определение:

<R> Stream<R> map(Function<? super T, ? extends R> mapper)

Передаваемая в метод map функция задает преобразование от объектов типа T к типу R.
И в результате возвращается новый поток с преобразованными объектами.

Возьмем вышеопределенный класс телефонов и выполним преобразование от типа Phone к типу String:

Stream<Phone> phoneStream = Stream.of(new Phone("iPhone 6 S", 54000), new Phone("Lumia 950", 45000),
                new Phone("Samsung Galaxy S 6", 40000));

phoneStream
    .map(p -> p.getName()) // помещаем в поток только названия телефонов
    .forEach(s -> System.out.println(s));
Операция map(p-> p.getName()) помещает в новый поток только названия телефонов. В итоге на консоли будут только названия:

iPhone 6 S
Lumia 950
Samsung Galaxy S 6

Еще проведем преобразования:

phoneStream
    .map(p -> "название: " + p.getName() + " цена: " + p.getPrice())
    .forEach(s -> System.out.println(s));

Здесь также результирующий поток содержит только строки, только теперь названия соединяются с ценами.

Для преобразования объектов в типы Integer, Long, Double определены специальные методы
mapToInt(), mapToLong() и mapToDouble() соответственно.

Плоское отображение. Метод flatMap

Плоское отображение выполняется тогда, когда из одного элемента нужно получить несколько.
Данную операцию выполняет метод flatMap:

<R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper)

Например, в примере выше мы выводим название телефона и его цену.
Но что, если мы хотим установить для каждого телефона цену со скидкой и цену без скидки.
То есть из одного объекта Phone нам надо получить два объекта с информацией, например, в виде строки. Для этого применим flatMap:

Stream<Phone> phoneStream = Stream.of(new Phone("iPhone 6 S", 54000), new Phone("Lumia 950", 45000),
                new Phone("Samsung Galaxy S 6", 40000));

phoneStream
    .flatMap(p -> Stream.of(
            String.format("название: %s  цена без скидки: %d", p.getName(), p.getPrice()),
            String.format("название: %s  цена со скидкой: %d", p.getName(), p.getPrice() - (int)(p.getPrice()*0.1))
    ))
    .forEach(s -> System.out.println(s));

Результат работы программы:

название: iPhone 6 S цена без скидки: 54000
название: iPhone 6 S цена со скидкой: 48600
название: Lumia 950 цена без скидки: 45000
название: Lumia 950 цена со скидкой: 40500
название: Samsung Galaxy S 6 цена без скидки: 40000
название: Samsung Galaxy S 6 цена со скидкой: 36000

И map и flatMap могут быть применены к стриму Stream<T> и оба возвращают стрим Stream<R>.
Разница заключается в том, что операция map создает одно выходное значение для каждого входного значения,
тогда как операция flatMap создает произвольное число(ноль или больше) значений для каждого входного значения.

////////////////// СОРТИРОВКА ЭЛЕМЕНТОВ /////////////////////////////////////////////////////
Сортировка в Stream API
Коллекции, на основе которых нередко создаются потоки, уже имеют специальные методы для сортировки содержимого.
Однако класс Stream также включает возможность сортировки.
Такую сортировку мы можем задействовать, когда у нас идет набор промежуточных операций с потоком,
которые создают новые наборы данных, и нам надо эти наборы отсортировать.

Для простой сортировки по возрастанию применяется метод sorted():

import java.util.Collections;
import java.util.List;

public class Program {
    public static void main(String[] args) {

        List<String> phones = new ArrayList<String>();
        Collections.addAll(phones, "iPhone X", "Nokia 9", "Huawei Nexus 6P",
                "Samsung Galaxy S8", "LG G6", "Xiaomi MI6",
                "ASUS Zenfone 3", "Sony Xperia Z5", "Meizu Pro 6",
                "Pixel 2");

        phones.stream()
                .filter(p->p.length()<12)
                .sorted() // сортировка по возрастанию
                .forEach(s->System.out.println(s));
    }
Консольный вывод после сортировки объектов:

LG G6
Meizu Pro 6
Nokia 9
Pixel 2
Xiaomi MI6
iPhone X

///////////////////////////////////// ОБЪЕДИНЕНИЕ ПОТОКОВ /////////////////////////////////////////////////////////////////
Объединение потоков
Ряд методов Stream API возвращают подпотоки или объединенные потоки на основе уже имеющихся потоков.
Рассмотрим эти методы.

1) takeWhile
Метод takeWhile() выбирает из потока элементы, пока они соответствуют условию.
Если попадается элемент, который не соответствует условию, то метод завершает свою работу.
Выбранные элементы возвращаются в виде потока.

import java.util.stream.Stream;

public class Program {
    public static void main(String[] args) {

        Stream<Integer> numbers = Stream.of(-3, -2, -1, 0, 1, 2, 3, -4, -5);
        numbers.takeWhile(n -> n < 0)
            .forEach(n -> System.out.println(n));
    }
}
В данном случае программа выбирает из потока числа, пока они меньше нуля. Консольный вывод программы:
-3
-2
-1
При этом несмотря на то, что в потоке больше отрицательных чисел, но метод завершает работу,
как только обнаружит первое число, которое не соответствует условию.
В этом и состоит отличие, например, от метода filter().

Чтобы в данном случае охватить все элементы, которые меньше нуля,
поток следует предварительно отсортировать:

Stream<Integer> numbers = Stream.of(-3, -2, -1, 0, 1, 2, 3, -4, -5);
numbers.sorted().takeWhile(n -> n < 0)
        .forEach(n -> System.out.println(n));
Консольный вывод программы:
-5
-4
-3
-2
-1

2) Сoncat
Статический метод concat() объединяет элементы двух потоков, возвращая объединенный поток:

import java.util.stream.Stream;

public class Program {
    public static void main(String[] args) {

        Stream<String> people1 = Stream.of("Tom", "Bob", "Sam");
        Stream<String> people2 = Stream.of("Alice", "Kate", "Sam");

        Stream.concat(people1, people2).forEach(n -> System.out.println(n));
    }
}
Консольный вывод:
Tom
Bob
Sam
Alice
Kate
Sam

//////////////////////////////////////////////// МЕТОДЫ ПРОПУСКА И ОГРАНИЧЕНИЙ /////////////////////////////////////////////
1) Метод skip(long n) используется для пропуска n элементов. Этот метод возвращает новый поток,
в котором пропущены первые n элементов.

2) Метод limit(long n) применяется для выборки первых n элементов потоков.
Этот метод также возвращает модифицированный поток, в котором не более n элементов.

Зачастую эта пара методов используется вместе для создания эффекта постраничной навигации.
Рассмотрим, как их применять:

Stream<String> phoneStream = Stream.of("iPhone 6 S", "Lumia 950", "Samsung Galaxy S 6", "LG G 4", "Nexus 7");

phoneStream.skip(1)
    .limit(2)
    .forEach(s->System.out.println(s));
В данном случае метод skip пропускает один первый элемент, а метод limit выбирает два следующих элемента.
В итоге мы получим следующий консольный вывод:
Lumia 950
Samsung Galaxy S 6

Вполне может быть, что метод skip может принимать в качестве параметра число большее, чем количество элементов в потоке.
В этом случае будут пропущены все элементы, а в результирующем потоке будет 0 элементов.
И если в метод limit передается число, большее, чем количество элементов, то просто выбираются все элементы потока.

Теперь рассмотрим, как создать постраничную навигацию:

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.*;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {

        List<String> phones = new ArrayList<String>();
        phones.addAll(Arrays.asList(new String[]
                {"iPhone 6 S", "Lumia 950", "Huawei Nexus 6P",
                "Samsung Galaxy S 6", "LG G 4", "Xiaomi MI 5",
                "ASUS Zenfone 2", "Sony Xperia Z5", "Meizu Pro 5",
                "Lenovo S 850"}));

        int pageSize = 3; // количество элементов на страницу
        Scanner scanner = new Scanner(System.in);
        while(true){

            System.out.println("Введите номер страницы: ");
            int page = scanner.nextInt();

            if(page<1) break; // если число меньше 1, выходим из цикла

            phones.stream().skip((page-1) * pageSize)
                .limit(pageSize)
                .forEach(s->System.out.println(s));
       }
    }
}
В данном случае у нас набор из 10 элементов. С помощью переменной pageSize определяем количество элементов на странице - 3.
То есть у нас получится 4 страницы (на последней будет только один элемент).
В бесконечном цикле получаем номер страницы и выбираем только те элементы, которые находятся на указанной странице.
Теперь введем какие-нибудь номера страниц, например, 4 и 2:

Введите номер страницы:
4
Lenovo S 850
Введите номер страницы:
2
Samsung Galaxy S 6
LG G 4
Xiaomi MI 5

////////////////////////////////////// ТЕРМИНАЛЬНЫЕ ОПЕРАЦИИ //////////////////////////////////////////////////////////////
Терминальные операции и коллекторы
Операции сведения представляют терминальные операции, которые возвращают некоторое значение - результат операции.
В Stream API есть ряд операций сведения.

1) count
Метод count() возвращает количество элементов в потоке данных:

import java.util.stream.Stream;
import java.util.Optional;
import java.util.*;
public class Program {

    public static void main(String[] args) {

        ArrayList<String> names = new ArrayList<String>();
        names.addAll(Arrays.asList(new String[]{"Tom", "Sam", "Bob", "Alice"}));
        System.out.println(names.stream().count());  // 4

        // количество элементов с длиной не больше 3 символов
        System.out.println(names.stream().filter(n->n.length()<=3).count());  // 3
    }
}
findFirst и findAny

2) Метод findFirst() извлекает из потока первый элемент, а findAny() извлекает случайный объект из потока (нередко так же первый):

ArrayList<String> names = new ArrayList<String>();
names.addAll(Arrays.asList(new String[]{"Tom", "Sam", "Bob", "Alice"}));

Optional<String> first = names.stream().findFirst();
System.out.println(first.get());    // Tom

Optional<String> any = names.stream().findAny();
System.out.println(first.get());    // Tom

3) allMatch, anyMatch, noneMatch
Еще одна группа операций сведения возвращает логическое значение true или false:

— boolean allMatch(Predicate<? super T> predicate): возвращает true, если все элементы потока удовлетворяют условию в предикате

— boolean anyMatch(Predicate<? super T> predicate): возвращает true, если хоть один элемент потока удовлетворяют условию в предикате

— boolean noneMatch(Predicate<? super T> predicate): возвращает true, если ни один из элементов в потоке не удовлетворяет условию в предикате

Пример использования функций:

import java.util.stream.Stream;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Arrays;
public class Program {

    public static void main(String[] args) {

        ArrayList<String> names = new ArrayList<String>();
        names.addAll(Arrays.asList(new String[]{"Tom", "Sam", "Bob", "Alice"}));

        // есть ли в потоке строка, длина которой больше 3
        boolean any = names.stream().anyMatch(s->s.length()>3);
        System.out.println(any);    // true

        // все ли строки имеют длину в 3 символа
        boolean all = names.stream().allMatch(s->s.length()==3);
        System.out.println(all);    // false

        // НЕТ ЛИ в потоке строки "Bill". Если нет, то true, если есть, то false
        boolean none = names.stream().noneMatch(s->s=="Bill");
        System.out.println(none);   // true
    }
}
3) min и max
Методы min() и max() возвращают соответственно минимальное и максимальное значение.
Поскольку данные в потоке могут представлять различные типы, в том числе сложные классы,
то в качестве параметра в эти методы передается объект интерфейса Comparator, который указывает, как сравнивать объекты:

Optional<T> min(Comparator<? super T> comparator)
Optional<T> max(Comparator<? super T> comparator)
Оба метода возвращают элемент потока (минимальный или максимальный), обернутый в объект Optional.

******************* a) Например, найдем минимальное и максимальное число в числовом потоке:
import java.util.stream.Stream;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Arrays;
public class Program {

    public static void main(String[] args) {

        ArrayList<Integer> numbers = new ArrayList<Integer>();
        numbers.addAll(Arrays.asList(new Integer[]{1,2,3,4,5,6,7,8,9}));

        Optional<Integer> min = numbers.stream().min(Integer::compare);
        Optional<Integer> max = numbers.stream().max(Integer::compare);
        System.out.println(min.get());  // 1
        System.out.println(max.get());  // 9
    }
}
Интерфейс Comparator - это функциональный интерфейс, который определяет один метод compare,
принимающий два сравниваемых объекта и возвращающий число (если первый объект больше,
возвращается положительное число, иначе возвращается отрицательное число).
Поэтому вместо конкретной реализации компаратора мы можем передать лямбда-выражение или метод,
который соответствует методу compare интерфейса Comparator.
Поскольку сравниваются числа, то в метод передается в качестве компаратора статический метод Integer.compare().
При этом методы min и max возвращают именно Optional,
и чтобы получить непосредственно результат операции из Optional, необходимо вызвать метод get().

***************** b) Рассмотрим более сложный случай, когда нам надо сравнивать более сложные объекты:
import java.util.stream.Stream;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Arrays;
public class Program {

    public static void main(String[] args) {

        ArrayList<Phone> phones = new ArrayList<Phone>();
        phones.addAll(Arrays.asList(new Phone[]{
            new Phone("iPhone 8", 52000),
            new Phone("Nokia 9", 35000),
            new Phone("Samsung Galaxy S9", 48000),
            new Phone("HTC U12", 36000)
        }));

        Phone min = phones.stream().min(Phone::compare).get();
        Phone max = phones.stream().max(Phone::compare).get();
        System.out.printf("MIN Name: %s Price: %d \n", min.getName(), min.getPrice());
        System.out.printf("MAX Name: %s Price: %d \n", max.getName(), max.getPrice());
    }
}
class Phone{

    private String name;
    private int price;

    public Phone(String name, int price){
        this.name=name;
        this.price=price;
    }
    public static int compare (Phone p1, Phone p2){
        if(p1.getPrice() > p2.getPrice())
            return 1;
        return -1;
    }
    public String getName() { return name; }
    public int getPrice() { return price;}
}

В данном случае мы находим минимальный и максимальный объект Phone:
фактически объекты с максимальной и минимальной ценой.
Для определения функциональности сравнения в классе Phone реализован статический метод compare,
который соответствует сигнатуре метода compare интерфейса Comparator.
И в методах min и max применяем этот статический метод для сравнения объектов.

Консольный вывод:

MIN Name: Nokia 9 Price: 35000
MAX Name: iPhone 8 Price: 52000
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.*;



/*
Stream API
Появился в Java 8

Это дженерик-интерфейс из пакет java.util.stream, параметризованный типом элементов из которых он состоит
Рядом с ним лежат IntStream, LongStream и DoubleStream - братья-близнецы для примитивных типов

Как выглядит?

package java.util.stream;

public interface Stream<T> extends BaseStream<T, Stream<T>> {
    // MANY methods
}
Stream представляет не просто средство обхода элементов(цикл foreach, итератор),
а средство описания алгоритма обработки и преобразования последовательности элементов

Если сравнивать стримы с коллекциями, то коллекции предполагают хранение всего набора элементов,
и они, соответственно, конечны.
А стрим потенциально может быть бесконечным
Коллекция предоставляет индивидуальный доступ к своим элементам по индексу или ключу, а стрим такого не позволяет
Коллекции можно менять (добавлять или удалять элементы, в том числе через итератор, а применение трансформации к стриму
никак не влияет на тот источник, из которого элементы берутся
 */
public class ex33_1StreamAPI {
    public static void main(String[] args) throws IOException {
        int sum = IntStream.iterate(1, n -> n + 1) // метод iterate принимает первый элемент последовательности и вычисляет следующий по формуле, возвращается стрим
                .filter(n -> n % 5 == 0 && n % 2 != 0) // фильтруем стрим и оставляем только те числа, которые делятся на 5 и не делятся на 2
                .limit(10) // из бесконечной последовательности берем только первые 10 элементов
                .map(n -> n * n) // и возводим каждый из них в квадрат
                .sum(); // и наконец суммируем все элементы и получаем число результат
        System.out.println(sum);
        // последняя операция - суммирование является спусковым крючком, чтобы стрим начал выполнять все те действия,
        // которые мы ему передали на предыдущих шагах
        // при этом информация о том, что нам нужны только первые 10 элементов долшла до генератора чисел - он будет работать не бесконечно,
        // а только пока не наберет нужное количество удовлетворяющих предикату элементов

        // Использование стримов состоит из 3 частей
        // 1) получение стрима
        // 2) 0 или более промежуточных операций преобразования - стрим их просто запоминает, но пока не выполняет
        // 3) единственная терминальная операция, запускающая весь процесс вычисления,
        // которая должна стать его полезным результатом
        // 4) и есть еще опциональный заключительный шаг - вызов его метода .close()
        // этот шаг обязателен только тогда, когда стрим выделял какие-то системные ресурсы - в данном примере нет
        // а вот если стрим связан с содержимым файла или директории на диске, то закрыть его надо во избежание утечек ресурсов
        // благодря тому, что стрим реализует интрефейс AutoClosable, его можно использовать в блоке try-with-resources



        //////////////////////////////// Разберем этапы работы со стримом ////////////////////////

        ///////////////////////// 1 СОЗДАНИЕ СТРИМА ///////////////////////////////
        // 1) стрим можно получить из любой коллекции методом stream()
        Set<String> vocabulary = new HashSet<>(List.of("one", "two", "three"));
        Stream<String> stream1 = vocabulary.stream();

        // 2) стрим можно получить из потока BufferedReader (символьный поток) методом .lines()
        BufferedReader reader = new BufferedReader(new FileReader("C:\\JavaEx\\filePoem.txt"));
        Stream<String> stream2 = reader.lines(); // метод вернет поток строчек из данного потока символов

        // 3) можно получить стрим из директории на диске при помощи методов .list() и .walk()
        Path path = Path.of("C:\\JavaEx");
        Stream<String> stream10 = Files.lines(path); // вернет содержимое файла построчно
        Stream<Path> stream3 = Files.list(path); // вернет содержимое директории на один уровень
        Stream<Path> stream4 = Files.walk(path); // рекурсивно обойдет и поддиректории тоже
        // стримы 2) и 3) нужно закрывать!!! в блоке try-with-resources

        // 4) стрим можно получить из строчки вызовом метода .chars()
        // этот стрим будет содержать символы строки
        // но так как отдельного чар-стрим в библиотеке нет, мы получим IntStream
        IntStream chars = "hello".chars();

        //-----------------------------------------------------------------------

        // Стримы можно порождать динамически
        // 5) Генерировать при помощи некоторого supplier-а - поставщика (в данном примере random)
        // Supplier - это интерфейс с единственным методом get
        // и этот метод get должен последовательно, один за другим, возвращать элементы последовательности
        DoubleStream randomNumbers = DoubleStream.generate(Math::random);

        double a = Math.random(); // случайные числа [0;1)
        int b = (int)(Math.random()*101); // генераци случайных чисел [0;100]

        // 6) Стрим можно получить итерированием какой-то функции - n -> n + 1 в данном примере
        // такой пример мы уже рассмотрели
        IntStream integers = IntStream.iterate(0, n -> n + 1);

        // 7) Если речь идет о целых числах, то можно получить диапазон чисел в виде стрима
        // тут есть два метода
        IntStream smallIntegers = IntStream.range(0, 100); // 0-99
        // первый из них возвращает стрим из чисел от начала до конца диапазона - не включительно
        IntStream smallInegers2 = IntStream.rangeClosed(0, 100); // 0-100
        // а второй - от начала до конца диапазона включительно

        //-----------------------------------------------------------------------------------

        // 8) Стрим можно получить конкатенацией двух других стримов
        IntStream combinedStream = IntStream.concat(smallIntegers, smallInegers2);

        // 9) Или взять пустой стрим
        IntStream empty = IntStream.empty();

        // 10) можно получить стрим из массива
        double[] array = {0.2, 0.55, 10.245};
        DoubleStream streamFromArray = Arrays.stream(array);

        // 11) или просто взять и перечислить все элементы стрима явно
        IntStream streamOfElements = IntStream.of(2, 4, 6, 8, 10);

        //////////// КРАТКО //////////////////////////////////////////

//        1) collection.stream() или list.stream() или arrayList.Stream()
//        map.entrySet().stream()
//        2) Stream.of("1", "2", "3")
//        3) Arrays.stream(array) или Stream.of(array)
//        4) Files.lines(путь\к\файлу)
//
//        5) «строка».chars()
//        6) Stream.builder().add(...)....build()
//        7) collection.parallelStream()
//
//        8) BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        bufferedReader.lines();
//
//        9) Stream.iterate(начальное_условие, выражение_генерации)
//        10) Stream.generate(выражение_генерации
//        11) Stream.empty()
//
//        12) Stream.concat(stream1, stream2); // объединяет два потока в один
//        13) IntStream.range(1, 10); // поток диапозона чисел от 1 до 9
//        IntStream.rangeClosed(1, 10); // поток диапозона чисел от 1 до 10


//
//        ///////////////////// MAP AND FLATMAP /////////////////////////////////////
//        1, 2, 3, 4 , 5
//        List<Integer> mappedList = originalList.stream()
//                .map(x -> x * 2)
//                .collect(Collectors.toList());
//        Алексей Волков22:41
//        2, 4, 6, 8, 10
//        List<Integer> flatMappedList = originalList.stream()
//                .flatMap(x -> Arrays.asList(x, x * 2).stream())
//                .collect(Collectors.toList());
//        1, 2, 2, 4, 3, 6, 4, 8, 5, 10



        ///////////////////// 2 ПРОМЕЖУТОЧНЫЕ ОПЕРАЦИИ ////////////////////////////
        IntStream streamNew = IntStream.of(1, 2, 5, 10, 77, 35, 105, 244, 555, 1024, 13543, 234, 555, 123, 664, 243, 1324);
        streamNew.filter(n -> n > 100)  // операция фильтрации принимает предикат - проверяем, что число больше 100
                .mapToObj(Integer::toString)  // принимает функцию, которая из каждого эл-та стрима делает какой-то новый элемент потенциально другого типа
                .flatMapToInt(s -> s.chars()) // похожа на map, но принимает функцию, возвращающую стрим из каждого элемента, а затем их конкатенирует
                .distinct()  // убирает из стрима дубликаты
                .sorted() // сортирует элементы стрима по возрастанию, если стрим не примативных типов, а объектов, то туда можно передать Comparator
                .skip(3) // позволяет пропустить некоторое количество элементов - первые 3
                .peek(System.out::println) // подглядим, что получилось на этом шаге
                .limit(2); // ограничивает оставшиеся элементы заданным количеством

        // Можно в качестве промежуточной операции встроить .peek(Consumer), принимающий Consumer
        // Она позволяет подсмотреть, какие элементы обитают на разных этапах обработки стрима
        // Для отладки в качестве Consumer удобно передавать System.out::println

        // 2.1 asIntStream, asLongStream, asDoubleStream
        // IntStream можно преобразовать в LongStream, либо в DoubleStream
        // LongStream только в DoubleStream
        // DoubleStream назад не преобразовывается
        DoubleStream doubleStream = IntStream.rangeClosed(1, 100)
                .asLongStream()
                .asDoubleStream();

        // 2.2 toArray
        // Все примитивные стримы можно преобразовать в массив примитивов
        int[] ints = IntStream.of(1, 2).toArray(); // [1, 2]
        long[] longs = LongStream.of(3, 4).toArray(); // [3, 4]
        double[] doubles = DoubleStream.of(5, 6).toArray(); // [5.0, 6.0]



        ///////////////////////// 3 ТЕРМИНАЛЬНЫЕ ОПЕРАЦИИ ///////////////////////////////////////
        // Терминальная операция запускает стрим на исполнение

        // 1) Терминальная операция for each - она принимает Consumer (println в данном случае),
        // которому будут переданы все элементы, которые в cтриме остались
        // в данном случае они будут выведены в консоль
        IntStream stream31 = IntStream.range(0, 200);
        stream31.forEach(System.out::println);

        // 2) Терминальная операция findFirst возвращает первый в порядке следования элемент из стрима
        // Возвращается OptionalInt, потому что стрим может быть пустой и тогда в нем никакого первого элемента нет
        IntStream stream32 = IntStream.iterate(0, n -> n + 2);
        OptionalInt result = stream32.findFirst();
        // Аналогичная операция findAny() не гарантирует, что вернется первый элемент, вернется тот элемент, который стриму удобнее
        IntStream thisStream = IntStream.of(1, 3, 3, 5, 5);
        OptionalInt anyResult = thisStream.findAny();

        // 3) Терминальная операция allMatch позволяет проверить, что все элементы стрима удовлетворяют переданному условию-Predicate
        // возвращается булевское значение, если действительно этот предикат выполняется для всех элементов
        Stream<String> stream33 = Stream.of("one", "two", "three", "four", "five");
        boolean allStringsAreAtLeast10Chars = stream33.allMatch(s -> s.length() > 10);
        // есть аналогичные методы .anyMatch() для проверки того, что хотя бы один из элементов стрима удовлетворяет условию
        // и noneMatch() для проверки того, что ни один из элементов не удовлетворяет предикату

        // 4) Терминальная операция min возвращает минимальный элемент из стрима
        // Аналогично в виде Optional потому, что стрим может быть пустой
        Stream<String> stream34 = Stream.of("this", "that", "these", "those");
        Optional<String> minString = stream34.min(Comparator.comparing(String::length, Integer::compare));
        // есть также терминальная операция max, возвращающая максимум
        // min и max в случае стрима-объекта принимают Comparator - правила, по которым определяется минимум и максимум

        // 5) Терминальная операция  count просто возвращает количество элементов, оставшихся в стриме
        // после выполнения всех операций трансформации и фильтрации
        IntStream stream35 = IntStream.of(1,2,4,5,6,7);
        int count = (int) stream35.count();

        // 6) Терминальная операция sum, доступная в стримах примитивов, вернет обычную арифметическую сумму элементов
        IntStream streamAnother = IntStream.of(5, 2, 5, 2, 5);
        int total = streamAnother.sum();

        // 7) Терминальная операция collect позволяет собрать элементы стрима в какое-то новое хранилище (список, например)
        Stream<String> stream36 = Stream.of("I", "he", "she", "it", "we", "you", "they");
        List<String> list = stream36.collect(Collectors.toList());
        // тип этого хранилища опеределяется параметром, который передается в метод collect
        // это некий Collector и он может собирать элементы стрима во что угодно, не только в коллекции
        // многие стандартные коллекторы есть в классе Collectors

        // 8) Терминальная операция reduce позволяет вычислить свертку элементов стрима
        // то есть результат применения некоего бинарного оператора к каждой паре элементов стрима
        // пока от стрима не останется один единственный элемент
        Stream<BigInteger> bigInts = Stream.of(BigInteger.valueOf(124324324), BigInteger.valueOf(432434321), BigInteger.valueOf(324344354));
        BigInteger bigIntegerSum = bigInts.reduce(BigInteger.ZERO, BigInteger::add);
        // это и есть результат свертки и он возвращается в качестве результата из метода reduce
        // если стрим был пуст, то возвращается некоторое нулевое значение

        // Важно, что вызвать на стриме терминальную операцию можно только один раз
        // После вызова терминальной операции стрим считается больше непригодным к использованию
        // и этот объект остается только выбросить
        // Если к тем же элементам нужно применить еще какое-то преобразование, посчитать по ним какое-то другое значение,
        // то надо заново сконструировать стрим, снова настроить его
        // и запустить какую-то терминальную операцию

        ///////////////// КАК НАПИСАТЬ ПРИ ПОМОЩИ СТРИМОВ ИЗВЕСТНЫЕ АЛГОРИТМЫ /////////////////////////////////////
        System.out.println(factorial(7));

        Random random = new Random();
        IntStream myRandom = random.ints();
        DoubleStream myDouble = random.doubles();
        LongStream myLong = random.longs();
        System.out.println(Arrays.toString(myRandom.limit(15).toArray()));

    }
    // Первый пример - найти факториал числа
    public static BigInteger factorial(int n) {
        return IntStream.rangeClosed(1, n)  // здесь мы можем породить интервал целых чисел от 1 до n в виде стрима
                .mapToObj(i -> BigInteger.valueOf(i)) // затем каждое из этих чисел превратить в BigInteger
                .reduce(BigInteger.ONE, BigInteger::multiply); // а затем вычислить свертку при помощи операции умножения
        // результат будет тем же самым факториалом, который мы вычисляли в одном из домашних заданий
    }
    // Второй пример - определить, является ли строка палиндромом
    public static boolean isPalindrome(String s) {
        StringBuilder leftToRight = new StringBuilder();
        // Основная сложность здесь - отфильтровать символы строки, оставить только буквы и цифры
        // И проверять уже палиндромность резултирующей строки
        s.chars().filter(Character::isLetterOrDigit)  // получаем стрим символов из нашей строки, отфильтровываем только буквы или символы
                .map(Character::toLowerCase)  // приводим каждый из них в нижний регистр
                .forEach(leftToRight::appendCodePoint); // и сохраняем результат в StringBuilder
        StringBuilder rightToLeft = new StringBuilder(leftToRight).reverse(); // а дальше остается реверсировать
        return leftToRight.toString()
                .equals(rightToLeft.toString()); // и сравнить две строки
    }

}
