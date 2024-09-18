/*
Интерфейс Map<K, V> представляет отображение или иначе говоря словарь, где каждый элемент представляет пару "ключ-значение".
При этом все ключи уникальные в рамках объекта Map. Такие коллекции облегчают поиск элемента, если нам известен ключ - уникальный идентификатор объекта.

Следует отметить, что в отличие от других интерфейсов, которые представляют коллекции, интерфейс Map НЕ расширяет интерфейс Collection.

Среди методов интерфейса Map можно выделить следующие:
void clear(): очищает коллекцию
boolean containsKey(Object k): возвращает true, если коллекция содержит ключ k
boolean containsValue(Object v): возвращает true, если коллекция содержит значение v
Set<Map.Entry<K, V>> entrySet(): возвращает набор элементов коллекции. Все элементы представляют объект Map.Entry
boolean equals(Object obj): возвращает true, если коллекция идентична коллекции, передаваемой через параметр obj
boolean isEmpty: возвращает true, если коллекция пуста
V get(Object k): возвращает значение объекта, ключ которого равен k. Если такого элемента не окажется, то возвращается значение null
V getOrDefault(Object k, V defaultValue): возвращает значение объекта, ключ которого равен k. Если такого элемента не окажется, то возвращается значение defaultVlue
V put(K k, V v): помещает в коллекцию новый объект с ключом k и значением v. Если в коллекции уже есть объект с подобным ключом, то он перезаписывается. После добавления возвращает предыдущее значение для ключа k, если он уже был в коллекции. Если же ключа еще не было в коллекции, то возвращается значение null
V putIfAbsent(K k, V v): помещает в коллекцию новый объект с ключом k и значением v, если в коллекции еще нет элемента с подобным ключом.
Set<K> keySet(): возвращает набор всех ключей отображения
Collection<V> values(): возвращает набор всех значений отображения
void putAll(Map<? extends K, ? extends V> map): добавляет в коллекцию все объекты из отображения map
V remove(Object k): удаляет объект с ключом k
int size(): возвращает количество элементов коллекции

Чтобы положить объект в коллекцию, используется метод put, а чтобы получить по ключу - метод get.
Реализация интерфейса Map также позволяет получить наборы как ключей, так и значений.
А метод entrySet() возвращает набор всех элементов в виде объектов Map.Entry<K, V>.

Обобщенный интерфейс Map.Entry<K, V> представляет объект с ключом типа K и значением типа V и определяет следующие методы:
boolean equals(Object obj): возвращает true, если объект obj, представляющий интерфейс Map.Entry, идентичен текущему
K getKey(): возвращает ключ объекта отображения
V getValue(): возвращает значение объекта отображения
V setValue(V v): устанавливает для текущего объекта значение v
int hashCode(): возвращает хеш-код данного объекта

При переборе объектов отображения мы будем оперировать этими методами для работы с ключами и значениями объектов.

Классы отображений. HashMap
Базовым классом для всех отображений является абстрактный класс AbstractMap, который реализует большую часть методов интерфейса Map.
Наиболее распространенным классом отображений является HashMap, который реализует интерфейс Map и наследуется от класса AbstractMap.

Пример использования класса:
Чтобы добавить или заменить элемент, используется метод put, либо replace,
а чтобы получить его значение по ключу - метод get.
С помощью других методов интерфейса Map также производятся другие манипуляции над элементами: перебор, получение ключей, значений, удаление.
 */
/*
КЛАСС HashMap содержит класс Node - узел, в нем хранятся хэш-код объекта, ключ, значение, ссылка на следующий объект
static class Node<K,V> implements Map.Entry<K,V> {
        final int hash;
        final K key;
        V value;
        Node<K,V> next;

        Node(int hash, K key, V value, Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final K getKey()        { return key; }
        public final V getValue()      { return value; }
        public final String toString() { return key + "=" + value; }

        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public final boolean equals(Object o) {
            if (o == this)
                return true;

            return o instanceof Map.Entry<?, ?> e
                    && Objects.equals(key, e.getKey())
                    && Objects.equals(value, e.getValue());
        }
    }
 */
import java.util.*;

public class ex31_7HashMap {
    public static void main(String[] args) {

        Map<Integer, String> states = new HashMap<Integer, String>();
        states.put(1, "Germany");
        states.put(2, "Spain");
        states.put(4, "France");
        states.put(3, "Italy");

        // получим объект по ключу 2
        String first = states.get(2);
        System.out.println(first);
        // получим весь набор ключей
        Set<Integer> keys = states.keySet();
        // получить набор всех значений
        Collection<String> values = states.values();
        //заменить элемент
        states.replace(1, "Poland");
        // удаление элемента по ключу 2
        states.remove(2);
        // перебор элементов
        for(Map.Entry<Integer, String> item : states.entrySet()){

            System.out.printf("Key: %d  Value: %s \n", item.getKey(), item.getValue());
        }

        Map<String, Person_7> people = new HashMap<String, Person_7>();
        people.put("1240i54", new Person_7("Tom"));
        people.put("1564i55", new Person_7("Bill"));
        people.put("4540i56", new Person_7("Nick"));

        for(Map.Entry<String, Person_7> item : people.entrySet()){

            System.out.printf("Key: %s  Value: %s \n", item.getKey(), item.getValue().getName());
        }

        // Как вывести HashMap на печать - упрощенный вариант
        Map<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        System.out.println(map); // => {one=1, two=2, three=3}

        Map<Integer, String> map1 = new HashMap<>();
        map1.put(1000, "Zaur Tregulov");
        map1.put(3568, "Ivan Ivanov");
        map1.put(6578, "Mariya Sidorova");
        map1.put(15879, "Nikolay Petrov");
        map1.putIfAbsent(1000, "Oleg Ivanov");
        System.out.println(map1);
        System.out.println(map1.get(1000));
        System.out.println(map1.get(1001));
        map1.remove(15789);
        System.out.println(map1);
        System.out.println(map1.containsValue("Nikolay Petrov"));
        System.out.println(map1.containsValue("Ivan Ivanov"));
        System.out.println(map1.containsKey(1000));
        System.out.println(map1.containsKey(15879));
        System.out.println(map1.containsKey(500));
        System.out.println(map1.keySet());
        System.out.println(map1.values());

        Map<String, String> map2 = new HashMap<>();
        map2.put("Sasha", "Dobriy");
        map2.put("Misha", "Unmiy");




    }
}
class Person_7{

    private String name;
    public Person_7(String value){
        name=value;
    }
    String getName(){return name;}
}
/*
Методы коллекций Map
Интерфейс Map<K, V> представляет отображение или иначе говоря словарь, где каждый элемент представляет пару "ключ-значение".
При этом все ключи уникальные в рамках объекта Map. Такие коллекции облегчают поиск элемента, если нам известен ключ - уникальный идентификатор объекта.

Следует отметить, что в отличие от других интерфейсов, которые представляют коллекции, интерфейс Map НЕ расширяет интерфейс Collection.

Среди методов интерфейса Map можно выделить следующие:
— void clear(): очищает коллекцию
— boolean containsKey(Object k): возвращает true, если коллекция содержит ключ k
— boolean containsValue(Object v): возвращает true, если коллекция содержит значение v
— Set<Map.Entry<K, V>> entrySet(): возвращает набор элементов коллекции. Все элементы представляют объект Map.Entry
— boolean equals(Object obj): возвращает true, если коллекция идентична коллекции, передаваемой через параметр obj
— boolean isEmpty: возвращает true, если коллекция пуста
— V get(Object k): возвращает значение объекта, ключ которого равен k. Если такого элемента не окажется, то возвращается значение null
— V getOrDefault(Object k, V defaultValue): возвращает значение объекта, ключ которого равен k. Если такого элемента не окажется, то возвращается значение defaultVlue
— V put(K k, V v): помещает в коллекцию новый объект с ключом k и значением v. Если в коллекции уже есть объект с подобным ключом, то он перезаписывается. После добавления возвращает предыдущее значение для ключа k, если он уже был в коллекции. Если же ключа еще не было в коллекции, то возвращается значение null
— V putIfAbsent(K k, V v): помещает в коллекцию новый объект с ключом k и значением v, если в коллекции еще нет элемента с подобным ключом.
— Set<K> keySet(): возвращает набор всех ключей отображения
— Collection<V> values(): возвращает набор всех значений отображения
— void putAll(Map<? extends K, ? extends V> map): добавляет в коллекцию все объекты из отображения map
— V remove(Object k): удаляет объект с ключом k
— int size(): возвращает количество элементов коллекции

Чтобы положить объект в коллекцию, используется метод put, а чтобы получить по ключу - метод get.
Реализация интерфейса Map также позволяет получить наборы как ключей, так и значений.
А метод entrySet() возвращает набор всех элементов в виде объектов Map.Entry<K, V>.

Обобщенный интерфейс Map.Entry<K, V> представляет объект с ключом типа K и значением типа V и определяет следующие методы:
— boolean equals(Object obj): возвращает true, если объект obj, представляющий интерфейс Map.Entry, идентичен текущему
— K getKey(): возвращает ключ объекта отображения
— V getValue(): возвращает значение объекта отображения
— V setValue(V v): устанавливает для текущего объекта значение v
— int hashCode(): возвращает хеш-код данного объекта

При переборе объектов отображения мы будем оперировать этими методами для работы с ключами и значениями объектов.

HashMap — основан на хэш-таблицах, реализует интерфейс Map (что подразумевает хранение данных в виде пар ключ/значение).
Ключи и значения могут быть любых типов, в том числе и null.
Данная реализация не дает гарантий относительно порядка элементов с течением времени.

Создание объекта
Map<String, String> hashmap = new HashMap<String, String>();
Новоявленный объект hashmap, содержит ряд свойств:
— table — Массив типа Node[], который является хранилищем ссылок на списки (цепочки) значений; Node в свою очередь реализует интерфейс Map.Entry. **Каждый элемент массива в дальнейшем будет называться корзиной или бакетом, потому не удивляйся
— loadFactor — Коэффициент загрузки. Значение по умолчанию 0.75 является хорошим компромиссом между временем доступа и объемом хранимых данных;
— threshold — Предельное количество элементов, при достижении которого, размер хэш-таблицы увеличивается вдвое. Рассчитывается по формуле (capacity * loadFactor);
— size — Количество элементов HashMap-а;

В конструкторе, выполняется проверка валидности переданных параметров и установка значений в соответствующие свойства класса.
Словом, ничего необычного. Обратите внимание, массив бакетов (table) создается только при первой попытке добавления пары ключ/значения в мапу.

При создании объекта hashMap из другого объекта map, либо при вызове метода put, в HashMap вызывается метод putVal,
который, в свою очередь, вызывает метод resize, который и инициализирует первый раз масси с бакетами,
и также обновляет его размеры при достижении текущих пределов.

final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
              boolean evict) {
   Node<K,V>[] tab; Node<K,V> p; int n, i;
   if ((tab = table) == null || (n = tab.length) == 0)
       n = (tab = resize()).length; //вызов метода resize
   ...
}

final Node<K,V>[] resize() {
   Node<K,V>[] oldTab = table;
   int oldCap = (oldTab == null) ? 0 : oldTab.length;
   int oldThr = threshold;
   int newCap, newThr = 0;
   if (oldCap > 0) {
       ... //
   }
   else if (oldThr > 0) // initial capacity was placed in threshold
       newCap = oldThr;
   else {               // zero initial threshold signifies using defaults
       newCap = DEFAULT_INITIAL_CAPACITY;
       newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
   }
   ...
   Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
   table = newTab;
   if (oldTab != null) {
       ...
   }
   return newTab;
}


Вы можете указать свои емкость и коэффициент загрузки, используя конструкторы HashMap(capacity) и HashMap(capacity, loadFactor).
Максимальная емкость, которую вы сможете установить, равна половине максимального значения int (1073741824).

Добавление элементов
hashmap.put("0", "zero");
При добавлении элемента, последовательность шагов следующая:

Сначала вычисляется hashCode ключа с помощью метода hash

static final int hash(Object key) {
   int h;
   return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
}
(заметьте, для ключа null хэшкод будет приниматься равным 0)
Далее вызывается метод putVal, в котором при необходимости создается/обновляется корзина, затем вычисляется нужный индекс корзины:

i = (n - 1) & hash
Затем, зная индекс в массиве произодится проверка наличия по этому индексу элементов. Если их нет, то просто создается новый объект Node, куда складываются хэшкод ключа, ключ, значение и null в качестве ссылки на следующий элемент, т.к пока это первый элемент в корзине:

if ((p = tab[i = (n - 1) & hash]) == null)
    tab[i] = newNode(hash, key, value, null);
Если же данная корзина не пуста, то получив ссылку на первый элемент в корзине программа пошагово пытается найти Node с таким же ключом, при чем сперва сравниваются hashCode ключей (который хранятся отдельным полем в Node), и только при их совпадении ключи проверяются на равенство (т.к. сравнение объектов через equals операция в общем случае намного более тяжеловесна, нежели сравнение двух int). Вот почему важно соблюдение контракта equals и hashCode, а так же корректная реализация hashCode.


Node<K,V> e; K k;

if (p.hash == hash &&
      ((k = p.key) == key || (key != null && key.equals(k)))) //первый элемент в цепочки имеет такой же ключ
    e = p;

    ...

else { //если Node с представленным ключом не найдена, то перебираем элементы
    for (int binCount = 0; ; ++binCount) {
        if ((e = p.next) == null) {
            p.next = newNode(hash, key, value, null); //создание новой Node в конце цепочи

            ...

            break;
        }

    if (e.hash == hash &&
          ((k = e.key) == key || (key != null && key.equals(k)))) //в цепочке найдена нода с идентичным ключом
        break;
    p = e;
}
Теперь, если в корзине была найдена Node с представленным ключом, то в этой Node происходит замена значения на новое

if (e != null) { // existing mapping for key
    V oldValue = e.value;

    if (!onlyIfAbsent || oldValue == null)
        e.value = value;

    afterNodeAccess(e);

    return oldValue;
}
Флаг onlyIfAbsent как раз и регулирует то, нужно ли заменять существующее значение, или нет (например метод putIfAbsent не заменяет существующие значения, в то время как метод put - заменяет).

Получение значение по ключу

hashmap.get("0");
При получении элемента по ключу, в hashMap вызывается метод get, который в свою очередь вызывает метод getNode:

public V get(Object key) {
    Node<K,V> e;
    return (e = getNode(key)) == null ? null : e.value;
}

final Node<K,V> getNode(Object key) {
    Node<K,V>[] tab;
    Node<K,V> first, e;
    int n, hash;
    K k;

    if ((tab = table) != null && (n = tab.length) > 0 &&
        (first = tab[(n - 1) & (hash = hash(key))]) != null) {//проверка, что в вычисленной корзине есть элементы
        if (first.hash == hash && // первая Node всегда проверяется отдельно
            ((k = first.key) == key || (key != null && key.equals(k))))
            return first;

        if ((e = first.next) != null) {
            ...
            do {
                if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k)))) //проверка ключа
                    return e;
            } while ((e = e.next) != null); //проверка ключей каждого элемента в списке Node, пока не будет найден нужный или не дойдем до конца списка
        }
    }
    return null;
}
 */
