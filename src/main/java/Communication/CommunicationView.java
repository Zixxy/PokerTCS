package main.java.Communication;

import main.java.Adapter.AdapterInterface;
import main.java.Model.Deck;
import main.java.View.ViewInterface;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by dakurels on 2014-05-14.
 */
public class CommunicationView  implements ViewInterface{

    private Thread clientsListener;
    private Collection<Thread> massagesListeners;
    private ServerSocket server;
    private Collection<Socket> clients;
    private Collection<PrintWriter> outs;
    private AdapterInterface adapter;


    private class ClientsListener implements Runnable {
        private CommunicationView communicationView;
        private ServerSocket server;

        public ClientsListener(CommunicationView communicationView, ServerSocket server) {
            this.communicationView = communicationView;
            this.server = server;
        }

        @Override
        public void run() {
            while(true) {
                try {
                    communicationView.add(server.accept());
                }
                catch (IOException e) {}
            }
        }
    }
    private class MassageListener implements Runnable {
        private CommunicationView view;
        private Socket socket;
        private BufferedReader in;
        public MassageListener(CommunicationView view, Socket socket) throws IOException {
            this.view = view;
            this.socket = socket;
            this.in =  new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }

        @Override
        public void run() {
            while (true) {
                try {
                    this.view.parse(this.in.readLine());
                } catch (IOException e) {
                    System.err.println("Cannot read message");
                    break;
                }
            }
        }
    }

    public CommunicationView(int port, AdapterInterface adapter) throws IOException{
        try {
            this.server = new ServerSocket(port);
        }
        catch (IOException e){
            System.err.println("Cannot make server at port: " + port);
            throw e;
        }
        this.adapter = adapter;
        clients = new ArrayList<Socket>();
        outs = new ArrayList<PrintWriter>();
        massagesListeners = new ArrayList<Thread>();
        clientsListener = new Thread(new ClientsListener(this, this.server));
        clientsListener.start();
    }

    private synchronized void add(Socket x) {
        clients.add(x);
        try {
            outs.add(new PrintWriter(x.getOutputStream(), true));
            massagesListeners.add(new Thread(new MassageListener(this, x)));
        }
        catch (Exception e){}

    }

    private synchronized void parse(String order){
        String txt[] = order.split("~");
        txt[0]=txt[0].toLowerCase();
        /*
        public void fold(int playerId);
        public void check(int playerId);
        public void raise(int playerId, String amount);
        public void resign(int playerId);
         */
        if(txt[0].equals("fold")) {
            adapter.fold(Integer.valueOf(txt[1]));
        }
        else if(txt[0].equals("check")) {
            adapter.check(Integer.valueOf(txt[1]));
        }
        else if(txt[0].equals("raise")) {
            adapter.raise(Integer.valueOf(txt[1]), txt[2]);
        }
        else if(txt[0].equals("resign")) {
            adapter.resign(Integer.valueOf(txt[1]));
        }
    }

    private void sendCommand(String txt) {
        synchronized(CommunicationView.class) {
            for (PrintWriter print : outs)
                print.println(txt);
        }
    }


    @Override
    public void addPlayer(String name, int id) {
        this.sendCommand("addPlayer~"+ name + "~" + id);
    }

    @Override
    public void removePlayer(int id) {
        this.sendCommand("removePlayer" + "~" + id);
    }

    @Override
    public void updatePlayerCash(int id, int cash) {
        this.sendCommand("updatePlayerCash~"+ id + "~" + cash);
    }

    @Override
    public void addThreeCardsOnTable(Deck.Card firstCard, Deck.Card secondCard, Deck.Card thirdCard) {
        this.sendCommand("addThreeCards~" + firstCard.toString() + "~" + secondCard.toString() + "~" + thirdCard.toString());

    }

    @Override
    public void addOneCard(Deck.Card card) {
        this.sendCommand("addOneCard~" + card.toString());
    }

    @Override
    public void clearTable() {
        this.sendCommand("clearTable");

    }

    @Override
    public void updatePlayerHand(Deck.Card firstCard, Deck.Card secondCard) {
        //TODO
    }

    @Override
    public void updatePlayerLinedCash(int id, int cash) {
        this.sendCommand("updatePlayerLinedCash~" + id);
    }

    @Override
    public void removePlayersLinedCash(int id) {
        this.sendCommand("removePlayersLinedCash~0");
    }

    @Override
    public void sendMessage(String text) {
        this.sendCommand("sendMessage~" + text);
    }

    @Override
    public void startNewRound() {
        this.sendCommand("startNewRound");
    }

    @Override
    public void setPot(int cash) {
        this.sendCommand("setPot~" + cash);
    }
}
