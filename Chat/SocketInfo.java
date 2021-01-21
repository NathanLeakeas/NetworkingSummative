package Chat;
import java.net.*;

public class SocketInfo {
    Socket s;
    String uname;
    public SocketInfo(Socket socket,String username)
    {   
        s=socket;
        uname = username;
    }
    public SocketInfo(Socket socket)
    {
        s=socket;
        uname=null;
    }

    public Socket getSocket()
    {
        return s;
    }

    public String getUsername()
    {
        return uname;
    }

    public void setUsername(String username)
    {
        uname = username;
    }
}
