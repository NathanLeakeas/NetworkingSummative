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
        ServerSocket servSock = new ServerSocket(5566);
        while(true)
        {
            //try()
            //{
                Socket s = servSock.accept();
                System.out.println("Client connected from "+s.getInetAddress());
                SocketInfo currentSocket = new SocketInfo(s);
                sockList.add(currentSocket);
                ChatMessageHandlerServer service = new ChatMessageHandlerServer(s,sockList);
                Thread t = new Thread(service);
                t.start();

            //}
        }
    }
}
