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
import main.java.View.CommandLine;
import main.java.View.TableView;
import main.java.View.TableViewInterface;
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
                if(p.inGame){
                Server.tables.get(p.tableNumber).cm.parse(order, p.socket);}
                else{
                    Server.parse(order,p.socket);
                }
            } catch (IOException e) {
                System.err.println("Cannot read massage from playing player");
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
    public CommunicationView cm;
    public ModelOne mo;
    public MainAdapter ma;
    public PlayerOnline host;
    public ArrayList<PlayerOnline> players;
    public Table(PlayerOnline p){
        this.players  = new ArrayList<PlayerOnline>();
        this.host=p;
        ma = new MainAdapter();
        this.cm=new CommunicationView(ma);
        this.mo=new ModelOne(ma);
        ma.addModel(mo);
        ma.addView(cm);
    }
}
public class Server {

    private static Thread mainListener;
    public static int port;
    public static ArrayList<PlayerOnline> connected =new ArrayList<PlayerOnline>();
    public static ArrayList<Table> tables = new ArrayList<Table>();
    public static void parse(String string, Socket socket){

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
