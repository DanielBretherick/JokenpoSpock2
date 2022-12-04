import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ClienteJava {
    private static String host = "127.0.0.1";
    private static Integer port = 40000;
    private static List<List<String>> play = new ArrayList<>();

    public static int heuristica(){
        Random out = new Random();
        return out.nextInt(5);
    }

    public static 

    public static void main(String args[]) throws Exception {
        Socket clientSocket = new Socket(ClienteJava.host, ClienteJava.port);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        for(int i=0; i < 15; i++) {
            String response;
            String input = String.valueOf(heuristica());
            System.out.println(input);
            outToServer.writeBytes(input);
            response = inFromServer.readLine();

            // Display respones
            System.out.println("Response from server: " + response);
        }
        // Close socket
        clientSocket.close();

    }
}