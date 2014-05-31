package Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by dakurels on 2014-05-14.
 */
public class Config {
    private String ip;
    private int port;
    private int userId;

    public Config(String fileName) {
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader("config"));
        } catch (IOException e) {
            throw new RuntimeException("Cannot open config file", e);
        }
        boolean foundIP = false, foundUserId=false;
        String actualLine;
        try {
            while (null != (actualLine = br.readLine())) {
                if (actualLine.split(":")[0].equals("IP")) {
                    foundIP = true;
                    ip = actualLine.split(":")[1];
                    port = Integer.valueOf(actualLine.split(":")[2]);
                }
                else if(actualLine.split(":")[0].equals("ID")) {
                    this.userId = Integer.valueOf(actualLine.split(":")[1]);
                    foundUserId = true;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot read from config file", e);
        }
        if (!foundIP) {
        	try {
				br.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
            throw new RuntimeException("Cannot find IP configuration");
        }
        if (!foundUserId) {
        	try {
				br.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
            throw new RuntimeException("Cannot find user's id configuration");
        }
        try {
			br.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
    }

    public String getIP() {
        return ip;
    }

    public int getPort(){
        return port;
    }

    public int getUserId() {
        return userId;
    }
}
