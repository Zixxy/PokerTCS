package SocketTries;

/* Server.java */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    private static final int PORT = 50000;
    static boolean flaga = true;

    private static ServerSocket serverSocket;
    private static Socket clientSocket;

    public static void main(String[] args) throws IOException
    {
        serverSocket = null;
        try
        {
            serverSocket = new ServerSocket(PORT);
        }
        catch(IOException e)
        {
            System.err.println("Could not listen on port: "+PORT);
            System.exit(1);
        }

        System.out.print("Wating for connection...");

        Thread t = new Thread(new Runnable()
        {
            public void run()
            {
                try
                {
                    while(flaga)
                    {
                        System.out.print(".");
                        Thread.sleep(1000);
                    }
                }
                catch(InterruptedException ie)
                {
                    //
                }

                System.out.println("\nClient connected on port "+PORT);
            }
        });
        t.start();

        clientSocket = null;
        try
        {
            clientSocket = serverSocket.accept();
            flaga = false;
        }
        catch(IOException e)
        {
            System.err.println("Accept failed.");
            t.interrupt();
            System.exit(1);
        }

        final PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
        final BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        t = new Thread(new Runnable()
        {
            public void run()
            {
                try
                {
                    Thread.sleep(5000);

                    while(true)
                    {
                        out.println("Ping");
                        System.out.println(System.currentTimeMillis()+" Ping sent");

                        String input = in.readLine();

                        if(input.equals("Pong"))
                        {
                            System.out.println(System.currentTimeMillis()+" Pong received");
                        }
                        else
                        {
                            System.out.println(System.currentTimeMillis()+" Wrong answer");

                            out.close();
                            in.close();
                            clientSocket.close();
                            serverSocket.close();
                            break;
                        }


                        Thread.sleep(5000);
                    }
                }
                catch(Exception e)
                {
                    System.err.println(System.currentTimeMillis()+" Unexpected Error");
                }
            }
        });
        t.start();
    }
}
