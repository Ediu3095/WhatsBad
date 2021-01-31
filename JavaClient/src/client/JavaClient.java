package client;

import java.io.*;
import java.net.*;
import java.util.Arrays;

public class JavaClient
{
    private String           username;
    private byte[]           addr;
    private int              port;
    
    private Socket           clientSocket;
    private DataOutputStream outToServer;
    private BufferedReader   inFromServer;
    private String           data;
    
    public JavaClient()
    {
        username = null;
    }
    
    public int connect(byte[] addr, int port)
    {
        try
        {
            InetAddress address = InetAddress.getByAddress(addr);
            clientSocket = new Socket(address, port);
            return 0;
        }
        catch(UnknownHostException e)
        {
            System.err.println("The host " + Arrays.toString(addr) + ":" + port + " is unknown");
            return 1;
        }
        catch(IOException e)
        {
            System.err.println("An input output exception was caught while creating the socket");
            return 2;
        }
    }
    
    public int speakToServer(String sentence)
    {
        try
        {
            outToServer.writeBytes(sentence);
            return 0;
        }
        catch (IOException e)
        {
            System.err.println("An output exception was caught while speaking to server");
            return 1;
        }
    }
    
    public int listenFromServer(String sentence)
    {
        try
        {
            data = "";
            while (inFromServer.ready())
            {
                data += inFromServer.readLine();
            }
            return 0;
        }
        catch (IOException e)
        {
            System.err.println("An input exception was caught while listening from server");
            return 1;
        }
    }
    
    public int disconnect()
    {
        try
        {
            clientSocket.close();
            return 0;
        }
        catch(IOException e)
        {
            System.err.println("The socket could not be closed");
            return 1;
        }
    }
}
