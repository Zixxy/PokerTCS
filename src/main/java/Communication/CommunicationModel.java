package main.java.Communication;

import main.java.Adapter.AdapterInterface;
import main.java.Model.Deck;
import main.java.Model.ModelInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by arytmetyk on 2014-05-14.
 */
public class CommunicationModel implements ModelInterface {
    Socket socket;
    String ip="192.168.0.4";
    int port=50000;
    private AdapterInterface adapter;
    private PrintWriter out ;
    Thread listen;
    public CommunicationModel(AdapterInterface adapter) throws IOException {
        this.adapter=adapter;
        this.socket=new Socket(this.ip,this.port);
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.listen = new Thread(new Listener(this.socket,this));
        this.listen.start();
}
    class Listener implements Runnable{
        Socket socket;
        BufferedReader in;
        CommunicationModel communicationModel;

        public Listener(Socket socket, CommunicationModel communicationModel) throws IOException {
            this.socket=socket;
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.communicationModel=communicationModel;

        }


        @Override
        public void run() {
            while(true){
                try {
                    communicationModel.parse(in.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void parse(String txt){
        String[] tab = txt.split("~");

        if(tab[0].toLowerCase().equals("addplayer")){
            adapter.addPlayer(tab[1],new Integer(tab[2]));
        }
        if(tab[0].toLowerCase().equals("removeplayer")){
            adapter.removePlayer(new Integer(tab[1]));
        }
        if(tab[0].toLowerCase().equals("updateplayercach")){
            adapter.updatePlayerLinedCash(new Integer(tab[1]),new Integer(tab[2]));
        }
        if(tab[0].toLowerCase().equals("setpot")){
            adapter.setPot(new Integer(tab[1]));
        }
        if(tab[0].toLowerCase().equals("addthreecards")){
            Deck.Card arr[]= new Deck.Card[3];
            arr[0]=Deck.getSpecifiedCard(tab[1]);
            arr[1]=Deck.getSpecifiedCard(tab[2]);
            arr[2]=Deck.getSpecifiedCard(tab[3]);

            adapter.addThreeCards(arr);
        }
        if(tab[0].toLowerCase().equals("addonecard")){
            adapter.addOneCard(Deck.getSpecifiedCard(tab[1]));
        }
        if(tab[0].toLowerCase().equals("cleartable")){
            adapter.clearTable();
        }
        if(tab[0].toLowerCase().equals("sendmessage")){
            adapter.sendMessage(tab[1]);
        }
        if(tab[0].toLowerCase().equals("startnewround")){
            adapter.startNewRound();
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
    public void addPlayer(String name) {
        out.println("addPlayer~"+name);
    }

    @Override
    public void removePlayer(int playerId) {
        out.println("removePlayer~"+playerId);
    }

    @Override
    public void start() {
        out.println("start~");
    }

    @Override
    public void setAnte(int arg) {
        out.println("setAnte~"+arg);
    }

    @Override
    public Deck.Card[] getHandCards(int playerId) {
        return new Deck.Card[0];
    }

    @Override
    public int getActualStage() {
        return 0;
    }

    @Override
    public void fold(int playerId) {
        out.println("fold~"+playerId);
    }

    @Override
    public void check(int playerId) {
        out.println("check~"+playerId);
    }

    @Override
    public void raise(int playerId, int amount) {
        out.println("raise~"+playerId+"~"+amount);
    }

    @Override
    public void resign(int playerId) {
        out.println("resign~"+playerId);
    }
}