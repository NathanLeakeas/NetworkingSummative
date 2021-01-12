import java.io.*;
import java.util.Scanner;
import java.net.*;

public class HandleRequest implements Runnable
{
    Socket s;
    Scanner in;
    PrintWriter out;
    public HandleRequest(Socket sock) throws IOException
    {
        s=sock;
    }

    public void run()
    {
        try
        {
            in = new Scanner(s.getInputStream());
            out = new PrintWriter(s.getOutputStream());
            HandleHTTPRequests();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void HandleHTTPRequests() throws IOException
    {
        while(true)
        {
            if(!in.hasNext()){return;}
            String request = in.nextLine();
            String[] requestArray = request.split(" ");
            if(requestArray[0].compareTo("GET")==0)
            {
                String contents = fetchResource(requestArray[1].substring(1));
                if(contents==null)
                {
                    String response = "<html><body><h1>404 File Not Found</h1></body></html>";
                    out.println("HTTP/1.1 404 Not Found");
                    out.println("Content-Type: text/html\nContent-Length: "+response.length()+"\n\n");
                    out.println(response);
                    out.flush();
                }
                else
                {
                    String response = "HTTP/1.1 200 OK\nContent-Type: text/html\nContent-Length: "+contents.length()+"\n\n";
                    response +=contents;
                    out.print(response+"\n");
                    out.flush();
                }
                
            }
            
        }
    }

    public String fetchResource(String resourceIdentifier)
    {
        File resource = new File(resourceIdentifier);
        
        try
        {
            Scanner fileReader = new Scanner(resource);
            String contents = "";
            while(fileReader.hasNext())
            {
                contents+=fileReader.nextLine()+"\r\n";
            }
            fileReader.close(); 
            return contents;
        }
        catch(FileNotFoundException e)
        {
            return null;
        }

    }
}