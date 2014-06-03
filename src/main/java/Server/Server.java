package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import Adapter.MainAdapter;
import Communication.CommunicationView;
import Model.ModelOne;

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
                thread.setDaemon(true);
                thread.start();

            } catch (IOException e) {
                throw new RuntimeException("Error while waiting for connection", e);
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
        	throw new RuntimeException("Cannot make BufferedReader from connected player", e);
        }
    }
    public void run(){
        String order;
        while(true){
            try {
                order=in.readLine();
                String txt[] = order.split("~");
                System.err.println("GOT SERVER ORDER: "+order);
                if(p.inGame && txt[0].toLowerCase().equals("start")) {
                    server.tables.get(p.tableNumber).cv.start(p.inGameId);
                }
                else if(p.inGame && !txt[0].toLowerCase().equals("removeplayerfromtable") && !txt[0].toLowerCase().equals("start")){
                    System.out.println("Tables: "+server.tables.get(p.tableNumber));
                    System.out.println("I sit in "+p.tableNumber);
                    server.tables.get(p.tableNumber).cv.parse(order, p.socket);
                }
                else{
                    server.parse(order,p.socket, p);
                }
            } catch (IOException e) {
                System.err.println("Cannot read massage from playing player");
                //player disconnected - removing him from game
                if(p.inGame) server.removePlayerFromTable(p,p.tableNumber);
                System.err.println("Lost connection with player "+p.socket);
                break;
            }
        }
    }
}
class PlayerOnline{
	public int image;
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
            throw new RuntimeException("Cannot make PrintWriter for socket: "+socket.toString(), e);
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
class SenderToLobby implements Runnable{
    Server server;
    public SenderToLobby(Server server){
        this.server=server;
    }
    @Override
    public void run() {
        while(true) {
            server.updateLobby();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                //just go on
                System.err.println("Interupped exception ale probujemy dalej");

            }
        }
    }
}
public class Server {

    private Thread mainListener,lobbySender;
    public int port=1229;
    public ArrayList<PlayerOnline> connected =new ArrayList<PlayerOnline>();
    public ArrayList<Table> tables = new ArrayList<Table>();
    
    public Server(int port) {
        try {
            mainListener = new Thread(new Listener(port,this)) ;
        } catch (IOException e) {
        	throw new RuntimeException("Cannot make server at port" + port, e);
        }
        mainListener.start();
        lobbySender=new Thread(new SenderToLobby(this));
        lobbySender.start();
    }

    synchronized void parse(String order, Socket socket, PlayerOnline p){
        System.err.println("GOT SERVERRRRRRR ORDER: " + order);
        String txt[] = order.split("~");
        txt[0]=txt[0].toLowerCase();
        switch(txt[0]){
        	case "addtable":
        		addTable(p);
        		break;
        	case "addplayertotable":
        		addPlayerToTable(p,new Integer(txt[1]));
        		break;
        	case "removetable":
        		removeTable(new Integer(txt[1]));
        		break;
        	case "removeplayerfromtable":
        		removePlayerFromTable(p,p.tableNumber);
        		break;
        	case "setplayername":
        		System.err.println(order);
                setPlayerName(p,txt[1]);
                break;
        	case "setplayerimage":
        		System.err.println(order);
        		setPlayerImage(p,new Integer(txt[1]));
                break;
             default:
                throw new RuntimeException("Unknown operation type "+txt);
        }
    }

    private void setPlayerImage(PlayerOnline p, int image) {
        System.err.println("TESTING:"+ p.name);
		p.image = image;
	}

	public void sendToLobby(String txt) {
        //System.out.println("SEND LOBBY OUT" + txt);
        for(PlayerOnline player: connected) {
            if(!player.inGame){
                player.writer.println(txt);
            }
        }
    }
    public void guiAddTable(){
        sendToLobby("guiaddtable~"+(tables.size()));
    }
    public void guiClearTableList(){
        System.out.println("Kaze wyczyscic stoly");
        sendToLobby("guicleartablelist");
    }
    public void guiAddTable(Integer id, boolean started){
        sendToLobby("guiaddtable~"+(id)+"~"+started);
    }
    public void guiRemoveTable(int tableIndex){
        sendToLobby("guiremovetable~"+tableIndex);
    }
    public void updateNumberOfPlayers(int tableIndex, int number){
        sendToLobby("updatenumberofplayers~"+tableIndex+"~"+number);
    }

    public void addTable(PlayerOnline host){
        guiAddTable();
        Table table = new Table(host);
        tables.add(table);
        addPlayerToTable(host, tables.indexOf(table));

    }
    public void updateLobby(){
        guiClearTableList();
        try{
        for(Table t:tables){
            if(t==null) continue;
            guiAddTable(tables.indexOf(t),t.mo.isStarted());
        }
        for(Table t:tables){
            if(t==null) continue;
            updateNumberOfPlayers(tables.indexOf(t),t.players.size());
        }} catch(Exception e){

            e.printStackTrace();
            System.err.println("Error with table detected, waiting for new information about tables");
        }
    }
    public void addPlayerToTable(PlayerOnline p, int tableIndex){
        p.inGame=true;
        p.tableNumber=tableIndex;
        p.inGameId = tables.get(tableIndex).mo.addPlayer(p.toString(), p.image);
        tables.get(tableIndex).players.add(p);
        try {
            tables.get(tableIndex).cv.addOut(p.writer, p.inGameId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setPlayerId(p,p.inGameId);
        updateNumberOfPlayers(tableIndex, tables.get(tableIndex).players.size());
    }
    public void setPlayerId(PlayerOnline p, int inGameId){
        p.writer.println("setplayerid~"+inGameId);
    }
    public void setPlayerName(PlayerOnline p, String txt){
        p.name=txt;
        System.err.println("TESTING SET PLAYER NAME:"+ p.name);
    }
    public void removeTable(int tableIndex){
        for(PlayerOnline p : tables.get(tableIndex).players ){
            p.inGame = false;
        }
        tables.set(tableIndex, null);
        guiRemoveTable(tableIndex);
    }
    public void  removePlayerFromTable(PlayerOnline p, int tableIndex){
        tables.get(p.tableNumber).mo.removePlayer(p.inGameId);
        if(p == tables.get(tableIndex).host) removeTable(tableIndex);
        else {
            p.inGame = false;
            tables.get(tableIndex).players.remove(p);
            try {
                tables.get(tableIndex).cv.removeOut(p.writer, p.inGameId);
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}