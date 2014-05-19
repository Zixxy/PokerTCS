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
    private int waiting;

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
                    System.out.println("WAITING FOR CONNECTION");
                    communicationView.add(server.accept());
                    System.out.println("STOPPED");
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
                    this.view.parse(this.in.readLine(), socket);
                } catch (IOException e) {
                    System.err.println("Cannot read message");
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
        System.out.println("Created server at port : " + port);
        this.adapter = adapter;
        clients = new ArrayList<Socket>();
        outs = new ArrayList<PrintWriter>();
        massagesListeners = new ArrayList<Thread>();
        clientsListener = new Thread(new ClientsListener(this, this.server));
        clientsListener.start();
    }

    private synchronized void add(Socket x) {
        System.out.println("CONNECT: " + x.getInetAddress().toString());
        clients.add(x);
        try {
            outs.add(new PrintWriter(x.getOutputStream(), true));
            Thread newUser = new Thread(new MassageListener(this, x));
            newUser.start();
            massagesListeners.add(newUser);
        }
        catch (Exception e){}

    }

    private synchronized void parse(String order, Socket socket){
        System.err.println("GOT ORDER: " + order);
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
        else if(txt[0].equals("usercards")) {
            //System.out.println("ASKED: " + txt[1]);
            //this.sendCards(socket, Integer.valueOf(txt[1]));
        }
    }

    private void sendCommand(String txt) {
        System.err.println("SEND OUT: "+txt);
        for (PrintWriter print : outs)
            print.println(txt);
    }
  /*  private synchronized void sendCards(Socket socket, int id) {
        try {
            (new PrintWriter(socket.getOutputStream(), true)).println("yourCards~" + adapter.getHandCards(id)[0].toString() + "~" + adapter.getHandCards(id)[1]);
            System.out.println("yourCards~" + adapter.getHandCards(id)[0].toString() + "~" + adapter.getHandCards(id)[1]);
            waiting--;
        }
        catch (IOException e) {
            System.out.println("Cannot send cards, FATAL ERROR");
            System.err.println("Cannot send cards, FATAL ERROR");
        }
    }
*/
    @Override
    public void addPlayer(String name, int id) {
        synchronized(CommunicationView.class) {
            this.sendCommand("addPlayer~" + name + "~" + id);
        }
    }

    @Override
    public void removePlayer(int id) {
        synchronized(CommunicationView.class) {
            this.sendCommand("removePlayer" + "~" + id);
        }
    }

    @Override
    public void updatePlayerCash(int id, int cash) {
            synchronized(CommunicationView.class) {
                this.sendCommand("updatePlayerCash~" + id + "~" + cash);
            }
    }

    @Override
    public void addThreeCardsOnTable(Deck.Card firstCard, Deck.Card secondCard, Deck.Card thirdCard) {
        synchronized(CommunicationView.class) {
            this.sendCommand("addThreeCards~" + firstCard.toString() + "~" + secondCard.toString() + "~" + thirdCard.toString());
        }

    }

    @Override
    public void addOneCard(Deck.Card card) {
        synchronized(CommunicationView.class) {
            this.sendCommand("addOneCard~" + card.toString());
        }
    }

    @Override
    public void clearTable() {
        synchronized(CommunicationView.class) {
            this.sendCommand("clearTable");
        }

    }

    @Override
    public void updatePlayerHand(int playerId, Deck.Card firstCard, Deck.Card secondCard) {
        synchronized(CommunicationView.class) {
            this.sendCommand("updatePlayerHand~" + playerId + "~" + firstCard + "~" + secondCard );
        }
    }

    @Override
    public void updatePlayerLinedCash(int id, int cash) {
        synchronized(CommunicationView.class) {
            this.sendCommand("updatePlayerLinedCash~" + id + "~" + cash);
        }
    }

    @Override
    public void removePlayersLinedCash(int id) {
        synchronized(CommunicationView.class) {
            this.sendCommand("updatePlayerLinedCash~" + id + "~0");
        }
    }

    @Override
    public void sendMessage(String text) {
        synchronized(CommunicationView.class) {
            this.sendCommand("sendMessage~" + text);
        }
    }

    @Override
    public void startNewRound() {
        synchronized(CommunicationView.class) {
            this.sendCommand("startNewRound");
            waiting = outs.size();
            while(waiting > 0) {
                System.out.println("ERRRRRRRROOOOOOOOORRRRRR");
            }
        }
    }

    @Override
    public void setPot(int cash) {
        synchronized(CommunicationView.class) {
            this.sendCommand("setPot~" + cash);
        }
    }

	@Override
	public void updateActualPlayer(int id) {
        synchronized(CommunicationView.class) {
            this.sendCommand("updateActualPlayer~" + id);
        }
	}

	@Override
	public void updateNormalPlayer(int id) {
        synchronized(CommunicationView.class) {
            this.sendCommand("updateNormalPlayer~" + id);
        }
	}

	@Override
	public void updateResignedPlayer(int id) {
        synchronized(CommunicationView.class) {
            this.sendCommand("updateResignedPlayer~" + id);
        }
	}
}
