package arsw.demo.ejercicio3;

import java.net.*;
import java.io.*;

public class SquareServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(35000);
        Socket clientSocket = serverSocket.accept();

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            int numero = Integer.parseInt(inputLine);
            int cuadrado = numero * numero;
            out.println("Resultado = " + cuadrado);
        }

        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}