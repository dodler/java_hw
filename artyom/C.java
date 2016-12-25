import java.io.Serializable;

/**
 * Created by lyan on 23.12.16.
 */
public class C extends B implements Serializable{
    public C(){
        super("asd");
        System.out.println("C");
    }
}
