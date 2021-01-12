import java.net.*;
import java.util.Date;
import java.util.Scanner;
import java.io.*;
public class DateServer {

    public static void main(String[] args) throws IOException
    {
        ServerSocket server = new ServerSocket(5580);
        while(true)
        {
            //try()
            //{
                Socket s = server.accept();
                System.out.println("Client connected from "+s.getInetAddress());
                PrintWriter toConnection = new PrintWriter(s.getOutputStream());
                toConnection.print(new Date().toString());
                toConnection.flush();
                s.close(); 
                
            //}
        }
    }
}
