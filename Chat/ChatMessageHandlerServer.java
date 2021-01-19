package Chat;
import java.net.*;
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class ChatMessageHandlerServer implements Runnable
{
    ObjectInputStream fromHost;
    ObjectOutputStream toHost;
    Socket s;
    List<SocketInfo> clientList;
    public ChatMessageHandlerServer(Socket sock, List<SocketInfo> sockList)
    {
        s = sock;
        clientList = sockList;
    }

    public void run()
    {
            try
            {
                
                HandleMessages();
                
            }
            catch(Exception e)
            {
                System.out.println("Exception:"+e+" at: ");
                e.printStackTrace();
            }
            
    }

    public void HandleMessages() throws IOException, ClassNotFoundException
    {
        while(true)
        {
            fromHost = new ObjectInputStream(s.getInputStream());
            toHost = new ObjectOutputStream(s.getOutputStream());
            //if(!fromHost.hasNext()){return;}
            Message message = (Message)fromHost.readObject();
            System.out.println(message.getProtocol()+":"+message.argument.get(0));
            /*for(SocketInfo otherClient : clientList)
            {
                
            }*/
            if(message.getProtocol().compareTo("MSG")==0)
            {
                for(SocketInfo otherClient : clientList)
                {
                    if(otherClient.getSocket()!=s)//prevent sending message to client that sent the message
                    {   
                        ObjectOutputStream toNewClient = new ObjectOutputStream(otherClient.getSocket().getOutputStream());
                        ArrayList<String> messageContent = new ArrayList<>();
                        toNewClient.writeObject(new Message("MSG",messageContent));
                        toNewClient.flush();
                    }
                }
            }
            }
            
            
    }

}