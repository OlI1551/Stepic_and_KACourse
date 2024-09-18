/*
При создании приложений мы часто сталкиваемся с ошибками, которые необходимо отлаживать.
Итак, с помощью логов мы можем легко получить информацию о том, что происходит в приложении,
с записью ошибок и необычных обстоятельств.
В Java существует API для логирования,
предоставленный в пакете java.util.logging.

Компоненты ведения журнала помогают разработчику создавать их,
передавать в соответствующее место назначения и поддерживать надлежащий формат. Ниже приведены три компонента:
1) Loggers – отвечает за сбор записей журнала и передачу их соответствующему заявителю.
2) Appenders или Handlers – они отвечают за запись событий журнала в пункт назначения.
Аппендеры форматируют события с помощью макетов перед отправкой результатов.
3) Layouts или Formatters – отвечает за определение того, как данные выглядят, когда они появляются в записи журнала.


В мире java из-за отсутствия какого-то общего логгера, который нравился бы всем,
наплодилось много разных (несколько компаний сделали свои логеры).
JUL — java.util.logging - стандартный java-логгер
log4j, log4j2
JCL — jakarta commons logging
Logback
SLF4J — simple logging facade for java
Когда подключаешь себе зависимости они могут использовать внутри себя разные логеры.
slf4j старается решить эту проблему, создав обертки для каждого логера. Т.е. дает возможность использовать их все как один.
Например в проекте 3 зависимости:
1. использует log4j
2. использует logback
3. использует java.util
без slf4j пришлось бы конфигурировать каждый. А с ним можно задать один конфиг и спокойно с ним работать, как будто во всем проекте один логгер.
Но могу быть не прав )


Что такое Логгеры (Logger) в Java?
Логгеры (Logger) в Java – это объекты, которые запускают события журнала. Они создаются и вызываются в коде приложения,
где генерируют события журнала перед передачей их следующему компоненту, который является Appender.
Можно использовать несколько логгеров в одном классе для ответа на различные события или использовать в иерархии.
Они обычно называются с использованием иерархического пространства имен, разделенных точками.
По правилам хорошего кода все имена Logger должны основываться на классе или имени пакета зарегистрированного компонента.
Кроме того, каждый логгер отслеживает ближайшего существующего предка в пространстве имен,
а также имеет связанный с ним «уровень».

Как создать?
Вы должны использовать Logger.getLogger() .
Метод getLogger() идентифицирует имя Logger и принимает строку в качестве параметра.
Таким образом, если Logger уже существует, он возвращается, в противном случае создается новый.
   static Logger logger = Logger.getLogger(TestClass.class.getName());
Здесь TestClass – это имя класса, для которого мы получаем объект Logger.
Пример:
public class Customer{
    private static final Logger LOGGER = Logger.getLogger(Customer.class.getName());
    public void getCustomerDetails() {
    }
}

Уровни
Уровни журналов используются для классификации их по степени серьезности или влиянию на стабильность приложения.
Пакет java.util.logging предоставляет следующие уровни в порядке убывания:
SEVERE (Самый строгий уровень);
WARNING;
INFO;
CONFIG;
FINE;
FINER;
FINEST

Регистрация событий
Чтобы регистрировать события в Java, вы должны убедиться, что вы назначаете уровень, чтобы легко отфильтровать события.
Чтобы назначить уровень и добавить сообщение, вы можете использовать следующие методы:
Способ 1
logger.log(Level.INFO, “Сообщение с уровнем INFO”);
Способ 2
logger.info(“Сообщение с уровнем INFO”);

Для того, чтобы установить для логгера определенный уровень логирования используется метод setLevel():
logger.setLevel(Level.WARNING);

Логгер будет регистрировать события указанного уровня и всех уровней выше
(логгер из примера будет регистрировать события уровней WARNING и SEVERE)
 */
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class ex29Logging {
    static Logger LOGGER;
    static {
        try(FileInputStream ins = new FileInputStream("C:\\JavaEx\\log.config")){ // полный путь до файла с конфигами
            LogManager.getLogManager().readConfiguration(ins);
            LOGGER = Logger.getLogger(ex29Logging.class.getName());
        }catch (Exception ignore){
            ignore.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {

        try {
            FileOutputStream fos = new FileOutputStream("C:\\JavaEx\\log.config");
            LOGGER.log(Level.INFO,"Начало main, создаем лист с типизацией Integers");
            List<Integer> ints = new ArrayList<Integer>();
            LOGGER.log(Level.INFO,"присваиваем лист Integers листу без типипзации");
            List empty = ints;
            LOGGER.log(Level.INFO,"присваиваем лист без типипзации листу строк");
            List<String> string = empty;
            LOGGER.log(Level.WARNING,"добавляем строку \"бла бла\" в наш переприсвоенный лист, возможна ошибка");
            string.add("бла бла");
            LOGGER.log(Level.WARNING,"добавляем строку \"бла 23\" в наш переприсвоенный лист, возможна ошибка");
            string.add("бла 23");
            LOGGER.log(Level.WARNING,"добавляем строку \"бла 34\" в наш переприсвоенный лист, возможна ошибка");
            string.add("бла 34");


            LOGGER.log(Level.INFO,"выводим все элементы листа с типизацией Integers в консоль");
            for (Object anInt : ints) {
                System.out.println(anInt);
            }

            LOGGER.log(Level.INFO,"Размер равен " + ints.size());
            LOGGER.log(Level.INFO,"Получим первый элемент");
            Integer integer = ints.get(0);
            LOGGER.log(Level.INFO,"выведем его в консоль");
            System.out.println(integer);

        }catch (Exception e){
            LOGGER.log(Level.WARNING,"что-то пошло не так" , e);
        }
    }
}