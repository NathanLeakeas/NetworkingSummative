package Chat;

import java.io.*;
import java.util.*;
import java.net.Socket;

public class Account 
{
    String user;
    /**
     * creates new account by writing it to the account file
     */
    public void newAccount(String username)//, String password)
    {
        user = username;
        //String pass = password;
        
        //create new file if it doesn't exist
        try
        {
            File acct = new File("accounts.txt");
            if (!acct.exists())
            {
                acct.createNewFile();
            }
            //write the account to the file
        FileWriter fw = new FileWriter(acct.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(user);
        bw.close();
        System.out.println("Account Created");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    /**
     * Checks to see if the account provided is a valid account.  If account doesn't exist, gives the option to create an account for the user.
     * @return boolean isValid, determines if the account is valid or not.
     */
    public boolean checkAccount (String username/*,String password*/) throws FileNotFoundException
    {
        String user = username;
        //String pass = password;
        Scanner filescan = new Scanner(new File("accounts.txt"));
        Boolean isValid = false;
        while (!isValid && filescan.hasNext())
        {
            if (user==filescan.next())
            {
                isValid = true;
                return isValid;
            }
        }
        return false;
        /*This code is supposed to be used server-side only: no point including this portion
        Scanner scan = new Scanner(System.in);
        if (isValid==false)
        {
            System.out.println("That account is not valid.  Would you like to create a new account with the username " + user+"? (Y/N) ");
            String input = scan.nextLine();
            if (input =="Y")
            {
                this.newAccount(user);
            }
            else
                return isValid;
        }*/
    }
    
    private String getUName()
    {
        return user;
    }

    public Socket getSocket(List<SocketInfo> socketlist)
    {
        List<SocketInfo> socklist = socketlist;
        for (SocketInfo s : socklist)
        {
            if (s.getUsername().getUName()==this.getUName())
                {
                    return s.getSocket();
                }
        }
        return new Socket();//placeholder
    }
}