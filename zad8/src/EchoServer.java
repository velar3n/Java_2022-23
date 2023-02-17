import java.io.*;
import java.net.*;
import java.util.*;
 
public class EchoServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(6666);
        } catch (IOException e) {
            System.out.println("Could not listen on port: 6666");
            System.exit(-1);
        }
 
        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.out.println("Accept failed: 6666");
            System.exit(-1);
        }
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
 
        while(true) {
            double result;
            String input = in.readLine();

            if(input.equalsIgnoreCase("EXIT"))
                break;

            StringTokenizer equation = new StringTokenizer(input);
            if(equation.countTokens() != 3) {
                out.println("Input error");
            } else {
                double number1 = Double.parseDouble(equation.nextToken());
                String operation = equation.nextToken();
                double number2 = Double.parseDouble(equation.nextToken());
                
                if (operation.equals("+")) {
                    result = number1 + number2;
                    out.println(result);
                }
                else if (operation.equals("-")) {
                    result = number1 - number2;
                    out.println(result);
                }
                else if (operation.equals("*")) {
                    result = number1 * number2;
                    out.println(result);
                }
                else if (operation.equals("/")) {
                    if(number2 == 0) {
                        out.println("Division by 0, please change input");
                    } else {
                        result = number1 / number2;
                        out.println(result);
                    }
                } else {
                    out.println("Input error");
                }
            }
        }
        out.println("Closing connection...");
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
 
    }
}