/*
Урок с кодом
Задача:
В этой задаче вам предстоит самостоятельно написать набор классов таким образом,
чтобы данный код успешно компилировался и выполнялся.

Вам предстоит использовать новые знания про generics, коллекции и Stream API.

В приведенном коде используется оператор assert. Этот оператор используется для того,
чтобы проверять определенные инварианты в коде. С помощью него возможно писать небольшие тесты
и следить за корректностью своей программы (в обычной ситуации предпочтительно для этих целей
использовать библиотеки для модульного тестирования, которые выходят за рамки базового курса).

Оператор выглядит следующим образом:
assert предикат: сообщение;
Предикат – выражение с типом boolean.
Если выражение является ложным, то в программе возникает исключение AssertionError с соответствующим сообщением.
По-умолчанию данная функциональность отключена.
Чтобы ее включить, необходимо передать специальный ключ -ea в параметры виртуальной машины.
Сделать это можно прямо при запуске в консоли с помощью программы java,
либо указав этот ключ в настройках запуска программы в вашей IDE.
В случае IntellijIDEA, например, эта опция указывается в поле Run -> Edit Configurations... -> конфигурация запуска вашей программы -> VM Options.

Код, который необходимо заставить успешно работать:

// Random variables
String randomFrom = "..."; // Некоторая случайная строка. Можете выбрать ее самостоятельно.
String randomTo = "...";  // Некоторая случайная строка. Можете выбрать ее самостоятельно.
int randomSalary = 100;  // Некоторое случайное целое положительное число. Можете выбрать его самостоятельно.

// Создание списка из трех почтовых сообщений.
MailMessage firstMessage = new MailMessage(
        "Robert Howard",
        "H.P. Lovecraft",
        "This \"The Shadow over Innsmouth\" story is real masterpiece, Howard!"
);

assert firstMessage.getFrom().equals("Robert Howard"): "Wrong firstMessage from address";
assert firstMessage.getTo().equals("H.P. Lovecraft"): "Wrong firstMessage to address";
assert firstMessage.getContent().endsWith("Howard!"): "Wrong firstMessage content ending";

MailMessage secondMessage = new MailMessage(
        "Jonathan Nolan",
        "Christopher Nolan",
        "Брат, почему все так хвалят только тебя, когда практически все сценарии написал я. Так не честно!"
);

MailMessage thirdMessage = new MailMessage(
        "Stephen Hawking",
        "Christopher Nolan",
        "Я так и не понял Интерстеллар."
);

List<MailMessage> messages = Arrays.asList(
        firstMessage, secondMessage, thirdMessage
);

// Создание почтового сервиса.
MailService<String> mailService = new MailService<>();

// Обработка списка писем почтовым сервисом
messages.stream().forEachOrdered(mailService);

// Получение и проверка словаря "почтового ящика",
//   где по получателю можно получить список сообщений, которые были ему отправлены
Map<String, List<String>> mailBox = mailService.getMailBox();

assert mailBox.get("H.P. Lovecraft").equals(
        Arrays.asList(
                "This \"The Shadow over Innsmouth\" story is real masterpiece, Howard!"
        )
): "wrong mailService mailbox content (1)";

assert mailBox.get("Christopher Nolan").equals(
        Arrays.asList(
                "Брат, почему все так хвалят только тебя, когда практически все сценарии написал я. Так не честно!",
                "Я так и не понял Интерстеллар."
        )
): "wrong mailService mailbox content (2)";

assert mailBox.get(randomTo).equals(Collections.<String>emptyList()): "wrong mailService mailbox content (3)";


// Создание списка из трех зарплат.
Salary salary1 = new Salary("Facebook", "Mark Zuckerberg", 1);
Salary salary2 = new Salary("FC Barcelona", "Lionel Messi", Integer.MAX_VALUE);
Salary salary3 = new Salary(randomFrom, randomTo, randomSalary);

// Создание почтового сервиса, обрабатывающего зарплаты.
MailService<Integer> salaryService = new MailService<>();

// Обработка списка зарплат почтовым сервисом
Arrays.asList(salary1, salary2, salary3).forEach(salaryService);

// Получение и проверка словаря "почтового ящика",
//   где по получателю можно получить список зарплат, которые были ему отправлены.
Map<String, List<Integer>> salaries = salaryService.getMailBox();
assert salaries.get(salary1.getTo()).equals(Arrays.asList(1)): "wrong salaries mailbox content (1)";
assert salaries.get(salary2.getTo()).equals(Arrays.asList(Integer.MAX_VALUE)): "wrong salaries mailbox content (2)";
assert salaries.get(randomTo).equals(Arrays.asList(randomSalary)): "wrong salaries mailbox content (3)";


В конечном итоге, вам нужно реализовать классы MailService, MailMessage и Salary (и, вероятно, вспомогательные классы и интерфейсы)
и отправить их код в форму. Все классы должны быть публичными и статическими (ваши классы подставятся во внешний класс для тестирования).

В идеологически правильном решении не должно фигурировать ни одного оператора instanceof.

В классе для тестирования объявлены следующие импорты:

import java.util.*;
import java.util.function.*;
 */

import java.util.*;
import java.util.function.Consumer;

public class ex33__4_1 {
    public static void main(String[] args) {
        String randomFrom = "...";
        String randomTo = "...";
        int randomSalary = 100;



        MailMessage firstMessage = new MailMessage(
                "Robert Howard",
                "H.P. Lovecraft",
                "This \"The Shadow over Innsmouth\" story is real masterpiece, Howard!"
        );

        assert firstMessage.getFrom().equals("Robert Howard"): "Wrong firstMessage from address";
        assert firstMessage.getTo().equals("H.P. Lovecraft"): "Wrong firstMessage to address";
        assert firstMessage.getContent().endsWith("Howard!"): "Wrong firstMessage content ending";

        MailMessage secondMessage = new MailMessage(
                "Jonathan Nolan",
                "Christopher Nolan",
                "Брат, почему все так хвалят только тебя, когда практически все сценарии написал я. Так не честно!"
        );

        MailMessage thirdMessage = new MailMessage(
                "Stephen Hawking",
                "Christopher Nolan",
                "Я так и не понял Интерстеллар."
        );

        MailService<String> mailService = new MailService<>();
        List<MailMessage> messages = Arrays.asList(firstMessage, secondMessage, thirdMessage);
        messages.stream().forEachOrdered(mailService);
        Map<String, List<String>> mailBox = mailService.getMailBox();

        assert mailBox.get("H.P. Lovecraft").equals(
                Arrays.asList(
                        "This \"The Shadow over Innsmouth\" story is real masterpiece, Howard!"
                )
        ): "wrong mailService mailbox content (1)";

        assert mailBox.get("Christopher Nolan").equals(
                Arrays.asList(
                        "Брат, почему все так хвалят только тебя, когда практически все сценарии написал я. Так не честно!",
                        "Я так и не понял Интерстеллар."
                )
        ): "wrong mailService mailbox content (2)";

        assert mailBox.get(randomTo).equals(Collections.<String>emptyList()): "wrong mailService mailbox content (3)";



        Salary salary1 = new Salary("Facebook", "Mark Zuckerberg", 1);
        Salary salary2 = new Salary("FC Barcelona", "Lionel Messi", Integer.MAX_VALUE);
        Salary salary3 = new Salary(randomFrom, randomTo, randomSalary);

        MailService<Integer> salaryService = new MailService<>();
        Arrays.asList(salary1, salary2, salary3).forEach(salaryService);
        Map<String, List<Integer>> salaries = salaryService.getMailBox();

        assert salaries.get(salary1.getTo()).equals(Arrays.asList(1)): "wrong salaries mailbox content (1)";
        assert salaries.get(salary2.getTo()).equals(Arrays.asList(Integer.MAX_VALUE)): "wrong salaries mailbox content (2)";
        assert salaries.get(randomTo).equals(Arrays.asList(randomSalary)): "wrong salaries mailbox content (3)";
    }
    public interface Sendable<T> {
        String getFrom();
        String getTo();
        T getContent();
    }
    public static class MailMessage implements Sendable<String> {
        private String from;
        private String to;
        private String content;

        public MailMessage(String from, String to, String content) {
            this.from = from;
            this.to = to;
            this.content = content;
        }
        @Override
        public final String getFrom() {
            return from;
        }
        @Override
        public final String getTo() {
            return to;
        }
        @Override
        public final String getContent() {
            return content;
        }
    }

    public static class Salary implements Sendable<Integer> {
        private String from;
        private String to;
        private Integer content;

        public Salary(String from, String to, Integer content) {
            this.from = from;
            this.to = to;
            this.content = content;
        }
        @Override
        public final String getFrom() {
            return from;
        }
        @Override
        public final String getTo() {
            return to;
        }
        @Override
        public final Integer getContent() {
            return content;
        }
    }

    public static class MailService<T> implements Consumer<Sendable<T>> {
        Map<String, List<T>> objectList = new HashMap<String, List<T>>() {
            @Override
            public List<T> get(Object key) {
                return super.getOrDefault(key, Collections.emptyList());
            }
        };
//        @Override
//        public List<T> get(Object key) {
//            return ((super.get(key) == null) ? Collections.emptyList() : super.get(key));
//        }

        Map<String, List<T>> getMailBox() {
            return objectList;
        }

        @Override
        public void accept(Sendable<T> o) {
            objectList.computeIfAbsent(o.getTo(), k -> new LinkedList<>()).add(o.getContent());
            // метод сomputeIfAbsent возвращает или значение из HashMap по ключу
            // или содержимое, которое мы в него положим с помощью функции
        }
    }
}
//
//        public void accept(Sendable<T> send) {
//            mailDictionary.putIfAbsent(send.getTo(), new ArrayList<>());
//            mailDictionary.get(send.getTo()).add((T) send.getContent());
//        }
//
//        @Override
//        public void accept(Transferable<T> transferable) {
//            mailBox.computeIfAbsent(transferable.getTo(), t -> new ArrayList<>()).add(transferable.getContent());
//        }



/*
В общем, задача не очень сложная (точнее не такая сложная, как это казалось, когда я смотрел на нее в самом начала изучения курса).
Для адекватного понимания того, что от вас требуется (потому что в самом условии написано очень запутано) поясню:

НАЧНИТЕ СРАЗУ ЕСТЬ СЛОНА ПО ЧАСТЯМ.
*  	Загрузите (ctrl+c, ctrl+v) в идею код который должен заработать.
*  	Начните писать классы. Поля классов, конструктор и геттеры понятно,  как писать.
*  	Есть только нюанс, что классы «Почтовое сообщение» и «Зарплата» это  некоторые сущности которые можно отправлять.
    При этом в комментариях пишут, что сделать абстрактный класс и унаследовать от него — плохая  идея.
    Значит надо написать интерфейс, в котором собрать геттеры.
*  	При написании Почтового сервиса можно заметить, что он используется в стриме, который вызывается на коллекцию сообщений.
    Значит, он должен будет что-то по очереди делать с сообщениями которые в него будут переданы.
    Значит сервис должен «имплементировать» консьюмер, параметризованный отправляемой сущностью.

Когда вы выполните действия описанные выше, код перестанет информировать об ошибках и начнутся пляски с двумя не очевидными вещами.

Во-первых — тест "wrong	mailService mailbox content (3)"
Здесь можно «читерить», то есть просто в Почтовом сервисе объявить свой личный класс Map,
который наследует от HashMap, все кроме одного метода get().
Этот метод надо переписывать так, чтоб он всё делал как родительский,
кроме того, что при отсутствии ключа в карте выдавал пустую коллекцию.
Пишут что менторы требуют «оверрайдить» в одну строку.

Во-вторых	— @Override	public void accept()
(так как вы имплементировали в «Почтовый сервис» консьюмер)
Пишут что менторы требуют «оверрайдить» в одну строку.
Я пользовался методом computeIfAbsent(), который возвращает
* 	или содержимое Map, в случае когда он (ключ) есть.
* 	или содержимое, которое вы в него положите при помощи функции.
 */
