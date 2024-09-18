import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
Эта задачка совмещает тренировку по материалу предыдущих двух модулей – необходимо разобраться и написать
объект-ориентированный код и при этом коснуться свежих тем – исключений и логирования.

Все классы должны быть определены как публичные и статические,
так как в процессе проверки ваш код будет подставлен во внешний класс, который занимается тестированием и проверкой структуры.
Для удобства во внешнем классе объявлено несколько удобных констант и импортировано все содержимое пакета java.util.logging.
Для определения, посылкой или письмом является Sendable объект воспользуйтесь оператором instanceof.

public static final String AUSTIN_POWERS = "Austin Powers";
public static final String WEAPONS = "weapons";
public static final String BANNED_SUBSTANCE = "banned substance";

Дан набор классов, описывающих работу гипотетической почтовой системы.
Для начала рассмотрим код, описывающий все используемые сущности.
1) Interface Sendable
2) У Sendable есть 2 наследника, объединенные абстрактным классом
3) Первый класс описывает обычное письмо, в котором находится только текстовое сообщение
4) Второй класс описывает почтовую посылку
5) При этом сама посылка описывается следующим классом
И 2 класса, которые моделируют работу почтового сервиса:

 */
public class ex29NewMailService {
    public static final String AUSTIN_POWERS = "Austin Powers";
    public static final String WEAPONS = "weapons";
    public static final String BANNED_SUBSTANCE = "banned substance";

    public static void main (String[] args) {
        Logger logger = Logger.getLogger(ex29NewMailService.class.getName());

        Inspector inspector = new Inspector();
        Spy spy = new Spy(logger);
        Thief thief = new Thief(10000);
        MailService[] variousWorkers= new MailService[]{spy, thief, inspector};
        UntrustworthyMailWorker worker = new UntrustworthyMailWorker(variousWorkers);

        AbstractSendable[] correspondence = {
                new MailMessage("Oxxxymiron", "Гнойный", "Я здесь чисто по фану, поглумиться над слабым\n" +
                        "Ты же вылез из мамы под мой дисс на Бабана...."),
                new MailMessage("Гнойный", "Oxxxymiron", "....Что? Так болел за Россию, что на нервах терял ганглии.\n" +
                        "Но когда тут проходили митинги, где ты сидел? В Англии!...."),
                new MailMessage("Жриновский", AUSTIN_POWERS, "Бери пацанов, и несите меня к воде."),
                new MailMessage(AUSTIN_POWERS, "Пацаны", "Го, потаскаем Вольфовича как Клеопатру"),
                new MailPackage("берег", "море", new Package("ВВЖ", 32)),
                new MailMessage("NASA", AUSTIN_POWERS, "Найди в России ракетные двигатели и лунные stones"),
                new MailPackage(AUSTIN_POWERS, "NASA", new Package("ракетный двигатель ", 2500000)),
                new MailPackage(AUSTIN_POWERS, "NASA", new Package("stones", 1000)),
                new MailPackage("Китай", "КНДР", new Package("banned substance", 99)),
                new MailPackage(AUSTIN_POWERS, "ИГИЛ (запрещенная группировка", new Package("tiny bomb", 9000)),
                new MailMessage(AUSTIN_POWERS, "Психиатр", "Помогите"),
        };
        Arrays.stream(correspondence).forEach(parcell -> {
            try {
                worker.processMail(parcell);
            } catch (StolenPackageException e) {
                logger.log(Level.WARNING, "Inspector found stolen package: " + e);
            } catch (IllegalPackageException e) {
                logger.log(Level.WARNING, "Inspector found illegal package: " + e);
            }
        });
    }
    /*
    Интерфейс: сущность, которую можно отправить по почте.
    У такой сущности можно получить от кого и кому направляется письмо.
    */
    public static interface Sendable {
        String getFrom();
        String getTo();
    }
    /*
    Абстрактный класс,который позволяет абстрагировать логику хранения
    источника и получателя письма в соответствующих полях класса.
    */
    public static abstract class AbstractSendable implements Sendable {
        protected final String from;
        protected final String to;
        public AbstractSendable(String from, String to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public String getFrom() {
            return from;
        }
        @Override
        public String getTo() {
            return to;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            AbstractSendable that = (AbstractSendable) o;
            if (!from.equals(that.from)) return false;
            if (!to.equals(that.to)) return false;
            return true;
        }
    }
    /*
    Письмо, у которого есть текст, который можно получить с помощью метода `getMessage`
    */
    public static class MailMessage extends AbstractSendable {
        private final String message;
        public MailMessage(String from, String to, String message) {
            super(from, to);
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            MailMessage that = (MailMessage) o;
            if (message != null ? !message.equals(that.message) : that.message != null) return false;
            return true;
        }
    }
    /*
    Посылка, содержимое которой можно получить с помощью метода `getContent`
    */
    public static class MailPackage extends AbstractSendable {
        private final Package content;
        public MailPackage(String from, String to, Package content) {
            super(from, to);
            this.content = content;
        }

        public Package getContent() {
            return content;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            MailPackage that = (MailPackage) o;
            if (!content.equals(that.content)) return false;
            return true;
        }

    }
    /*
    Класс, который задает посылку. У посылки есть текстовое описание содержимого и целочисленная ценность.
    */
    public static class Package {
        private final String content;
        private final int price;
        public Package(String content, int price) {
            this.content = content;
            this.price = price;
        }

        public String getContent() {
            return content;
        }
        public int getPrice() {
            return price;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Package aPackage = (Package) o;
            if (price != aPackage.price) return false;
            if (!content.equals(aPackage.content)) return false;
            return true;
        }
    }
    /*
    Теперь рассмотрим классы, моделирующие работу почтового сервиса:
    Интерфейс MailService задает общий класс, который может каким-либо образом обработать почтовый объект.
    */
    public interface MailService {
        Sendable processMail(Sendable mail);
    }
    public static class RealMailService implements MailService {

        @Override
        public Sendable processMail(Sendable mail) {
            System.out.println("Почту обработали");
            return mail;
        }
    }
    /* класс, который является MailService:
    1) UntrustworthyMailWorker – класс, моделирующий ненадежного работника почты, который вместо того,
    чтобы передать почтовый объект непосредственно в сервис почты, последовательно передает этот объект набору третьих лиц,
    а затем, в конце концов, передает получившийся объект непосредственно экземпляру RealMailService.
    У UntrustworthyMailWorker должен быть конструктор от массива MailService (результат вызова processMail первого элемента
    массива передается на вход processMail второго элемента, и т. д.) и метод getRealMailService,
    который возвращает ссылку на внутренний экземпляр RealMailService, он не приходит массивом в конструкторе и не настраивается извне класса.


    1) UntrustworthyMailWorker
    - «должен быть конструктор от массива MailService» - значит мы должны создать массив объектов MailService
    и свзяать его с конструктором (например через слово this)

    - «метод getRealMailService, который возвращает ссылку на внутренний экземпляр RealMailService,
    он не приходит массивом в конструкторе и не настраивается извне класса.» -
    значит нужно создать в классе объект RealMailService и в методе просто его вернуть.

    - «класс, моделирующий ненадежного работника почты, который вместо того, чтобы передать почтовый объект непосредственно
    в сервис почты, последовательно передает этот объект набору третьих лиц, а затем, в конце концов, передает получившийся объект
    непосредственно экземпляру RealMailService» + «(результат вызова processMail первого элемента массива передается
    на вход processMail второго элемента, и т. д.)» -  третьи лица – это классы, которые мы будем писать ниже
    (шпион, вор, инспектор). Метод (тот самый из интерфейса) возвращает некую сущность, которую можно отправить по почте
    и в параметрах содержит переменную этой сущности. Эта переменная будет меняться в зависимости от действий третьих лиц.
    Таким образом нам нужно создать такую переменную в методе (она будет своего рода счетчиком).
    Так как мы работаем с несколькими лицами (те самые третье лица), то лучше по ним пройтись с помощью массива.
    В теле массива нашей переменной счетчику будем присваивать «результат вызова processMail первого(а потом и второго и третьего)элемента массива».
    Массив мы уже создали выше – работаем с ним.
    -  «а затем, в конце концов, передает получившийся объект непосредственно экземпляру» - это значит у нашего
    ранее созданного объекта RealMailService нужно вызвать метод, который мы прогоняли через цикл.
    */
    public static class UntrustworthyMailWorker implements MailService {
        private MailService[] falseWorkers; // набор 3-х лиц
        private RealMailService realWorker = new RealMailService(); // работник настоящего MailService

        public UntrustworthyMailWorker (MailService[] falseW){ // конструктор нерадивого работника
            this.falseWorkers = falseW;
        }
        @Override
        public Sendable processMail(Sendable mail) {
            for (int i = 0; i < falseWorkers.length; i++) { // перебираем всех 3-х лиц
                mail = falseWorkers[i].processMail(mail); // каждое лицо из 3-х лиц обрабатывает посылку, и нерадивый работник передает результат обработки следующему паразиту
            }
            return realWorker.processMail(mail);
        }
        public MailService getRealMailService() {
            return realWorker; // нерадивый работник вызывает настоящего работника
        }
    }
    /**
     2) Spy – шпион, который логгирует о всей почтовой переписке, которая проходит через его руки.
     Объект конструируется от экземпляра Logger, с помощью которого шпион будет сообщать о всех действиях.
     Он следит только за объектами класса MailMessage и пишет в логгер следующие сообщения
     (в выражениях нужно заменить части в фигурных скобках на значения полей почты):
     2.1) Если в качестве отправителя или получателя указан "Austin Powers", то нужно написать в лог сообщение с уровнем WARN:
     Detected target mail correspondence: from {from} to {to} "{message}"
     2.2) Иначе, необходимо написать в лог сообщение с уровнем INFO: Usual correspondence: from {from} to {to}
     */
    /*
    2) Spy
    - «Объект конструируется от экземпляра Logger, с помощью которого шпион будет сообщать о всех действиях.»
    - создаем Logger и связываем его с конструктором.
    «2.1) Если в качестве отправителя или получателя указан "Austin Powers", то нужно написать в лог сообщение с уровнем WARN:
    Detected target mail correspondence: from {from} to {to} "{message}" 2.2)
    Иначе, необходимо написать в лог сообщение с уровнем INFO: Usual correspondence: from {from} to {to} »  -
    по условию этот шпион работает только с письмами (MailMessage), а у нас в параметрах метода (того самого из интерфейса)
    некая сущность которою можно отправить по почте. Но отправить можно не только письмо, следовательно нам нужно привести
    нашу сущность к письму. Это возможно так как все классы и интерфейсы в этой задаче связаны. Таким образом мы создадим письмо.
    Теперь нам нужно узнать кто на конверте этого письма указан отправителем, а кто получателем (применим соответствующие методы).
    После этого идем уже по условию задачи, там более менее понятно описано. Не забываем в логере экранировать некоторые символы.
     */

    public static class Spy implements MailService {
        private final Logger LOGGER; // объявляем логгер

        public Spy(Logger l) { // конструктор шпиона
            this.LOGGER = l;
        }

        @Override
        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailMessage) { // проверяем, является ли полученное отправление письмом - шпиона интересуют только письма
                MailMessage mailForSpy = (MailMessage) mail; // создаем из нашего отправления письмо, которым займется шпион
                if (mailForSpy.getFrom().equals(AUSTIN_POWERS) || mailForSpy.getTo().equals(AUSTIN_POWERS)) {
                    this.LOGGER.log(Level.WARNING, "Detected target mail correspondence: from {0} to {1} \"{2}\"",
                            new Object[]{mailForSpy.getFrom(), mailForSpy.getTo(), mailForSpy.getMessage()});
                } else {
                    this.LOGGER.log(Level.INFO, "Usual correspondence: from {0} to {1}", new Object[]{mailForSpy.getFrom(), mailForSpy.getTo(), mailForSpy.getMessage()});
                }
            }
            return mail; // возвращает письмо в неизменном виде
        }
    }
    /**
     3) Thief – вор, который ворует самые ценные посылки и игнорирует все остальное.
     Вор принимает в конструкторе переменную int – минимальную стоимость посылки, которую он будет воровать.
     Также, в данном классе должен присутствовать метод getStolenValue, который возвращает суммарную стоимость всех посылок,
     которые он своровал. Воровство происходит следующим образом: вместо посылки, которая пришла вору, он отдает новую,
     такую же, только с нулевой ценностью и содержимым посылки "stones instead of {content}".
     */
    /*
    3) Thief
    До метода (того самого из интерфейса) написано всё понятно.
    «Воровство происходит следующим образом: вместо посылки, которая пришла вору, он отдает новую, такую же,
    только с нулевой ценностью и содержимым посылки "stones instead of {content}".» - аналогичная ситуация описанная выше.
    В параметрах у нас некая сущность, а нам нужно привести её именно к посылке + узнать содержимое этой посылки
    (для этого существует метод из описания к задаче).
    Далее, если стоимость посылки больше или равна минимальной стоимости, то мы её воруем
    (т.е. добавляем её сумму в суммарную стоимость всех посылок).
    Далее нам требуют отправить обратно подмененную посылку. Но метод (да-да, тот самый из интерфейса ) требует нам вернуть
    именно сущность, которую можно отправить по почте. Поэтому ссылка будет Sendable, а объект MailPackage
    и далее по конструктору. У посылки ( не путать с почтовой посылкой) нужно будет вызвать (в рамках конструктора)
    метод разрывающий её содержание.
     */
    public static class Thief implements MailService {
        private int stolenValue; // счетчик - здесь хранится сумма украденного
        private int minValue; // минимальная стоимость посылки, которую надо украсть

        public Thief(int m) {
            this.minValue = m; // при создании нового вора указываем ему, с какой стоимостью посылки воровать
            this.stolenValue = 0; // новый вор еще ничего не украл

        }

        public int getStolenValue() {
            return stolenValue;  // можем узнать, скольку вор наворовал
        }

        @Override
        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailPackage) { // проверяем, является ли полученное отправление посылкой - вора интересуют только посылки
                MailPackage mailForThief = (MailPackage) mail; // создаем из нашего отправления объект посылку, которой займется вор
                if (mailForThief.getContent().getPrice() >= this.minValue) { // вор проверил стоимость посылки и она подходящая
                    this.stolenValue += mailForThief.getContent().getPrice(); // вор украл посылку
                    return new MailPackage(mailForThief.getFrom(), mailForThief.getTo(),
                            new Package("stones instead of " + mailForThief.getContent().getContent(), 0)); // вор отдал подставную посылку
                }
            }
            return mail; // вор отдал малоценную посылку
        }
    }
    /**
     * 4) Inspector – Инспектор, который следит за запрещенными и украденными посылками и бьет тревогу в виде исключения,
     * если была обнаружена подобная посылка. Если он заметил запрещенную посылку с одним из запрещенных содержимым
     * ("weapons" и "banned substance"), то он бросает IllegalPackageException. Если он находит посылку,
     * состоящую из камней (содержит слово "stones"), то тревога прозвучит в виде StolenPackageException.
     * Оба исключения вы должны объявить самостоятельно в виде непроверяемых исключений.
     */
    /*
    4) Inspector
    Для начала стоит отметить что почтовая посылка и посылка не являются классом родителем и наследником.
    Но метод у почтовой посылки позволяет получать посылку обычную. В принципе, например, IDEA не будет ругаться,
    если мы нашу некую сущность приведем сразу к обычной посылке, но местная прога будет недовольна, так как задача исходит
    из принципа, что обычная посылка завернута (т.е. представляет почтовую посылку) и потому нужно для начала её развернуть.
    После этого используем метод простой посылки, позволяющий узнать о ней информацию.
    Потом сравниваем эту строку с тем, о чем нас просят в задаче и выводим соответствующие исключения.
    Я использовал contains и indexOf.
     */

    public static class IllegalPackageException extends RuntimeException {
        public IllegalPackageException() {
        }

        public IllegalPackageException (String message) {
            super("IllegalPackageException!");
        }
    }
    public static class StolenPackageException extends RuntimeException {
        public StolenPackageException() {
        }

        public StolenPackageException (String message) {
            super("Discovered the theft from the parcel!");
        }
    }

    public static class Inspector implements MailService {

        @Override
        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailPackage) {
                MailPackage mailForInspector = (MailPackage) mail;
                if (mailForInspector.getContent().getContent().contains(WEAPONS) ||
                        mailForInspector.getContent().getContent().contains(BANNED_SUBSTANCE)) {
                    throw new IllegalPackageException();
                }
                if (mailForInspector.getContent().getContent().contains("stones")) {
                    throw new StolenPackageException();
                }
                return mailForInspector; // должен вернуть ту посылку, которую получил в измененном виде
            }
            return mail; // почта инспектора не интересует
        }
    }
}