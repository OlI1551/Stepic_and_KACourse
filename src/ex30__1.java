/*
Задача:
Создайте дженерик класс MyBox,
который может хранить в себе один объект класса,
которым он параметризирован в поле с названием object.

Требования:
1. Класс должен быть параметризован T.
2. Поле должно быть приватным с именем object.
3. Класс должен иметь модификатор доступа по умолчанию.
 */
public class ex30__1 {
    public static void main(String[] args) {

    }
}
class MyBox<T> {
    private T object;
}
/*
ТЗ по русски  :
Создайте обобщенный (он же дженерик) класс, указав у данного класса в качестве универсального параметра T
Объявите приватное поле  с именем object. Типом поля является указанный при создании класса универсальный параметр T
 */
