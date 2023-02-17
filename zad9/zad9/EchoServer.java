/* Jednoczesna obsługa wielu klientów - wątki (ThreadPool)
    Podawanie wszystkich liczb z przedziału podanego jako commandline argument */

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
 
public class EchoServer implements Runnable {

    private static final ExecutorService exec = Executors.newFixedThreadPool(5);

    public static void acceptConnections() {
        System.out.println("Waiting for connection ...");
        try {
            ServerSocket serverSocket = new ServerSocket(6666);
            Socket clientSocket = null;

            while (true) {
                clientSocket = serverSocket.accept();
                System.out.println("Connected to client " + clientSocket.getInetAddress().getAddress());
                connection(clientSocket);
            }
        } catch (IOException e) {
            System.out.println("Accept failed: 6666");
        }
    }

    public static void connection(Socket connect) {
        exec.execute(new EchoServer(connect));
    }

    Socket connentedClientSocket;

    public EchoServer(Socket socket) {
        connentedClientSocket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader in;
            try(PrintWriter out = new PrintWriter(connentedClientSocket.getOutputStream(),true)) {
                in = new BufferedReader(new InputStreamReader(connentedClientSocket.getInputStream()));
                String input;

                while((input = in.readLine()) != null) {
                    String result = " ";

                    StringTokenizer range = new StringTokenizer(input);
                    if(range.countTokens() != 2) {
                        out.println("Input error");
                    } else {
                        try {
                            int number1 = Integer.parseInt(range.nextToken());
                            int number2 = Integer.parseInt(range.nextToken());
                            if(number1 > number2) {
                            out.println("Input error");
                            } else {
                                String output;

                                for(int i = number1; i <= number2; i++) {
                                    if(checkPrime(i)) {
                                        result = result + Integer.toString(i) + " ";
                                    }
                                }
                                out.println(result);
                            }
                        } catch (NumberFormatException e) {
                            out.println("Input error");
                        }
                    }
                }
            }
            System.out.println("Disconnecting client " + connentedClientSocket.getInetAddress().getAddress());
            in.close();
            connentedClientSocket.close();
        } catch (IOException e) {}
    }


    public static void main(String[] args) throws IOException {
        acceptConnections();
    }


    static boolean checkPrime (int number) {
        if(number < 2) {
           return false;
        }

        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0)
                return false;
        }

        return true;
    }
}