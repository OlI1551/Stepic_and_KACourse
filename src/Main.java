import java.util.Arrays;

/*
В IntelliJ IDEA у вас есть несколько вариантов для переключения вкладок с помощью горячих клавиш:

Перемещение между открытыми вкладками:
Ctrl + Tab: переключение между текущей и предыдущей вкладками.
Ctrl + Shift + Tab: переключение между текущей и следующей вкладками.
Ctrl + E: открытие окна "Recent Files" для быстрого доступа к последним файлам.
Перемещение по вкладкам в пределах одного режима (например, между файлами редактора):
Ctrl + Alt + ← или →: переключение между вкладками слева или справа от текущей.
Ctrl + Page Up или Page Down: переключение на следующую или предыдущую вкладку.
Перемещение между различными панелями и окнами:
Alt + 1: переключение на окно "Projects".
Alt + 2: переключение на окно "Favorites".
Alt + 6: переключение на окно "TODO".
Ctrl + Shift + F12: раскрытие окна редактора в полноэкранный режим.
Вы также можете настроить наиболее удобные для вас горячие клавиши, перейдя в "Settings/Preferences" > "Keymap" и находя соответствующие действия для переключения вкладок.

 */
// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        System.out.println("ПРИМЕРЫ");
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        // Press Shift+F10 or click the green arrow button in the gutter to run the code.
        for (int i = 1; i <= 5; i++) {

            // Press Shift+F9 to start debugging your code. We have set one breakpoint
            // for you, but you can always add more by pressing Ctrl+F8.
            System.out.println("i = " + i);
        }

        System.out.println("ПРИМЕР ПО КЛОНИРОВАНИЮ");
        int[] originalArray = {1, 2, 3};
        MyClass original = new MyClass("Hello", originalArray);
        MyClass cloned = (MyClass) original.clone();

        // Изменим оригинальный массив
        originalArray[0] = 99;
        original.setMutableArray(originalArray);

        // Проверяем оригинал и клон
        System.out.println("Original string: " + original.getImmutableString()); // Hello
        System.out.println("Cloned string: " + cloned.getImmutableString());     // Hello

        System.out.println("Original array: " + Arrays.toString(original.getMutableArray())); // [99, 2, 3]
        System.out.println("Cloned array: " + Arrays.toString(cloned.getMutableArray())); // [1, 2, 3]
    }
}