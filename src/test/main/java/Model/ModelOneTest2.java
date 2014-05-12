package test.main.java.Model;

import static org.junit.Assert.*;
import main.java.Adapter.MainAdapter;
import main.java.Model.ModelOne;

import org.junit.Test;

public class ModelOneTest2 {

	@Test
	public void test() {
		MainAdapter adapter = new MainAdapter();
        ModelOne model= new ModelOne(adapter);
        model.addPlayer("Sylwek");
        model.addPlayer("Szymon");
        model.addPlayer("Maciek");
        model.addPlayer("Bartek");
        model.addPlayer("Miron");

        model.setStartedAmount(2020);
        model.setAnte(20);
        model.removePlayer(3);
        model.addPlayer("Maciek");
        assertEquals(5,model.size());
        assertFalse(model.isStarted());
        model.start();
        assertTrue(model.isStarted());
        assertTrue(model.isInGame(0));
        assertTrue(model.isInGame(1));
        assertTrue(model.isInGame(2));
        assertTrue(model.isInGame(3));
        assertTrue(model.isInGame(4));
        assertEquals(0,model.getActualPlayer());
        model.raise(2, 200);
        model.raise(3, 100);
        model.raise(0, 200);
        assertEquals(1,model.getActualPlayer());
        model.raise(1, 0);
        assertEquals(1,model.getActualPlayer());
        model.raise(1, 200);
        assertEquals(2,model.getActualPlayer());
        model.raise(4, 200);
        model.raise(2, 300);
        assertEquals(3,model.getActualPlayer());
        model.raise(3, 200);
        assertEquals(3, model.getActualPlayer());
        model.raise(3, 400);
        assertEquals(1600,model.getMoney(3));
        assertEquals(4,model.getActualPlayer());
        assertEquals(1800,model.getMoney(0));
        assertEquals(1800 , model.getMoney(1) );
        assertEquals(1700 ,model.getMoney(2));
        assertEquals(1600,model.getMoney(3));
        model.check(4);
        model.check(2);
        model.check(0);
        model.check(1);
        model.check(2);
        model.check(3);
        assertEquals(1600, model.getMoney(0));
        assertEquals(1600, model.getMoney(1));
        assertEquals(1600,model.getMoney(2) );
        assertEquals(1600 , model.getMoney(3) );
        assertEquals(1600, model.getMoney(3) );
        model.fold(2);
        assertTrue(model.isInGame(2));
        model.fold(4);
        assertFalse(model.isInGame(4));
        assertEquals(1600,model.getMoney(4));
        assertEquals(0,model.getActualPlayer());
        model.resign(2);
        assertFalse(model.isInGame(2));
        model.check(0);
        model.raise(1,0);
        assertEquals(3, model.getActualPlayer() );
        model.raise(2, 400);
        model.raise(3, 200);
        assertEquals(0,model.getActualPlayer());
        model.raise(0, 100);
        model.raise(1, 56475438);
        model.raise(0, 200);
        model.check(1);
        model.raise(3,12345);
        assertEquals(3, model.getActualPlayer());
        assertEquals(1400, model.getMoney(0));
        assertEquals(1400, model.getMoney(1) );
        assertEquals(1400, model.getMoney(3));
        assertEquals(1600, model.getMoney(2) );
        assertEquals(1600, model.getMoney(4));
	}


}
