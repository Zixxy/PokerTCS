package SocketTries;

/**
 * Created by Arytmetyk on 2014-05-14.
 */
/* Client.java */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client
{
    private static final int PORT = 50000;
    private static final String HOST = "localhost";

    public static void main(String[] args) throws IOException
    {
        Socket socket = null;

        try
        {
            socket = new Socket(HOST, PORT);
        }
        catch(Exception e)
        {
            System.err.println("Could not connect to "+HOST+":"+PORT);
            System.exit(1);
        }

        final PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
        final BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        Thread t = new Thread(new Runnable()
        {
            public void run()
            {
                long start = System.currentTimeMillis();

                while (true)
                {
                    try
                    {
                        String input = in.readLine();

                        if (input != null)
                        {
                            System.out.println(System.currentTimeMillis() + " Server: " + input);
                        }

                        if (input.equals("Ping"))
                        {
                            if(System.currentTimeMillis()-start>30000)
                            {
                                out.println("Pon g");
                                System.out.println(System.currentTimeMillis() + " Client: Pon g");
                                break;
                            }

                            out.println("Pong");
                            System.out.println(System.currentTimeMillis() + " Client: Pong");
                        }
                    }
                    catch (IOException ioe)
                    {
                        //
                    }
                }
            }
        });
        t.start();

        out.close();
        in.close();
        socket.close();
    }
}
