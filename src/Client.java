import javax.sound.sampled.AudioInputStream;
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.net.URL;
import javax.swing.*;
import javax.sound.sampled.*;

class Client extends JFrame{
    public static void main(String argv[]) throws Exception {
        new Client();
    }

    public Client(){
        String port;
        String response;
        Clip audioClip;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Sound Clip");
        this.setSize(400, 300);
        this.setVisible(true);

        try {
            String message;
            String audioFileName;

            //1. Get the port that this will run on
            System.out.println("Input the PORT for this Client");
            port = System.console().readLine();
            ServerSocket clientListenerPort = new ServerSocket(Integer.parseInt(port));

            //2. Are they looking for an audio or a video file
            System.out.println("Audio or video file? (a/v)");
            message = System.console().readLine();

            if(message.equals("a")) {
                //3. Get the name of the receiving client
                System.out.println("Input the name of the audio file you are requesting");
                audioFileName = System.console().readLine();

                Socket CSRouterSocket = new Socket("localhost", 6768);
                DataOutputStream outToCSRouter = new DataOutputStream(CSRouterSocket.getOutputStream());
                System.out.println("SENDING TO CSROUTER");
                outToCSRouter.writeBytes(audioFileName + "\n");
                outToCSRouter.writeBytes(port + "\n");
                outToCSRouter.close();

                //System.out.println("FROM SERVER:: " + response);
                CSRouterSocket.close();
                while (true) {
                    Socket connectionSocket = clientListenerPort.accept();
                    BufferedReader inFromClient =
                            new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                    response = inFromClient.readLine();

                    AudioInputStream audioIn;
                    URL audioURL = new File(response).toURI().toURL();
                    audioIn = AudioSystem.getAudioInputStream(audioURL);
                    audioClip = AudioSystem.getClip();
                    audioClip.open(audioIn);
                    audioClip.start();

                    System.out.println("Received:: " + response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}