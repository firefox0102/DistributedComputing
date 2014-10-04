import java.io.*;
import java.net.*;
import java.util.TreeMap;

class Server
{
    public static void main(String args[]) throws Exception
    {
        String inputString;
        String responseString;
        ServerSocket listenerPort = new ServerSocket(6769);
        TreeMap<String, Integer> ServerTable = new TreeMap<String, Integer>();
        ServerTable.put("client1", 6767);
        ServerTable.put("client2", 6790);
        ServerTable.put("client3", 6799);

        while(true) {
            System.out.println("DEBUG 1");
            Socket connectionSocket = listenerPort.accept();//Recieves the message incoming from the client
           //Recieves the message incoming from the client
            System.out.println("DEBUG 2");
            BufferedReader inFromClient =
                    new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            System.out.println("DEBUG 3");
            inputString = inFromClient.readLine();
            System.out.println("Received: " + inputString);
            responseString = ServerTable.get(inputString).toString();
            System.out.println("RESPONSE:: " + responseString);
            outToClient.writeBytes(responseString + "\n");
        }
    }
}