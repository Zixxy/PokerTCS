package SocketTries;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Arytmetyk on 2014-05-14.
 */
public class RunClient {

    public static void main(String[] args) throws IOException {

            Socket serverSocket = new Socket(InetAddress.getByName("192.168.0.104"), 2048);



/*Utworzenie strumieni wejściowego i wyjściowego*/
        PrintWriter out = new PrintWriter(serverSocket.getOutputStream(),true);
        BufferedReader in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));

/*Wysłanie komunikatu*/
        out.println("Czo to za komunikat?");

/*Oczekiwanie i odebranie komunikatu*/
        String input = in.readLine();

/*Zamknięcie strumieniu i socketów*/
        out.close();
        in.close();
        serverSocket.close();

    }
    }





