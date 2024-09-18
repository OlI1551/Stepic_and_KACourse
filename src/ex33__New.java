import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ex33__New {
    public static void main(String[] args) {
        // Создаем окно
        JFrame frame = new JFrame("Button Click Example");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создаем кнопку
        JButton button = new JButton("Click me");

        // Добавляем анонимный класс в качестве слушателя события нажатия на кнопку
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button clicked!");
            }
        });

        // Добавляем кнопку в окно и отображаем его
        frame.add(button);
        frame.setVisible(true);
    }
}
