import java.io.*;
import java.lang.Integer;
import java.net.*;
import java.net.Socket;

public class CSRouter
{
    public static void main(String args[]) throws Exception
    {
        String requestedMediaName;
        String responseString;
        System.out.println("BEGINNING POLL");
        ServerSocket listenerPort = new ServerSocket(6768);
        while(true) {
            Socket connectionSocket = listenerPort.accept();//Recieves the message incoming from the client
            System.out.println("COMMUNICATION RECIEVED");
            BufferedReader inFromClient =
                    new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            requestedMediaName = inFromClient.readLine();
            int port = Integer.parseInt(inFromClient.readLine());
            System.out.println("PORT :: " + port);
            connectionSocket.close();
            System.out.println("RECIEVED: " + requestedMediaName);
            //Go to the lookup Server table
            //1. Connect to the Server
            System.out.println("DEBUG 1");
            Socket serverSocket = new Socket("localhost", 6769);

            //2. Send request for the port
            System.out.println("DEBUG 2");
            DataOutputStream outToServer = new DataOutputStream(serverSocket.getOutputStream());
            System.out.println("DEBUG 2.5");
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));

            //3. Receive the port
            System.out.println("DEBUG 3");
            outToServer.writeBytes(requestedMediaName + "\n");
            System.out.println("DEBUG 3.5");
            responseString = inFromServer.readLine();
            serverSocket.close();

            //4. Send the data to client
            System.out.println("DEBUG 4");
            Socket sendingSocket = new Socket("localhost", port);
            DataOutputStream responseToClient = new DataOutputStream(sendingSocket.getOutputStream());
            responseToClient.writeBytes(responseString + "\n");

            responseToClient.close();
        }
    }
}
