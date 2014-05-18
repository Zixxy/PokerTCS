package test.main.java.Model;

import static org.junit.Assert.*;
import main.java.Adapter.MainAdapter;
import main.java.Model.ModelOne;

import org.junit.Test;

public class ModelOneTest3 {

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
        model.check(3); //do nothing
        model.raise(4, 2000); //do nothing
        assertEquals(2 , model.getActualPlayer());
        model.raise(2, 500); //do nothing
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
        model.raise(1,2000); //do nothing
        model.raise(2, 45234326); //do nothing
        model.raise(2, 3000);
        assertEquals(6000,model.getMoney(2));
        model.fold(4); //do nothing
        assertTrue(model.isInGame(4)); 
        model.check(3);
        assertEquals(6000, model.getMoney(3));
        model.fold(4);
        assertFalse(model.isInGame(4));
        model.check(0); 
        model.check(1);
        assertEquals(1, model.getActualStage());
        assertEquals(6000, model.getMoney(1));
        assertEquals(6000, model.getMoney(0));
        assertEquals(6000, model.getMoney(2));
        assertEquals(6000, model.getMoney(3));
        assertEquals(9000, model.getMoney(4));
        assertEquals(0, model.getActualPlayer());
        model.raise(3, 5); //do nothing
        model.raise(4,233); //do nothing
        model.check(0);
        model.check(1);
        model.check(2);
        model.raise(3, 1000);
        assertEquals(6000, model.getMoney(0));
        assertEquals(6000, model.getMoney(1));
        assertEquals(6000, model.getMoney(2));
        assertEquals(5000, model.getMoney(3));
        assertEquals(9000, model.getMoney(4));
        assertEquals(0, model.getActualPlayer());
        model.check(4); //do nothing
        model.check(0);
        model.raise(1, 500); //do nothing
        model.raise(1, 2000);
        assertEquals(5000, model.getMoney(0));
        assertEquals(4000, model.getMoney(1));
        assertEquals(6000, model.getMoney(2));
        assertEquals(5000, model.getMoney(3));
        assertEquals(9000, model.getMoney(4));
        model.raise(2, 1000); // do nothing
        assertEquals(2,model.getActualPlayer());
        model.raise(2, 2000);
        model.check(3);
        model.raise(0,1000);
        assertEquals(4000, model.getMoney(0));
        assertEquals(4000, model.getMoney(1));
        assertEquals(4000, model.getMoney(2));
        assertEquals(4000, model.getMoney(3));
        assertEquals(9000, model.getMoney(4));
        assertEquals(2, model.getActualStage());
        assertEquals(0, model.getActualPlayer());
        model.resign(1);
        assertFalse(model.isInGame(1));
        model.fold(0);
        assertFalse(model.isInGame(0));
        assertEquals(2, model.getActualPlayer());
        model.check(2);
        model.check(3);
        assertEquals(4000, model.getMoney(0));
        assertEquals(4000, model.getMoney(1));
        assertEquals(4000, model.getMoney(2));
        assertEquals(4000, model.getMoney(3));
        assertEquals(9000, model.getMoney(4));
        assertEquals(3, model.getActualStage());
        assertEquals(2,model.getActualPlayer());
        model.check(3); // do nothing
        model.raise(2, 5000); // do nothing
        model.raise(2,4000);
        assertEquals(4000, model.getMoney(0));
        assertEquals(4000, model.getMoney(1));
        assertEquals(0, model.getMoney(2));
        assertEquals(4000, model.getMoney(3));
        assertEquals(9000, model.getMoney(4));
        model.check(2); // do nothing
        
	}
}
