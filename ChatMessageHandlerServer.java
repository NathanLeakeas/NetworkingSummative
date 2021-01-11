package Chat;
import java.net.*;
import java.util.Scanner;
import java.io.*;
import java.util.List;


public class ChatMessageHandlerServer implements Runnable
{
    Scanner fromHost;
    PrintWriter toHost;
    Socket s;
    List<Socket> clientList;
    public ChatMessageHandlerServer(Socket sock, List<Socket> sockList)
    {
        s = sock;
        clientList = sockList;
    }

    public void run()
    {
            try
            {
                fromHost = new Scanner(s.getInputStream());
                toHost = new PrintWriter(s.getOutputStream());
                HandleMessages();
                
            }
            catch(IOException e)
            {

            }
            
    }

    public void HandleMessages() throws IOException
    {
        while(true)
        {
            //if(!fromHost.hasNext()){return;}
            String message = fromHost.nextLine();
            //System.out.println("message: "+message);
            for(Socket otherClient : clientList)
            {
                if(otherClient!=s)//prevent sending message to client that sent the message
                {
                    PrintWriter toNewClient = new PrintWriter(otherClient.getOutputStream());
                    toNewClient.println(""+otherClient.getInetAddress()+":"+message);
                    toNewClient.flush();
                }
            }
        }
    }

}