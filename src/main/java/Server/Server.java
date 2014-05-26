package main.java.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import main.java.Adapter.MainAdapter;
import main.java.Communication.CommunicationView;
import main.java.Model.ModelOne;

class Listener implements Runnable{
    ServerSocket socket;
    public Listener(int port) throws IOException {
        socket=new ServerSocket(port);
    }
    @Override
    public void run() {
        Thread thread;
        PlayerOnline p;
        while(true){
            try {
                p=new PlayerOnline(socket.accept());
                Server.connected.add(p);
                thread = new Thread(new PlayerListener(p));
                thread.start();
            } catch (IOException e) {
                System.err.println("Error while waiting for connection");
            }
        }
    }
}
class PlayerListener implements Runnable{
    PlayerOnline p;
    BufferedReader in;
    public PlayerListener(PlayerOnline p){
        this.p=p;
        try {
            this.in =  new BufferedReader(new InputStreamReader(p.socket.getInputStream()));
        } catch (IOException e) {
            System.err.println("Cannot make BufferedReader from connected player");
        }
    }
    public void run(){
        String order;
        while(true){
            try {
                order=in.readLine();
                String txt[] = order.split("~");
                if(p.inGame && !txt[0].equals("removeplayerfromtable".toLowerCase())){
                Server.tables.get(p.tableNumber).cv.parse(order, p.socket);}
                else{
                    Server.parse(order,p.socket, p);
                }
            } catch (IOException e) {
                System.err.println("Cannot read massage from playing player");
                //player disconnected - removing him from game
                Server.removePlayerFromTable(p,p.tableNumber);
                break;
            }
        }
    }
}
class PlayerOnline{
    public volatile boolean inGame;
    public PlayerOnline(Socket socket){
        this.inGame=false;
        this.socket=socket;
    }
    public Socket socket;
    public int tableNumber;
}
class Table{
    public CommunicationView cv;
    public ModelOne mo;
    public MainAdapter ma;
    public PlayerOnline host;
    public ArrayList<PlayerOnline> players;
    public Table(PlayerOnline p){
        this.players  = new ArrayList<PlayerOnline>();
        this.host=p;
        ma = new MainAdapter();
        this.cv =new CommunicationView(ma);
        this.mo=new ModelOne(ma);
        ma.addModel(mo);
        ma.addView(cv);
    }
}
public class Server {

    private static Thread mainListener;
    public static int port=1229;
    public static ArrayList<PlayerOnline> connected =new ArrayList<PlayerOnline>();
    public static ArrayList<Table> tables = new ArrayList<Table>();

    synchronized static void parse(String order, Socket socket, PlayerOnline p){//public because Server uses it
        System.err.println("GOT SERVER ORDER: " + order);
        String txt[] = order.split("~");
        txt[0]=txt[0].toLowerCase();
        if(txt[0].equals("addtable")) {
            addTable(p);
        }
        else if(txt[0].equals("addplayertotable")) {
            addPlayerToTable(p,new Integer(txt[1]));
        }
        if(txt[0].equals("removetable")) {
            removeTable(new Integer(txt[1]));
        }
        else if(txt[0].equals("removeplayerfromtable")) {
            removePlayerFromTable(p,new Integer(txt[1]));
        }
    }
    public static void addTable(PlayerOnline host){
        Table table = new Table(host);
        tables.add(table);
        addPlayerToTable(host, tables.indexOf(table));
    }
    public static void addPlayerToTable(PlayerOnline p, int tableIndex){
        p.inGame=true;
        p.tableNumber=tableIndex;
        tables.get(tableIndex).mo.addPlayer(p.toString());
        tables.get(tableIndex).players.add(p);
        try {
            tables.get(tableIndex).cv.addOut(p.socket);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void removeTable(int tableIndex){
        for(PlayerOnline p : tables.get(tableIndex).players ){
            p.inGame = false;
        }
        tables.set(tableIndex, null);
    }
    public static void  removePlayerFromTable(PlayerOnline p, int tableIndex){
        if(p == tables.get(tableIndex).host) removeTable(tableIndex);
        else {
            p.inGame = false;
            tables.get(tableIndex).players.remove(p);
        }
        try {
            tables.get(tableIndex).cv.removeOut(p.socket);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        try {
            mainListener = new Thread(new Listener(port)) ;
        } catch (IOException e) {
            System.err.println("Cannot make server at port" + port);
        }
        mainListener.start();
    }
}
