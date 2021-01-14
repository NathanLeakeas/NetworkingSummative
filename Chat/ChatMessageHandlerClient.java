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
            ObjectInputStream fromHost= new ObjectInputStream(s.getInputStream());
            ObjectOutputStream toHost = new ObjectOutputStream(s.getOutputStream());

            while(true)
            {
                try
                {
                    Message newMessage =  (Message)fromHost.readObject();
                    System.out.println("msg received");
                    System.out.println(newMessage.getProtocol()+":"+newMessage.argument.get(0));
                    if(newMessage.protocol.compareTo("MSG")==0)
                    {
                        String textMessage = (String)newMessage.argument.get(0);
                        System.out.println(textMessage);
                    }
                    
                }
                catch(Exception e)
                {

                }
                
            }
        }
        catch(IOException e)
        {

        }
        
    }
}
