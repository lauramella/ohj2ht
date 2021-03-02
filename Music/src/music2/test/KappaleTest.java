package music2.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import music2.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2021.02.27 17:21:36 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class KappaleTest {


  // Generated by ComTest BEGIN
  /** testRekisteroi80 */
  @Test
  public void testRekisteroi80() {    // Kappale: 80
    Kappale kappale1 = new Kappale(); 
    assertEquals("From: Kappale line: 82", 0, kappale1.getTunnusNro()); 
    kappale1.rekisteroi(); 
    Kappale kappale2 = new Kappale(); 
    kappale2.rekisteroi(); 
    int n1 = kappale1.getTunnusNro(); 
    int n2 = kappale2.getTunnusNro(); 
    assertEquals("From: Kappale line: 88", n2-1, n1); 
  } // Generated by ComTest END
}