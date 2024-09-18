import java.util.ArrayList;
import java.util.List;

public class ex30_1video1 {
    public static void main(String[] args) {
        List<String> list;
        // это объект list класса List
        // параметризованный дженериком <String> - типом данных, с которыми он может работать (хранить)

//        List ages = new ArrayList();  // до появления дженериков обычный лист вначале был - мы создали список возрастов студентов
//        ages.add(20);  // в него спокойно добавляли значения типа int, String вперемешку - кто-то поработал с нашим списком и нечаянно дописал в него имя студента
//        ages.add("Vasya");
//        for (int i = 0; i < ages.size(); i++) {
//            int j = (Integer) ages.get(i);
//            System.out.println(j); // до 5 Java (появления дженериков) код был такой - в него запихивали данные и надеялись, что все они будут типа Integer
//            // но любой неудачный джуниор мог запихнуть туда String и происходило исключение ClassCastException и все сваливалось
//        }

        List<Integer> ages = new ArrayList<>(); // И вот в Java 5 появились дженерики
//        ages.add("Vasya"); // этот код уже не скомпилируется - будет компилятор подчеркивать красным и требовать исправить ошибку
        ages.add(20);
        ages.add(21);
        for (int i = 0; i < ages.size(); i++) {
            int j = (Integer) ages.get(i);
            System.out.println(j);  // все идеально работает
        }

    }
}

