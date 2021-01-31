package server;

import java.io.*;
import java.net.*;

public class JavaWelcomeServer extends Thread
{
    private final JavaTaskServer[] taskServers;
    
    private ServerSocket welcomeSocket;
    private int          port;
    
    private boolean runTask;
    
    public JavaWelcomeServer()
    {
        taskServers = new JavaTaskServer[100];
        runTask = true;
    }
    
    public int connect(int p)
    {
        try
        {
            port = p;
            welcomeSocket = new ServerSocket(port);
            return 0;
        }
        catch (IOException e)
        {
            System.err.println("An input output exception was caught when creating socket with port:" + port);
            return 1;
        }
    }
    
    public JavaTaskServer[] getTasks()
    {
        return taskServers;
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
        int i = 0;
        while (runTask) {
            try
            {
                if (taskServers[i].isRunning())
                {
                    i = (i + 1) % 100;
                }
                else
                {
                    taskServers[i] = new JavaTaskServer(welcomeSocket.accept());
                }
            }
            catch (IOException e)
            {
                System.err.println("New connection could not be accepted");
            }
        }
        try
        {
            welcomeSocket.close();
        }
        catch (IOException e)
        {
            System.err.println("Connection could not be closed");
        }
    }
}