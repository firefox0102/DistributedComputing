import java.io.*;
import java.net.*;

public class CSRouter
{
    public static void main(String args[]) throws Exception
    {
        String inputString;
        String responseString;
        System.out.println("BEGINNING POLL");
        ServerSocket listenerPort = new ServerSocket(6768);
        while(true) {
            Socket connectionSocket = listenerPort.accept();//Recieves the message incoming from the client
            System.out.println("COMMUNICATION RECIEVED");
            //Recieves the message incoming from the client
            BufferedReader inFromClient =
                    new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            //Reads the string fromt the client-buffer-reader
            inputString = inFromClient.readLine();
            System.out.println("RECIEVED: " + inputString);

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
            outToServer.writeBytes(inputString + "\n");
            System.out.println("DEBUG 3.5");
            responseString = inFromServer.readLine();
            int receivingClientPort = Integer.parseInt(responseString);
            System.out.println("RESPONSE PORT ::" + receivingClientPort);
            //4. Send the data receiving client
            System.out.println("DEBUG 4");
            Socket receivingClientSocket = new Socket ("localhost", receivingClientPort);
            DataOutputStream outToReceivingClient = new DataOutputStream(receivingClientSocket.getOutputStream());
            outToReceivingClient.writeBytes(inputString + "\n");

            outToReceivingClient.close();
            //outToSendingClient.writeInt(receivingClientPort);
        }
    }
}
