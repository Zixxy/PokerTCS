package Communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import Adapter.AdapterInterface;
import Main.Run;
import Model.Deck;
import Model.Deck.*;
import Model.ModelInterface;
import View.MainWindow;

/**
 * Created by arytmetyk on 2014-05-14.
 */

public class CommunicationModel implements ModelInterface {
    Socket socket;
    String ip;
    int port;
    private AdapterInterface adapter;
    private PrintWriter out ;
    BufferedReader in;
    Thread listen;
    public CommunicationModel(AdapterInterface adapter, String ip, int port) throws IOException {
        this.adapter=adapter;
        this.ip=ip;
        this.port=port;
        this.socket=new Socket(this.ip,this.port);
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        this.listen = new Thread(new Listener(this.socket,this,in));
        this.listen.start();


}
    class Listener implements Runnable{
        Socket socket;
        BufferedReader in2;
        CommunicationModel communicationModel;

        public Listener(Socket socket, CommunicationModel communicationModel, BufferedReader in2) throws IOException {
            this.socket=socket;
            this.in2=in2;
            this.communicationModel=communicationModel;

        }


        @Override
        public void run() {
            while(true){
                try {
                    synchronized(CommunicationModel.class) {
                        communicationModel.parse(in2.readLine());
                    }
                } catch (IOException e) {
                    Run.mainWindow.showMainMenu();
                    break;
                    //Return to the main window should be here
                }
            }
        }
    }
    private void parse(String txt){
    	System.out.println("Communication Model got order"+txt);
        String tab[] = txt.split("~");
        tab[0] = tab[0].toLowerCase();
        
        switch(tab[0]){
        	case "addplayer":
        		adapter.addPlayer(tab[1],new Integer(tab[2]), new Integer(tab[3]));
        		break;
        	case "removeplayer":
        		adapter.removePlayer(new Integer(tab[1]));
        		break;
        	case "updateplayerlinedcash":
        		adapter.updatePlayerLinedCash(new Integer(tab[1]),new Integer(tab[2]));
        		break;
        	case "updateplayercash":
        		adapter.updatePlayerCash(new Integer(tab[1]), new Integer(tab[2]));
        		break;
        	case "updateresignedplayer":
        		adapter.updateResignPlayer(new Integer(tab[1]));
        		break;
        	case "updateactualplayer":
        		adapter.updateActualPlayer(new Integer(tab[1]));
        		break;
        	case "updateplayerhand":
        		Deck.Card arr[] = new Deck.Card[2];
                arr[0]=Deck.getSpecifiedCard(tab[2]);
                arr[1]=Deck.getSpecifiedCard(tab[3]);
                adapter.updatePlayerHand(new Integer(tab[1]), arr);
                break;
        	case "setpot":
        		adapter.setPot(new Integer(tab[1]));
        		break;
        	case "addthreecards":
        		Deck.Card ar[]= new Deck.Card[3];
                ar[0]=Deck.getSpecifiedCard(tab[1]);
                ar[1]=Deck.getSpecifiedCard(tab[2]);
                ar[2]=Deck.getSpecifiedCard(tab[3]);
                adapter.addThreeCards(ar);
                break;
        	case "addonecard":
        		adapter.addOneCard(Deck.getSpecifiedCard(tab[1]));
        		break;
        	case "cleartable":
        		adapter.clearTable();
        		break;
        	case "sendmessage":
        		adapter.sendMessage(tab[1]);
        		break;
        	case "startnewround":
        		adapter.startNewRound();
        		break;
        	case "setlastmove":
            	adapter.setLastMove(Integer.parseInt(tab[1]), Integer.parseInt(tab[2]));
            	break;
        	case "showcards":
            	adapter.showCards(Integer.parseInt(tab[1]), Integer.parseInt(tab[2]), Integer.parseInt(tab[3]));
            	break;
        	case "guiaddtable":
                adapter.guiAddTable(new Integer(tab[1]),tab[2]);
                break;
        	case "guicleartablelist":
        		adapter.guiClearTableList();
                break;
        	case "guiremovetable":
        		adapter.guiRemoveTable(new Integer(tab[1]));
        		break;
        	case "updatenumberofplayers":
                adapter.updateNumberOfPlayers(new Integer(tab[1]), new Integer(tab[2]));
                break;
        	case "setplayerid":
                adapter.setPlayerId(new Integer(tab[1]));
                break;
        	default:
                throw new RuntimeException("Unknown operation type: " + txt);
        }
    }
    @Override
    public boolean isStarted() {
        return false;
    }

    @Override
    public int getLimit() {
        return 0;
    }

    @Override
    public int getLimitVariant() {
        return 0;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isInGame(int playerId) {
        return false;
    }

    @Override
    public int getSmallBlind() {
        return 0;
    }

    @Override
    public void setSmallBlind(int small) {
        out.println("setSmallBlind~"+small);
    }

    @Override
    public int getBigBlind() {
        return 0;
    }

    @Override
    public void setBigBlind(int big) {
        out.println("setBigBlind~"+big);
    }

    @Override
    public int getActualPlayer() {
        return 0;
    }

    @Override
    public int getMoney(int playerId) {
        return 0;
    }

    @Override
    public void setStartedAmount(int amount) {
        out.println("setStartedAmount~"+amount);
    }

    @Override
    public int addPlayer(String name, int image) {
        out.println("addPlayer~"+name+"~"+image);
        return 7;//7 to bardzo duzo
    }

    @Override
    public void setPlayerName(String name) {
        out.println("setPlayerName~"+name);
    }
	@Override
	public void setPlayerImage(int image) {
		out.println("setPlayerImage~"+image);
		
	}

    @Override
    public void removePlayer(int playerId) {
        out.println("removePlayer~"+playerId);
    }
//
    @Override
    public void start() {
        out.println("start~");
    }

    @Override
    public void start(int id) {
        //user cannot send this
    }

    @Override
    public void setAnte(int arg) {
        out.println("setAnte~"+arg);
    }


    @Override
    public int getActualStage() {
        return 0;
    }

    @Override
    public void fold(int playerId) {
        System.out.println("Ja chce foldowac");
        out.println("fold~"+playerId);
    }

    @Override
    public void check(int playerId) {
    	System.out.println("Ja chce sprawdzac");
        out.println("check~"+playerId);
    }

    @Override
    public void raise(int playerId, int amount) {
    	System.out.println("Ja chce podbijac");
        out.println("raise~"+playerId+"~"+amount);
    }

    @Override
    public void resign(int playerId) {
        out.println("resign~"+playerId);
    }
    
    private int getCardColorValue(Card.Color color) {
        switch (color) {
            case CLUBS: return 0;
            case DIAMONDS: return 1;
            case HEARTS: return 2;
            case SPADES: return 3;
            default: throw new RuntimeException("Unknown card color");
        }
    }
    
	@Override
	public void showPlayerCards(int playerId, Card[] cards) {
		System.out.println("jestem w communicationModel");
		Card c = cards[0];
		int cardId = c.getValue().id;
    	int cardColor = getCardColorValue(c.getColor());
    	
    	Integer firstCardNumber = 20*cardColor + cardId;
    	
    	c = cards[1];
		cardId = c.getValue().id;
    	cardColor = getCardColorValue(c.getColor());
    	
    	Integer secondCardNumber = 20*cardColor + cardId;
    	
    	out.println("showCards~" + playerId + "~" + firstCardNumber + "~" + secondCardNumber);
	}

    @Override
    public void addTable() {
        out.println("addtable~");
    }

    @Override
    public void removePlayerFromTable() {
        out.println("removeplayerfromtable");
    }

    @Override
    public void addPlayerToTable(int tableIndex) {
        out.println("addplayertotable~"+tableIndex);
    }
	@Override
	public void sendOutMessage(String text) {
        // UNUSED
		out.println("sendoutmessage~"+text);
	}

}
