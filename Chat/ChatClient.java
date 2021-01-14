package Chat;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import java.net.*;

public class ChatClient {

    static final String host = "localhost";
    static final int port = 5566;
    public static void main(String[] args) throws IOException
    {
        Scanner readInput = new Scanner(System.in);
        //Ask Username before connecting
        System.out.print("Enter a username: ");
        String username = readInput.nextLine();
        System.out.println("\nConnecting...");


        try(Socket s = new Socket(host,port))
        {
            //Scanner fromHost = new Scanner(s.getInputStream());
            //PrintWriter toHost = new PrintWriter(s.getOutputStream());
            ObjectOutputStream toHost = new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream fromHost = new ObjectInputStream(s.getInputStream());
            ArrayList<String> loginArgs = new ArrayList<>();
            loginArgs.add(username);
            Message loginMessage = new Message("LOGIN",loginArgs);
            toHost.writeObject(loginMessage);
            
            System.out.println("Connected!");
            ChatMessageHandlerClient service = new ChatMessageHandlerClient(s);
            Thread t = new Thread(service);
            t.start();
            while(true)
            {
                
                //Scanner splitter;

                String input = readInput.nextLine();
                //splitter = new Scanner(input);
                ArrayList<String> message = new ArrayList<>();
                message.add(input);
                toHost.writeObject(new Message("MSG",message));
                toHost.flush();
            }
        }
    }
}
