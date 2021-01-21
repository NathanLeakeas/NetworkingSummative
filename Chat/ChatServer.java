package Chat;
import java.io.*;
import java.util.Scanner;
import java.util.*;
import java.util.Collections;


import java.net.*;

public class ChatServer {
    public static List<SocketInfo> sockList = Collections.synchronizedList(new ArrayList());
    
    public static void main(String[] args) throws IOException
    {
        ServerSocket servSock = new ServerSocket(5567);
        while(true)
        {
            //try()
            //{
                Socket s = servSock.accept();
                SocketInfo newConnection = new SocketInfo(s);
                sockList.add(newConnection);
                System.out.println("Client connected from "+s.getInetAddress());
                ChatMessageHandlerServer service = new ChatMessageHandlerServer(newConnection,sockList);
                Thread t = new Thread(service);
                t.start();

            //}
        }
    }
}
