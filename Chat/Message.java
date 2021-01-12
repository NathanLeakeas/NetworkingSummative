package Chat;
import java.io.*;
import java.util.ArrayList;;

public class Message implements Serializable {
    String protocol;
    ArrayList argument;

    public Message(String prot,ArrayList arg)
    {
        protocol=prot;
        argument=arg;
    }
}
