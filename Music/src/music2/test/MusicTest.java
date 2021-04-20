package music2.test;
// Generated by ComTest BEGIN
import music2.SailoException;
import java.util.*;
// Generated by ComTest END
import static org.junit.Assert.*;
import org.junit.*;
import music2.*;

/**
 * Test class made by ComTest
 * @version 2021.04.20 20:09:10 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class MusicTest {


  // Generated by ComTest BEGIN  // Music: 35
   private Music music; 
   private Kappale kap1; 
   private Kappale kap2; 
   private int kid1; 
   private int kid2; 
   private Setti setti1; 
   private Setti setti2; 
   private Relaatio rel1; 
   private Relaatio rel2; 
   private Relaatio rel3; 

   @SuppressWarnings("javadoc")
   public void alustaMusic() {
     music = new Music(); 
     kap1 = new Kappale(); kap1.taytaKappaleTiedoilla(); kap1.rekisteroi(); 
     kap2 = new Kappale(); kap2.taytaKappaleTiedoilla(); kap2.rekisteroi(); 
     kid1 = kap1.getTunnusNro(); 
     kid2 = kap2.getTunnusNro(); 
     setti1 = new Setti(); setti1.taytaSettiTiedoilla(); 
     setti2 = new Setti(); setti2.taytaSettiTiedoilla(); 
     rel1 = new Relaatio(1,1); 
     rel2 = new Relaatio(2,1); 
     rel3 = new Relaatio(3,2); 
     try {
     music.lisaa(kap1); 
     music.lisaa(kap2); 
     music.lisaa(setti1); 
     music.lisaa(setti2); 
     music.lisaa(rel1); 
     music.lisaa(rel2); 
     music.lisaa(rel3); 
     } catch ( Exception e) {
        System.err.println(e.getMessage()); 
     }
   }
  // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testLisaa87 
   * @throws SailoException when error
   */
  @Test
  public void testLisaa87() throws SailoException {    // Music: 87
    alustaMusic(); 
    assertEquals("From: Music line: 90", 2, music.etsi("*",0).size()); 
    music.lisaa(kap1); 
    assertEquals("From: Music line: 92", 3, music.etsi("*",0).size()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testKorvaaTaiLisaa106 
   * @throws SailoException when error
   */
  @Test
  public void testKorvaaTaiLisaa106() throws SailoException {    // Music: 106
    alustaMusic(); 
    assertEquals("From: Music line: 109", 2, music.etsi("*",0).size()); 
    music.korvaaTaiLisaa(kap1); 
    assertEquals("From: Music line: 111", 2, music.etsi("*",0).size()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testAnnaRelaatiot197 */
  @Test
  public void testAnnaRelaatiot197() {    // Music: 197
    alustaMusic(); 
    List<Relaatio> loytyneet; 
    loytyneet = music.annaRelaatiot(3); 
    assertEquals("From: Music line: 204", 0, loytyneet.size()); 
    loytyneet = music.annaRelaatiot(1); 
    assertEquals("From: Music line: 206", 2, loytyneet.size()); 
    assertEquals("From: Music line: 207", true, loytyneet.get(0) == rel1); 
    assertEquals("From: Music line: 208", true, loytyneet.get(1) == rel2); 
    loytyneet = music.annaRelaatiot(2); 
    assertEquals("From: Music line: 210", 1, loytyneet.size()); 
    assertEquals("From: Music line: 211", true, loytyneet.get(0) == rel3); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testAnnaKappaleenRelaatiot224 
   * @throws SailoException when error
   */
  @Test
  public void testAnnaKappaleenRelaatiot224() throws SailoException {    // Music: 224
    alustaMusic(); 
    Kappale kap3 = new Kappale(); 
    kap3.rekisteroi(); 
    music.lisaa(kap3); 
    List<Relaatio> loytyneet; 
    loytyneet = music.annaKappaleenRelaatiot(4); 
    assertEquals("From: Music line: 234", 0, loytyneet.size()); 
    loytyneet = music.annaKappaleenRelaatiot(1); 
    assertEquals("From: Music line: 236", 1, loytyneet.size()); 
    assertEquals("From: Music line: 237", true, loytyneet.get(0) == rel1); 
  } // Generated by ComTest END
}