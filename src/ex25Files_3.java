import java.io.*;

public class ex25Files_3 {

    public static void main(String[] args) {

        try(FileInputStream fin = new FileInputStream("notes.txt"))
        {
            byte[] buffer = new byte[256];
            System.out.println("File data:");

            int count;
            while((count=fin.read(buffer))!=-1){

                for(int i=0; i<count;i++){

                    System.out.print((char)buffer[i]);
                }
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}