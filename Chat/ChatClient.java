package Chat;
import java.io.*;
import java.util.Scanner;

import java.net.*;

public class ChatClient {

    static final String host = "localhost";
    static final int port = 5567;
    public static void main(String[] args) throws IOException
    {
        Scanner readInput = new Scanner(System.in);
        //Ask Username before connecting
        System.out.print("Enter a username: ");
        String username = readInput.nextLine();
        System.out.println("\nConnecting...");


        try(Socket s = new Socket(host,port))
        {
            Scanner fromHost = new Scanner(s.getInputStream());
            PrintWriter toHost = new PrintWriter(s.getOutputStream());
            toHost.println("LOGIN "+username);
            toHost.flush();
            System.out.println("Connected!");
            ChatMessageHandlerClient service = new ChatMessageHandlerClient(s);
            Thread t = new Thread(service);
            t.start();
            boolean logOut=false;
            while(!logOut)
            {
                
                Scanner splitter;

                String input = readInput.nextLine();
                if(input!="!logout")
                {
                    toHost.println("CHAT "+input);
                    toHost.flush();
                    System.out.println("message sent");
                }
                else
                {
                    toHost.println("LOGOUT ");
                    toHost.flush();
                    logOut=true;
                }
                
            }
        }
    }
}
