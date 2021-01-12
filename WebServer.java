 /**
  * Daniel Lott
  * 1/11/2021
  * Web Server
  */

/**
 * Write a simple web server that recognizes only the GET request (without the Host: request parameter and blank line). When a client connects to your server and sends a command, 
 * such as GET filename HTTP/1.1, then return a header HTTP/1.1 200 OK
    followed by a blank line and all lines in the file. If the file doesn’t exist, return 404 Not Found instead.
    Your server should listen to port 8080. Test your web server by starting up your web browser and loading a page, such as localhost:8080/c:\cs1\myfile.html.
 */

import java.io.*;
import java.net.*;
public class WebServer 
{
    public static void main(String[] args) throws IOException
    {
        final int SBAP_PORT = 8080;
        ServerSocket server = new ServerSocket(SBAP_PORT);
        System.out.println("Waiting for clients to connect...");
        while (true)
        {
            Socket s = server.accept();
            System.out.println("Client connected.");
            HandleRequest service = new HandleRequest(s);
            Thread t = new Thread(service);
            t.start();
        }

    }

    
}
