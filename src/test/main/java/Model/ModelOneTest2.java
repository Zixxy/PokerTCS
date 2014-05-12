package main.java.Model;

import static org.junit.Assert.*;
import main.java.Adapter.MainAdapter;
import main.java.Model.ModelOne;
import main.java.View.CommandLine;

import org.junit.Test;

public class ModelOneTest2 {

	@Test
	public void test() {
		MainAdapter adapter = new MainAdapter();
        ModelOne model= new ModelOne(adapter);
        adapter.addModel(model);
        adapter.addView(new CommandLine(adapter));
        model.setStartedAmount(2020);
        model.setAnte(20);
        model.addPlayer("Sylwek");
        model.addPlayer("Szymon");
        model.addPlayer("Maciek");
        model.addPlayer("Bartek");
        model.addPlayer("Miron");

        
        model.removePlayer(3);
        model.addPlayer("Bartek");
        //assertEquals(5,model.size());
        assertFalse(model.isStarted());
        model.start();
        assertTrue(model.isStarted());
        assertTrue(model.isInGame(0));
        assertTrue(model.isInGame(1));
        assertTrue(model.isInGame(2));
        assertTrue(model.isInGame(4));
        assertTrue(model.isInGame(5));
        assertEquals(0,model.getActualPlayer());
        model.raise(2, 200);
        model.raise(4, 100);
        model.raise(0, 200);
        assertEquals(1,model.getActualPlayer());
        model.raise(1, 0);
        assertEquals(1,model.getActualPlayer());
        model.raise(1, 200);
        assertEquals(2,model.getActualPlayer());
        model.raise(5, 200);
        model.raise(2, 300);
        assertEquals(4,model.getActualPlayer());
        model.raise(4, 200);
        assertEquals(4, model.getActualPlayer());
        model.raise(4, 400);
        assertEquals(1600,model.getMoney(4));
        assertEquals(5,model.getActualPlayer());
        assertEquals(1800,model.getMoney(0));
        assertEquals(1800 , model.getMoney(1) );
        assertEquals(1700 ,model.getMoney(2));
        assertEquals(1600,model.getMoney(4));
        model.check(5);
        model.check(2);
        model.check(0);
        model.check(1);
        model.check(2);
        model.check(4);
        assertEquals(1600, model.getMoney(0));
        assertEquals(1600, model.getMoney(1));
        assertEquals(1600,model.getMoney(2) );
        assertEquals(1600 , model.getMoney(4) );
        assertEquals(1600, model.getMoney(5) );
        model.fold(2);
        assertTrue(model.isInGame(2));
        model.fold(5);
        assertFalse(model.isInGame(5));
        assertEquals(1600,model.getMoney(5));
        assertEquals(0,model.getActualPlayer());
        model.resign(2);
        assertFalse(model.isInGame(2));
        model.check(0);
        model.raise(1,0);
        assertEquals(4, model.getActualPlayer() );
        model.raise(2, 400);
        model.raise(4, 200);
        assertEquals(0,model.getActualPlayer());
        model.raise(0, 100);
        model.raise(1, 56475438);
        model.raise(0, 200);
        assertEquals(1400, model.getMoney(0));
        assertEquals(1600, model.getMoney(1) );
        assertEquals(1400, model.getMoney(4));
        assertEquals(1600, model.getMoney(2) );
        assertEquals(1600, model.getMoney(5));
        model.check(1);
	}
	@Test
	public void test1() {
		MainAdapter adapter = new MainAdapter();
        ModelOne model= new ModelOne(adapter);
        adapter.addModel(model);
        //adapter.addView(new CommandLine(adapter));
        model.setStartedAmount(11000);
        model.setAnte(1000);
        model.setSmallBlind(500);
        model.setBigBlind(1000);
        model.addPlayer("Sylwek");
        model.addPlayer("Szymon");
        model.addPlayer("Maciek");
        model.addPlayer("Bartek");
        model.addPlayer("Miron");
        model.start();
        assertTrue(model.isInGame(0));
        assertTrue(model.isInGame(1));
        assertTrue(model.isInGame(2));
        assertTrue(model.isInGame(3));
        assertTrue(model.isInGame(4));
        assertEquals(9500 , model.getMoney(0));
        assertEquals(9000 , model.getMoney(1));
        model.check(3);
        model.raise(4, 2000);
        assertEquals(2 , model.getActualPlayer());
        model.raise(2, 500);
        assertEquals(2 , model.getActualPlayer());
        model.raise(2, 1000);
        assertEquals(3 , model.getActualPlayer());
        model.check(3);
        model.check(4);
        model.check(0);
        model.raise(1,1000);
        assertEquals(8000, model.getMoney(1));
        assertEquals(9000, model.getMoney(0));
        assertEquals(9000, model.getMoney(2));
        assertEquals(9000, model.getMoney(3));
        assertEquals(9000, model.getMoney(4));
        assertEquals(0,model.getActualStage());
        assertEquals(2 , model.getActualPlayer());
        model.raise(1,2000);
        model.raise(2, 45234326);
        model.raise(2, 3000);
        assertEquals(6000,model.getMoney(2));
	}

}
