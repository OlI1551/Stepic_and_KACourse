// Возьмем наш стандарт public class AnyBeny{сюда {метод}
// тело класса(основной Main сюда добавим в сигнатуру throws MyException){
// Cоздадите обьект класса MyNewException , через него вызываете метод}
// ну и конечно, наш вложенный класс надо прописать, я прост обычно это делаю за основным,
// прописываем класс MyNewException extended Exception{}
// его основная функция наследование Exception.
import java.io.IOException;
public class ex22Exceptions_1 {
    public static void main(String[]args) throws MyNewException {
        MyNewException ex = new MyNewException();
        ex.testExp();


    }

    public static class MyNewException extends IOException {

        public void testExp() throws MyNewException {
            throw new MyNewException();
        }
    }
}




