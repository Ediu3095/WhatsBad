package client;

import java.io.*;
import java.net.*;

public class JavaClient
{
    private String           username;
    private Socket           clientSocket;
    private DataOutputStream outToServer;
    private BufferedReader   inFromServer;
    
    public JavaClient()
    {
        username = null;
    }
    
    public void connect()
    {
        Socket clientSocket = new Socket("localhost", 56789);
    }
    
    public void TellServer(String sentence)
    {
        outToServer.writeBytes(sentence + "\n"); 
    }
    
    public static void main(String[] args) throws IOException {
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        
        System.out.println(inFromServer.readLine());
        clientSocket.close();
    }
}
