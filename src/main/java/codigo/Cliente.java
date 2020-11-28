/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.io.*;
import java.net.*;

/**
 *
 * @author Eduardo Ruiz
 */
public class Cliente {
    public static void main(String[] args) throws IOException {
        String sentence;
        BufferedReader inFromUser;
        Socket clientSocket;
        DataOutputStream outToServer;
        BufferedReader inFromServer;
        
        inFromUser = new BufferedReader(new InputStreamReader(System.in));
        clientSocket = new Socket("localhost", 56789);
        outToServer = new DataOutputStream(clientSocket.getOutputStream());
        inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        
        sentence = inFromUser.readLine();
        outToServer.writeBytes(sentence + "\n");
        System.out.println(inFromServer.readLine());
        clientSocket.close();
    }
}
