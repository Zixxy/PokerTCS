package test;


import Model.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by bartek on 05.05.14.
 */

public class PlayerTest {
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
