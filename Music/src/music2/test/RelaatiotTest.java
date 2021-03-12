package music2.test;
// Generated by ComTest BEGIN
import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;
import music2.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2021.03.12 14:32:24 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class RelaatiotTest {



  // Generated by ComTest BEGIN
  /** testAnnaRelaatiot67 */
  @Test
  public void testAnnaRelaatiot67() {    // Relaatiot: 67
    Relaatiot relaatiot = new Relaatiot(); 
    Relaatio rel1 = new Relaatio(1,2); relaatiot.lisaa(rel1); 
    Relaatio rel2 = new Relaatio(2,2); relaatiot.lisaa(rel2); 
    Relaatio rel3 = new Relaatio(4,2); relaatiot.lisaa(rel3); 
    Relaatio rel4 = new Relaatio(1,1); relaatiot.lisaa(rel4); 
    Relaatio rel5 = new Relaatio(2,4); relaatiot.lisaa(rel5); 
    Relaatio rel6 = new Relaatio(1,2); relaatiot.lisaa(rel6); 
    List<Relaatio> loytyneet; 
    loytyneet = relaatiot.annaRelaatiot(3); 
    assertEquals("From: Relaatiot line: 80", 0, loytyneet.size()); 
    loytyneet = relaatiot.annaRelaatiot(2); 
    assertEquals("From: Relaatiot line: 82", 4, loytyneet.size()); 
    assertEquals("From: Relaatiot line: 83", true, loytyneet.get(0) == rel1); 
    assertEquals("From: Relaatiot line: 84", true, loytyneet.get(1) == rel2); 
    loytyneet = relaatiot.annaRelaatiot(4); 
    assertEquals("From: Relaatiot line: 86", 1, loytyneet.size()); 
    assertEquals("From: Relaatiot line: 87", true, loytyneet.get(0) == rel5); 
  } // Generated by ComTest END
}