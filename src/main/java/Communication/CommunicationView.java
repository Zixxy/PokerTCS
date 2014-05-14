package main.java.Communication;

import main.java.Model.Deck;
import main.java.View.ViewInterface;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by dakurels on 2014-05-14.
 */
public class CommunicationView  implements ViewInterface{

    private Thread clientsListener;
    private Collection<Thread> massagesListener;
    private ServerSocket server;
    private Collection<Socket> clients;
    private Collection<PrintWriter> outs;


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

    public CommunicationView(int port) throws IOException{
        try {
            this.server = new ServerSocket(port);
        }
        catch (IOException e){
            System.err.println("Cannot make server at port: " + port);
            throw e;
        }

        clients = new ArrayList<Socket>();
        outs = new ArrayList<PrintWriter>();
        massagesListener = new ArrayList<Thread>();
        clientsListener = new Thread(new ClientsListener(this, this.server));
        clientsListener.start();
    }

    private synchronized void parse(String order){
        //TODO
    }

    private synchronized void  add(Socket x) {
        clients.add(x);
        try {
            outs.add(new PrintWriter(x.getOutputStream(), true));
        }
        catch (Exception e){}

    }



    //TODO
    @Override
    public void addPlayer(String name, int id) {

    }

    @Override
    public void removePlayer(int id) {

    }

    @Override
    public void updatePlayerCash(int id, int cash) {

    }

    @Override
    public void addThreeCardsOnTable(Deck.Card firstCard, Deck.Card secondCard, Deck.Card thirdCard) {

    }

    @Override
    public void addOneCard(Deck.Card card) {

    }

    @Override
    public void clearTable() {

    }

    @Override
    public void updatePlayerHand(Deck.Card firstCard, Deck.Card secondCard) {

    }

    @Override
    public void updatePlayerLinedCash(int id, int cash) {

    }

    @Override
    public void removePlayersLinedCash(int id) {

    }

    @Override
    public void sendMessage(String text) {

    }

    @Override
    public void startNewRound() {

    }

    @Override
    public void setPot(int cash) {

    }
}
