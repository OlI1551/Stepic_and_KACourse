/*
Java.util.logging
Данный фреймворк включен в стандарт и поставляется вместе с JDK,
поэтому ничего дополнительно скачивать и подключать вам не надо.
JUL имеет следующие уровни логгирования по возрастанию:
FINEST, FINER, FINE, CONFIG, INFO, WARNING, SEVERE, а так же ALL и OFF, включающий и отключающий все уровни соответственно.

Логгер создается вызовом одного из статических методов класса java.util.logging.Logger:
Logger log = Logger.getLogger(LoggingJul.class.getName());

Методы логгера могут принимать в качестве аргументов строковые сообщения, шаблоны сообщений, исключения,
ресурсы локализованных текстовок сообщений, а также, начиная с Java 8, поставщиков строковых сообщений:
// Строковое сообщение
String stringMessage = "Сообщение";
// Строковое сообщение с параметрами
String stringMessageFormat ="Сообщение {0}";
// Исключение
Throwable throwable = new Throwable();
// ResourceBundle хранящий сообщения
ResourceBundle resourceBundle = ResourceBundle.getBundle("logging.jul.bundle");
// Поставщик сообщений
Supplier<String> stringMessageSupplier = ()->"Сообщение";

Выделяется две группы методов: название которых соответствует уровню логгирования
и методы log, loggp, logrb, принимающие уровень логгирования в качестве параметра с типом Level.

Первая группа содержит методы двух типов:
принимающих строковое сообщение или поставщика строковых сообщений:
log.info(stringMessage);
log.info(stringMessageSupplier);

Вторая группа методов имеет следующие вариации:
// Вывести сообщение с указанием уровня логгирования
log.log(new LogRecord(Level.INFO, stringMessage));
log.log(Level.INFO, stringMessage);
log.log(Level.INFO, stringMessageSupplier);
log.log(Level.INFO, stringMessageFormat, args);
log.log(Level.INFO, stringMessage, throwable );
log.log(Level.INFO, throwable, stringMessageSupplier);
// Вывести сообщение с указанием уровня логгирования, класса и метода
log.logp(Level.INFO, "ClassName", "MethodName", stringMessage);
log.logp(Level.INFO, "ClassName", "MethodName", stringMessageSupplier);
log.logp(Level.INFO, "ClassName", "MethodName", stringMessageFormat, args);
log.logp(Level.INFO, "ClassName", "MethodName", stringMessage, throwable);
log.logp(Level.INFO, "ClassName", "MethodName", throwable, stringMessageSupplier);
// Вывести сообщение с указанием уровня логгирования, класса,
// метода и resourceBundle, хранящего сообщения
log.logrb(Level.INFO, "ClassName", "MethodName", resourceBundle, "messageId");
log.logrb(Level.INFO, "ClassName", "MethodName", resourceBundle, "messageId", throwable);
// Вывести сообщение об ошибке
log.throwing("ClassName","MethodName", throwable);

Теперь обратимся к конфигурации фреймворка. По умолчанию JUL будет выводить сообщения на консоль,
однако можно задать конфигурацию в файле свойств. Для задания способа вывода сообщений необходимо для вашего логгера
указать какие хендлеры он будет использовать.
Существует следующие классы хендлеров: FileHandler, ConsoleHandler, StreamHandler, SocketHandler, MemoryHandler.
Особенностью JUL является то, что настройки хендлеров задаются в целом для всего класса, а не для конкретного экземпляра,
что может порождать не мало проблем, например если вам потребуется сообщения различных логгеров выводить
в различные файлы или с различным форматированием. Рассмотрим простой пример конфигурационного файла:
# Настройки глобального логгера
handlers =java.util.logging. FileHandler
.level=ALL
# Конфигурация файлового хендлера
java.util.logging.FileHandler.level =ALL
java.util.logging.FileHandler.formatter =java.util.logging.SimpleFormatter
java.util.logging.FileHandler.limit = 1000000
java.util.logging.FileHandler.pattern   = log.txt
# Конфигурация консольного хендлера
java.util.logging.ConsoleHandler.level = ALL
java.util.logging.ConsoleHandler.pattern = log.log
java.util.logging.ConsoleHandler.formatter =java.util.logging.SimpleFormatter

Для того что бы JUL применил данную конфигурацию нужно передать параметр
-Djava.util.logging.config.file = <путь до файла>, либо при старте приложения выполнить код:
LogManager.getLogManager().readConfiguration(<ваш класс>.class.getResourceAsStream("logging.properties"));
 */
public class ex29Logging_2 {
    public static void main(String[] args) {

    }
}
