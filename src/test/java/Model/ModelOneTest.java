package Model;

import junit.framework.TestCase;
import Adapter.MainAdapter;
import Model.ModelOne;

import org.junit.Test;
//

/**
 * Created by Arytmetyk on 2014-05-12.
 */
public class ModelOneTest extends TestCase {
    @Test
    public void testFold() throws Exception {
        MainAdapter adapter = new MainAdapter();
        ModelOne model= new ModelOne(adapter);
        model.addPlayer("Sylwek");
        model.addPlayer("Szymon");
        model.addPlayer("Maciek");
        model.addPlayer("Bartek");

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
        //assertFalse(model.isInGame(2));
        assertEquals(980,model.getMoney(0));
        assertEquals(980,model.getMoney(1));
        assertEquals(980,model.getMoney(2));
        assertEquals(1020,model.getMoney(3));
    }

}
