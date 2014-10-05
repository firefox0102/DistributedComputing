import java.io.*;
import java.net.*;

class Client
{
    public static void main(String argv[]) throws Exception
    {
        String clientType;
        String port;
        String response;

        System.out.println("Is this Client a Sender? (y/n)");
        clientType = System.console().readLine();
        //1. Get the port that this will run on
        System.out.println("Input the PORT for this Client");
        port = System.console().readLine();
        ServerSocket clientListenerPort = new ServerSocket(Integer.parseInt(port));

        if(clientType.equals("y")) {
            String message;
            String receiverName;

            System.out.println("PORT ::" + port);
            //2. Get the name of the receiving client
            System.out.println("Input the Recieving Clients name");
            receiverName = System.console().readLine();
            System.out.println("clientName :: " + receiverName);

            //3. Get the message that will be sent to the receiving client
            System.out.println("Input the message");
            //BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
            //message = inFromUser.readLine();
            message = System.console().readLine();
            System.out.println("message :: " + message);

            Socket CSRouterSocket = new Socket("localhost", 6768);
            DataOutputStream outToCSRouter = new DataOutputStream(CSRouterSocket.getOutputStream());
            System.out.println("SENDING TO CSROUTER");
            outToCSRouter.writeBytes(receiverName + "\n");
            outToCSRouter.writeBytes(message + "\n");
            //BufferedReader inFromCSRouter = new BufferedReader(new InputStreamReader(CSRouterSocket.getInputStream()));
            //response = inFromCSRouter.readLine();
            //System.out.println("RESPONSE RECIEVED!" + response);
            //inFromCSRouter.close();
            outToCSRouter.close();

            //System.out.println("FROM SERVER:: " + response);
            CSRouterSocket.close();
        }
        while(true) {
            Socket connectionSocket = clientListenerPort.accept();
            BufferedReader inFromClient =
                    new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            response = inFromClient.readLine();
            System.out.println("Received:: " + response);
        }
    }
}