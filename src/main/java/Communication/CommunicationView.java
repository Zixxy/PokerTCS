package Communication;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;

import Adapter.AdapterInterface;
import Model.Deck;
import View.ViewInterface;

/**
 * Created by dakurels on 2014-05-14.
 */
public class CommunicationView  implements ViewInterface{

    private class WriterWithId {
        public PrintWriter writer;
        public int id;
        public WriterWithId(PrintWriter writer, int id) {
            this.writer = writer;
            this.id = id;
        }
        @Override
        public boolean equals(Object obj) {
            if(obj != null && obj instanceof WriterWithId) {
                WriterWithId wwi = (WriterWithId) obj;
                if(wwi.writer == this.writer && wwi.id == this.id)
                    return true;
            }
            return false;
        }
    }

    private Collection<WriterWithId> outs;
    private AdapterInterface adapter;

    public void addOut(PrintWriter writer, int id) throws IOException {
        outs.add(new WriterWithId(writer, id));
    }
    public void removeOut(PrintWriter writer, int id) throws IOException{
        outs.remove(new WriterWithId(writer, id));
    }

    @Override
    public void guiAddTable(int numberOfTable) {
        sendCommand("guiAddTable~"+numberOfTable);
    }

    @Override
    public void guiRemoveTable(int numberOfTable) {
        sendCommand("guiRemoveTable~"+numberOfTable);
    }
    
	@Override
	public void guiClearTableList() {
		sendCommand("guiClearTableList~");
	}

    @Override
    public void updateNumberOfPlayers(int numberOfTable, int currentNumberOfPlayers) {
        sendCommand("updateNumberOfPlayers~"+numberOfTable+"~"+currentNumberOfPlayers);
    }


    public CommunicationView(AdapterInterface adapter){
        this.adapter=adapter;
        outs = new ArrayList<WriterWithId>();
    }

    public synchronized void start(int id) {
        adapter.start(id);
    }

    public synchronized void parse(String order, Socket socket){//public because Server uses it
        System.err.println("GOT ORDER: " + order);
        String txt[] = order.split("~");
        txt[0]=txt[0].toLowerCase();

        switch(txt[0]){
        	case "fold":
        		adapter.fold(Integer.valueOf(txt[1]));
        		break;
        	case "check":
        		adapter.check(Integer.valueOf(txt[1]));
        		break;
        	case "raise":
        		adapter.raise(Integer.valueOf(txt[1]), txt[2]);
        		break;
        	case "resign":
        		adapter.resign(Integer.valueOf(txt[1]));
        		break;
        	case "usercards":
                //System.out.println("ASKED: " + txt[1]);
                //this.sendCards(socket, Integer.valueOf(txt[1]));
        		break;
        	case "setstartedamount":
        		adapter.setStartedAmount(Integer.valueOf(txt[1]));
        		break;
        	case "showcards":
        		sendCommand(order);
        		break;
        	default:
                throw new RuntimeException("Unknown operation type "+txt);
        }
        System.out.println("Now you can use cv.parse");
    }

    private void sendCommand(String txt) {
        //System.err.println("Mam "+outs.size()+" outy");
        System.err.println("SEND OUT: "+txt);

        for (WriterWithId print : outs)
            print.writer.println(txt);
    }

    private void sendCommandToId(String txt, int id) {
        System.err.println("SEND OUT: "+txt);
        for (WriterWithId print : outs)
            if(print.id == id)
                print.writer.println(txt);
    }

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
            this.sendCommandToId("updatePlayerHand~" + playerId + "~" + firstCard + "~" + secondCard, playerId );
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
	
	public void setLastMove(int id, int move){
		/*
		 * 0 - raise
		 * 1 - fold
		 * 2 - check
		 * 3 - all in
		 */
		synchronized(CommunicationView.class) {
			this.sendCommand("setLastMove~" + id + "~" + move);
		}
	}
	@Override
	public void almostConstructor() {
        throw new RuntimeException("This function should never be used");
	}
	@Override
	public void showCards(int playerId, int firstCardNumber, int secondCardNumber) {
        throw new RuntimeException("This function should never be used");
	}
	@Override
	public void setPlayerId(int id) {
        throw new RuntimeException("This function should never be used");
		
	}
	
	
}
