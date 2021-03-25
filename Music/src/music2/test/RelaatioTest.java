package music2.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import music2.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2021.03.21 12:15:57 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class RelaatioTest {



  // Generated by ComTest BEGIN
  /** testRekisteroi61 */
  @Test
  public void testRekisteroi61() {    // Relaatio: 61
    Relaatio rel1 = new Relaatio(1,1); 
    assertEquals("From: Relaatio line: 63", 0, rel1.getTunnusNro()); 
    rel1.rekisteroi(); 
    Relaatio rel2 = new Relaatio(2,1); 
    rel2.rekisteroi(); 
    int n1 = rel1.getTunnusNro(); 
    int n2 = rel2.getTunnusNro(); 
    assertEquals("From: Relaatio line: 69", n2-1, n1); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testParse96 */
  @Test
  public void testParse96() {    // Relaatio: 96
    Relaatio relaatio = new Relaatio(1,1); 
    relaatio.parse("   2  |  1   | 1"); 
    assertEquals("From: Relaatio line: 99", 2, relaatio.getTunnusNro()); 
    assertEquals("From: Relaatio line: 100", "2|1|1", relaatio.toString()); 
    relaatio.rekisteroi(); 
    int n = relaatio.getTunnusNro(); 
    relaatio.parse(""+(n+20));  // Otetaan merkkijonosta vain tunnusnumero
    relaatio.rekisteroi();  // ja tarkistetaan ett� seuraavalla kertaa tulee yht� isompi
    assertEquals("From: Relaatio line: 105", n+20+1, relaatio.getTunnusNro()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testToString149 */
  @Test
  public void testToString149() {    // Relaatio: 149
    Relaatio relaatio = new Relaatio(1,2); 
    relaatio.parse("   1   |  1 | 2 "); 
    assertEquals("From: Relaatio line: 152", "1|1|2", relaatio.toString()); 
  } // Generated by ComTest END
}