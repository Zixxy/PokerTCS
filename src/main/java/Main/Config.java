package main.java.Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.IllegalFormatCodePointException;

/**
 * Created by dakurels on 2014-05-14.
 */
public class Config {
    private String ip;
    private int port;

    public Config(String fileName) {
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader("config"));
        } catch (IOException e) {
            System.err.println("Cannot open config file");
            throw new IllegalStateException();
        }
        boolean foundIP = false;
        String actualLine;
        try {
            while (null != (actualLine = br.readLine())) {
                if (actualLine.split(":")[0].equals("IP")) {
                    foundIP = true;
                    ip = actualLine.split(":")[1];
                    port = Integer.valueOf(actualLine.split(":")[2]);
                }
            }
        } catch (IOException e) {
            System.err.println("Cannot read from config file");
            throw new IllegalStateException();
        }
        if (!foundIP) {
            System.err.println("Cannot find IP configuration");
            throw new IllegalStateException();
        }
    }

    public String getIP() {
        return ip;
    }

    public int getPort(){
        return port;
    }
}
