package Chat;
import java.net.*;
import java.util.Scanner;
import java.io.*;
import java.util.List;


public class ChatMessageHandlerServer implements Runnable
{
    Scanner fromHost;
    PrintWriter toHost;
    SocketInfo s;
    List<SocketInfo> clientList;
    public ChatMessageHandlerServer(SocketInfo sock, List<SocketInfo> sockList)
    {
        s = sock;
        clientList = sockList;
    }

    public void run()
    {
            try
            {
                fromHost = new Scanner(s.getSocket().getInputStream());
                toHost = new PrintWriter(s.getSocket().getOutputStream());
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
            String message = fromHost.nextLine();
            if(getProtocol(message).compareTo("CHAT")==0)
            {
                System.out.println(s.getUsername()+":"+stripProtocol(message));
                for(SocketInfo otherClientInfo : clientList)
                {
                    if(otherClientInfo!=s)//prevent sending message to client that sent the message
                    {
                        PrintWriter toNewClient = new PrintWriter(otherClientInfo.getSocket().getOutputStream());
                        toNewClient.println(s.getUsername()+":"+stripProtocol(message));

                        toNewClient.flush();
                    }
                }
            }
            else if(getProtocol(message).compareTo("LOGIN")==0)
            {
                s.setUsername(stripProtocol(message));
            }
            else if(getProtocol(message).compareTo("LOGOUT")==0)
            {
                s.getSocket().close();
            }
            
        }
    }

    /**
     * determines if the message is a LOGIN, CHAT, or LOGOUT protocol
     */
    public String getProtocol(String message)//LOGIN, CHAT, LOGOUT
    {
        
        if(message.substring(0,4).toLowerCase().equals("chat"))
        {
            return "CHAT";
        }
        else if (message.substring(0,5).toLowerCase().equals("login"))
        {
            return "LOGIN";
        }
        else if (message.substring(0,6).toLowerCase().equals("logout"))
        {
            return "LOGOUT";
        }
        return " ";
        
    }

    public String stripProtocol(String message)
    {
        return message.substring(getProtocol(message).length()+1);
    }

}