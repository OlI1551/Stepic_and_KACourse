import java.io.*;
import java.util.Arrays;
import java.util.Objects;

public class ex27_1 {
    public static void main(String[] args) throws Exception {
        byte[] intermediate = null;
        try (
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(output)) {

            oos.writeInt(3);
            oos.writeObject(new Animal("Dog"));
            oos.writeObject(new Animal("Cat"));
            oos.writeObject(new Animal("Mouse"));

            output.flush();
            intermediate = output.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(Arrays.toString(intermediate));
        Animal[] animals = deserializeAnimalArray(intermediate);
        System.out.println(Arrays.toString(animals));

    }
    // Создал объект ObjectInputStream через ByteArrayInputStream.
    // Прочитал int из потока и создал массив соответствующего размера, заполнил в цикле и вернул его в качестве результата.
    // Про исключение не заморачивайтесь, я обернул все в try-catch.
    // Ловил обычный Exception, а обработал его одной строкой - выбросив требуемое исключение по условию задачи.
    public static Animal[] deserializeAnimalArray(byte[] data) {
        Animal[] animalArray = null;
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(data);
            ObjectInputStream ois = new ObjectInputStream(bais);

            int animalCount = ois.readInt();
            animalArray = new Animal[animalCount];

            for (int i = 0; i < animalCount; i++) {
                animalArray[i] = (Animal) ois.readObject();
            }
            ois.close();

        } catch (ArrayIndexOutOfBoundsException | NegativeArraySizeException | ClassNotFoundException | ClassCastException | IOException e) {
            throw new IllegalArgumentException(e);
        }

        return animalArray;
    }
}
class Animal implements Serializable {
    private final String name;

    public Animal(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Animal) {
            return Objects.equals(name, ((Animal) obj).name);
        }
        return false;
    }

    public String toString() {
        return name;
    }
}