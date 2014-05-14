package SocketTries;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by dakurels on 2014-05-14.
 */
public class ChatServer {
    static int port = 50000;
    public static void main(String args[]) throws Exception{
        ServerSocket server = new ServerSocket(port);
        Socket client = server.accept();
        Socket client2 = server.accept();
        PrintWriter out = new PrintWriter(client2.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(client2.getInputStream()));
        Scanner consoleIn = new Scanner(System.in);
        while(true){
            out.println(consoleIn.nextLine());
            System.out.println(in.readLine());
        }
    }
}
