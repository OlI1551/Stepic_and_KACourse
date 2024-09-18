import java.io.*;

public class ex25Files_6 {

    public static void main(String[] args) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String text = "Hello Wolrd!";
        byte[] buffer = text.getBytes();
        try{
            baos.write(buffer);
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }
        // превращаем массив байтов в строку
        System.out.println(baos.toString());

        // получаем массив байтов и выводим по символьно
        byte[] array = baos.toByteArray();
        for(byte b: array){

            System.out.print((char)b);
        }
        System.out.println();

        // запишем в файл
        ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
        String text1 = "Hello Java!";
        byte[] buffer1 = text1.getBytes();
        try{
            baos1.write(buffer1);
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }
        try(FileOutputStream fos = new FileOutputStream("hellojava.txt")){

            baos1.writeTo(fos);
        }
        catch(IOException e){

            System.out.println(e.getMessage());
        }
    }
}