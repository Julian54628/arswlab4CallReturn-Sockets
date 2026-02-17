package arsw.demo.ejercicio3;

import java.net.*;
import java.io.*;

public class FunctionServer {
    private static String operacion = "cos";

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(35000);
        System.out.println("Servidor de funciones iniciado en puerto 35000");

        while (true) {
            Socket clientSocket = serverSocket.accept();

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if (inputLine.startsWith("fun:")) {
                    operacion = inputLine.substring(4);
                    out.println("La operación cambio a " + operacion);
                } else {
                    try {
                        double num = Double.parseDouble(inputLine);
                        double res = 0;
                        if (operacion.equals("sin")) res = Math.sin(num);
                        if (operacion.equals("cos")) res = Math.cos(num);
                        if (operacion.equals("tan")) res = Math.tan(num);
                        out.println("Resultado " + res);
                    } catch (NumberFormatException e) {
                        out.println("Número inválido");
                    }
                }
            }

            out.close();
            in.close();
            clientSocket.close();
        }
    }
}