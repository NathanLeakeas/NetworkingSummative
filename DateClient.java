import java.net.*;
import java.io.*;
import java.util.Scanner;

public class DateClient {

    public static void main(String[] args) throws IOException
    {
        try(Socket s = new Socket("localhost",5580))
        {
            Scanner fromHost = new Scanner(s.getInputStream());
            while(fromHost.hasNext())
            {
                String output = fromHost.nextLine();
                System.out.println(output); 
            }
        }
    }

}
