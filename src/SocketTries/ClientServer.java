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
public class ClientServer {
    static int port = 50000;
    static String ip = "192.168.0.104";
    public static void main(String args[]) throws Exception{
        Socket server = new Socket(ip, port);
        PrintWriter out = new PrintWriter(server.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
        Scanner consoleIn = new Scanner(System.in);
        while(true){
            System.out.println(in.readLine());
            out.println(consoleIn.nextLine());
        }
    }
}
