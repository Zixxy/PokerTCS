package SocketTries;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Arytmetyk on 2014-05-14.
 */
public class RunServer {
    public static void main(String[] args) throws IOException {
        /*Utworzenie na podanym porcie socketa serwera.*/
        ServerSocket serverSocket = new ServerSocket(2048);

/*Oczekiwanie na połączenie i zaakceptowanie*/
        Socket clientSocket = serverSocket.accept();

/*Utworzenie strumieni wejściowego i wyjściowego*/
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

/*Wysłanie komunikatu*/
        out.println("Czo to za komunikat?");
        System.out.println(in.readLine());

/*Oczekiwanie i odebranie komunikatu*/
        String input = in.readLine();

/*Zamknięcie strumieniu i socketów*/
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
//
    }
}
