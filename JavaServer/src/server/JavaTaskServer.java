package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class JavaTaskServer extends Thread
{
    private Socket           connectionSocket;
    private BufferedReader   inFromClient;
    private DataOutputStream outToClient;
    
    private String clientQuestion;
    private String serverAnswer;
    
    private boolean runTask;
    
    public JavaTaskServer(Socket s)
    {
        try
        {
            connectionSocket = s;
            inFromClient     = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            outToClient      = new DataOutputStream(connectionSocket.getOutputStream());
            runTask          = true;
        }
        catch (IOException e)
        {
            System.err.println("");
        }
    }
    
    public boolean isRunning()
    {
        return runTask;
    }
    
    public void stopTask()
    {
        runTask = false;
    }
    
    @Override
    public void run()
    {
        while (runTask)
        {
            try
            {
                // TO DO: update so that the server answers correctly
                clientQuestion = inFromClient.readLine();
                serverAnswer = "";
                outToClient.writeBytes(serverAnswer);
            }
            catch (IOException e)
            {
                System.err.println("");
            }
        }
        try
        {
            connectionSocket.close();
            inFromClient.close();
            outToClient.close();
        }
        catch (IOException e)
        {
            System.err.println("");
        }
    }
}
