import java.io.*;
import java.net.*;
import java.util.*;
 
public class EchoClient {
    public static void main(String[] args) throws IOException {
 
        Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
 
        try {
            echoSocket = new Socket("localhost", 6666);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(
            echoSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: localhost.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for "
            + "the connection to: localhost.");
            System.exit(1);
        }
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String userInput;
 
        System.out.println("Type Equation: ");

        while(true) {
            userInput = stdIn.readLine();
            if(userInput.equalsIgnoreCase("EXIT"))
                break;
            out.println(userInput);
            System.out.println("Result of Equation: " + in.readLine());
        }
 
        out.close();
        in.close();
        stdIn.close();
        echoSocket.close();
    }
}