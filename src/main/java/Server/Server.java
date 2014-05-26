package main.java.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import main.java.Adapter.MainAdapter;
import main.java.Communication.CommunicationView;
import main.java.Model.ModelOne;

class Listener implements Runnable{
    ServerSocket socket;
    Server server;
    public Listener(int port, Server server) throws IOException {
        socket=new ServerSocket(port);
        this.server=server;
    }
    @Override
    public void run() {
        Thread thread;
        PlayerOnline p;
        while(true){
            try {
                p=new PlayerOnline(socket.accept());
                server.connected.add(p);
                thread = new Thread(new PlayerListener(p,server));
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
    Server server;
    public PlayerListener(PlayerOnline p, Server server){
        this.p=p;
        this.server=server;
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
                server.tables.get(p.tableNumber).cv.parse(order, p.socket);}
                else{
                    server.parse(order,p.socket, p);
                }
            } catch (IOException e) {
                System.err.println("Cannot read massage from playing player");
                //player disconnected - removing him from game
                server.removePlayerFromTable(p,p.tableNumber);
                break;
            }
        }
    }
}
class PlayerOnline{
    public volatile boolean inGame;
    public String name;
    public PrintWriter writer;
    public int inGameId=-1;
    public PlayerOnline(Socket socket) throws IOException{
        this.inGame=false;
        this.socket=socket;
        try {
            writer = new PrintWriter(socket.getOutputStream(), true);
        }
        catch (IOException e) {
            System.err.println("Cannot make PrintWriter for socket: "+socket.toString());
            throw e;
        }
    }
    public Socket socket;
    public int tableNumber;
    @Override
    public String toString(){
        return name;
    }
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

    private Thread mainListener;
    public int port=1229;
    public ArrayList<PlayerOnline> connected =new ArrayList<PlayerOnline>();
    public ArrayList<Table> tables = new ArrayList<Table>();

    synchronized void parse(String order, Socket socket, PlayerOnline p){//public because Server uses it
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
        else if(txt[0].equals("setplayername~")) {
            setPlayerName(p,txt[1]);
        }
    }

    public void sendToLobby(String txt) {
        for(PlayerOnline player: connected) {
            if(!player.inGame){
                player.writer.println(txt);
            }
        }
    }
    public void guiAddTable(){
        sendToLobby("guiaddtable~");
    }
    public void guiRemoveTable(int tableIndex){
        sendToLobby("updatenumberofplayers~"+tableIndex);
    }
    public void updateNumberOfPlayers(int tableIndex, int number){
        sendToLobby("updatenumberofplayers~"+tableIndex+"~"+number);
    }

    public void addTable(PlayerOnline host){
        Table table = new Table(host);
        tables.add(table);
        addPlayerToTable(host, tables.indexOf(table));
        guiAddTable();
    }
    public void addPlayerToTable(PlayerOnline p, int tableIndex){
        p.inGame=true;
        p.tableNumber=tableIndex;
        p.inGameId = tables.get(tableIndex).mo.addPlayer(p.toString());
        tables.get(tableIndex).players.add(p);
        try {
            tables.get(tableIndex).cv.addOut(p.writer, p.inGameId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        updateNumberOfPlayers(tableIndex, tables.get(tableIndex).players.size());
    }
    public void setPlayerName(PlayerOnline p, String txt){
        p.name=txt;
    }
    public void removeTable(int tableIndex){
        for(PlayerOnline p : tables.get(tableIndex).players ){
            p.inGame = false;
        }
        tables.set(tableIndex, null);
        guiRemoveTable(tableIndex);
    }
    public void  removePlayerFromTable(PlayerOnline p, int tableIndex){
        if(p == tables.get(tableIndex).host) removeTable(tableIndex);
        else {
            p.inGame = false;
            tables.get(tableIndex).players.remove(p);
        }

        try {
            tables.get(tableIndex).cv.removeOut(p.writer, p.inGameId);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        updateNumberOfPlayers(tableIndex, tables.get(tableIndex).players.size());
    }
    public Server(int port) {
        try {
            mainListener = new Thread(new Listener(port,this)) ;
        } catch (IOException e) {
            System.err.println("Cannot make server at port" + port);
        }
        mainListener.start();
    }

}
