package main.java.Model;


import main.java.Adapter.MainAdapter;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Created by bartek on 06.05.14.
 */
public class ModelOneTest {
    @Test
    public void test0() throws Exception {
        MainAdapter adapter = new MainAdapter();
        ModelOne model= new ModelOne(adapter);
        model.addPlayer("Sylwek");
        model.addPlayer("Szymon");
        model.addPlayer("Maciek");
        model.addPlayer("Bartek");
        model.addPlayer("Miron");
        assertFalse(model.isStarted());
        model.start();
        assertTrue(model.isStarted());
        model.fold(0);
        assertFalse(model.isInGame(0));
        model.fold(2);
        assertTrue(model.isInGame(2));
        model.fold(1);
        assertFalse(model.isInGame(1));
        model.fold(2);
        assertFalse(model.isInGame(2));
        assertEquals(model.getMoney(0),990);
        assertEquals(model.getMoney(1),990);
        assertEquals(model.getMoney(2),990);
        assertEquals(model.getMoney(3),1030);


    }
}
