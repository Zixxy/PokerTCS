package test.main.java.Model;

import junit.framework.TestCase;
import main.java.Model.Player;

import org.junit.Test;

/**
 * Created by Arytmetyk on 2014-05-12.
 */
public class PlayerTest extends TestCase {
    @Test
    public void testOfConstructor() throws Exception {
        Player player=new Player("Krzysztof", 950);
        assertEquals("Krzysztof", player.getName());
        assertEquals((Object) 950, player.getMoney());
        player.setMoney(800);
        assertEquals("Krzysztof", player.getName());
        assertEquals((Object) 800, player.getMoney());
        player.setName("Zdzisław");
        assertEquals("Zdzisław", player.getName());
        assertEquals((Object) 800, player.getMoney());

    }
}
