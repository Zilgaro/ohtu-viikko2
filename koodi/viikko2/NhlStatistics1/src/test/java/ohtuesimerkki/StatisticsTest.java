/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Zilgaro
 */
public class StatisticsTest {
    
     Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Staatistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }  
    
    @Test
    public void StatisticsListNotNull() {
        List<Player> top = stats.topScorers(1);
        
        assertNotNull("Ei pitäs olla null", top);
    }
    
    @Test
    public void BadSearchReturnsNull() {
        Player test = stats.search("Kakku :D");
        
        assertNull("Ei pitäisi löytyä huonolla haulla", test);
    }
    
    @Test
    public void GoodSearchReturnsRight() {
        Player test = stats.search("Kurri");
        
        assertEquals(test.getName(), "Kurri");
    }
    
    @Test
    public void BadTeamSearchReturnsNull() {
        List<Player> test = stats.team("Kakku :D");
        
        assertTrue("Huonolla tiiminnimellä ei pitäisi löytyä", test.isEmpty());
    }
    
    @Test
    public void GoodTeamSearchReturnsRight() {
        List<Player> test = stats.team("PIT");
        
        assertTrue(test.get(0).getName().equals("Lemieux"));
    }
}
