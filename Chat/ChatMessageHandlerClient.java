package Chat;
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class ChatMessageHandlerClient implements Runnable {
    
    Socket s;
    public ChatMessageHandlerClient(Socket sock)
    {
        s=sock;
    }

    public void run()
    {
        try{
            Scanner fromHost = new Scanner(s.getInputStream());
            PrintWriter toHost = new PrintWriter(s.getOutputStream());

            while(true)
            {
                System.out.println(fromHost.nextLine());
            }
        }
        catch(IOException e)
        {

        }
        
    }
}
