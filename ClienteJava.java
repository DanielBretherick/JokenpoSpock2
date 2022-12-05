import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ClienteJava {
    private static String host = "127.0.0.1";
    private static Integer port = 40000;
    private static List<List<String>> plays = new ArrayList<>();
    private static int roundCounter;
    private static String actions[] = {"Rock", "Paper", "Scissor", "Lizard", "Spock", "Draw"};


    public static int heuristica(){
        Random out = new Random();
        return 3;
    }
    public static int checkWin(int a,int b){
        if (a==b)
            return 5;
        if( a == 0 || b == 1)
            return b;
        if( a == 0 && b == 2)
            return a;
        if( a == 0 && b == 3)
            return a;
        if( a == 0 && b == 4)
            return b;
        if( a == 1 && b == 2)
                return b;
        if( a == 1 && b == 3)
                return b;
        if( a == 1 && b == 4)
                return a;
        if( a == 2 && b == 3)
                return b;
        if( a == 2 && b == 4)
                return b;
        if( a == 3 && b == 4)
                return b;
        if( b == 0 && a == 2)
                return b;
        if( b == 0 && a == 3)
                return b;
        if( b == 0 && a == 4)
                return a;
        if( b == 1 && a == 2)
                return a;
        if( b == 1 && a == 3)
                return a;
        if( b == 1 && a == 4)
                return b;
        if( b == 2 && a == 3)
                return a;
        if( b == 2 && a == 4)
                return a;
        if( b == 3 && a == 4)
                return a;
        else
            return -1;
    }

    public static void main(String args[]) throws Exception {
        roundCounter = 0;
        Socket clientSocket = new Socket(ClienteJava.host, ClienteJava.port);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        for(int i=0; i < 15; i++) {
            List<String> sup = new ArrayList<>();
            String response;
            String input = String.valueOf(heuristica());
            System.out.println(input);
            outToServer.writeBytes(input);
            response = inFromServer.readLine();
            System.out.println("Response from server: " + response);
            sup.add(String.valueOf(roundCounter));
            sup.add(response);
            sup.add(input);
            sup.add(actions[checkWin(Integer.valueOf(response), Integer.valueOf(input))]);
            plays.add(sup);
            System.out.println(plays);
            roundCounter++;
        }
        clientSocket.close();

    }
}