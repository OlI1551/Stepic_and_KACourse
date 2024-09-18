/*
Напишите программу, читающую текст из System.in и выводящую в System.out сумму всех встреченных в тексте
вещественных чисел с точностью до шестого знака после запятой.
Числом считается последовательность символов, отделенная от окружающего текста пробелами или переводами строк
и успешно разбираемая методом Double.parseDouble.
На этот раз вам надо написать программу полностью, т.е. объявить публичный класс с именем Main
и точку входа в программу — метод main. И добавить все необходимые импорты.

Пример ввода 1: 1 2 3
Пример вывода 1: 6.000000

Пример ввода 2: a1 b2 c3
Пример вывода 2: 0.000000

Пример ввода 3:  -1e3 18 .111 11bbb
Пример вывода 3: -981.889000

Требования:
1. Программа должна читать текст из System.in
2. Программа должна выводить число с точностью до 6 знака после запятой

после ввода чисел с клавиатуры (0,1 5 10 kj; k * 20) нажать Ctrl + D!!! Результат - сумма 35,100000

The nextDouble() method of java.util.Scanner class scans the next token of the input as a Double().
If the translation is successful, the scanner advances past the input that matched.
Syntax:
public double nextDouble()
Parameters: The function does not accepts any parameter.
Return Value: This function returns the Double scanned from the input.
Exceptions: The function throws three exceptions as described below:
1) InputMismatchException: if the next token does not matches the Float regular expression, or is out of range
2) NoSuchElementException: if input is exhausted
3) IllegalStateException: if this scanner is closed
 */

import java.io.IOException;
import java.util.Scanner;

public class ex26_4withScanner_1 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        double total = 0;
        while (sc.hasNext()) {
            if (sc.hasNextDouble()) {
                total += sc.nextDouble();
            } else {
                sc.next(); // нужно обязательно считать элемент!!! чтобы его пропустить
            }
        }
        sc.close();
        System.out.printf("%.6f", total);
    }
}
