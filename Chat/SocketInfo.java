package Chat;
import java.net.*;

public class SocketInfo {
    Socket s;
    Account uname;
    public SocketInfo(Socket socket,Account username)
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

    public Account getUsername()
    {
        return uname;
    }
}
