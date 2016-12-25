import java.io.*;

/**
 * Created by lyan on 23.12.16.
 */
public class Main {
    public static void main(String[] args) {
        C c = new C();



        try(ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream("C.ser"));){
            stream.writeObject(c);
        }catch (IOException ioe){
            System.out.println(ioe);
        }

        try(ObjectInputStream stream = new ObjectInputStream(new FileInputStream("C.ser"));){
            C cc = (C) stream.readObject();
            System.out.print(cc);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
