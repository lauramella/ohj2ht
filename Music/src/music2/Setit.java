package music2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

/**
 * CRC-kortti
|------------------------------------------------------------------------|
| Luokan nimi: Setit                                 | Avustajat:        |
|-------------------------------------------------------------------------
| Vastuualueet:                                      |                   |
|                                                    | - Setti           |
|- pit yll varsinaista rekisteri seteist eli    | -                 |
|  osaa list ja poistaa setin                      | -                 |
|- lukee ja kirjoittaa setit tiedostoon              |                   |
|- osaa etsi ja lajitella                           |                   |
|                                                    |                   |
|                                                    |                   |
|                                                    |                   |
|                                                    |                   |
|                                                    |                   |
|                                                    |                   |
|                                                    |                   |
|                                                    |                   |
|                                                    |                   |
|-------------------------------------------------------------------------
 * @author laura
 * @version 8.3.2021
 *
 */
public class Setit {
    private Collection<Setti> alkiot = new ArrayList<Setti>();
    private String nimi = "";
    private boolean muutettu = false;


    /**
     * Alustaminen
     */
    public Setit() {
       // ei viel tarvii mitn.
    }
    
    
    /**
     * @param setti listtv setti
     */
    public void lisaa(Setti setti) {
        alkiot.add(setti);
        muutettu = true;
    }
    
    
    public boolean poista(Setti setti) {
        boolean ret = alkiot.remove(setti);
        if (ret) muutettu = true;
        return ret;
    }


   /**
    * Haetaan kaikki setit
    * @return tietorakenne jossa viitteet lydetteyihin setteihin
    * @example
    * <pre name="test">
    * #import java.util.*;
    * 
    *  Setit setit = new Setit();
    *  Setti s1 = new Setti(); setit.lisaa(s1);
    *  Setti s2 = new Setti(); setit.lisaa(s2);
    *  Setti s3 = new Setti(); setit.lisaa(s3);
    *  Setti s4 = new Setti(); setit.lisaa(s4);
    *  Setti s5 = new Setti(); setit.lisaa(s5);
    *  Setti s6 = new Setti(); setit.lisaa(s6);
    *  
    *  List<Setti> loytyneet;
    *  loytyneet = setit.annaSetit();
    *  loytyneet.size() === 6;
    *  loytyneet.get(0) == s1 === true;
    *  loytyneet.get(1) == s2 === true;
    * </pre> 
    */   
    public List<Setti> annaSetit() {
        List <Setti> loydetyt = new ArrayList<Setti>();
            for (Setti set : alkiot)
                loydetyt.add(set);
        return loydetyt;
} 
   
    
    @Override
    public String toString() {
        return nimi + ", settien lkm: " + alkiot.size();
    }
    
    /**
     * Tulostetaan kappaleen tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    
    /**
     * Lukee setit tiedostosta.
     * @param hakemisto tiedoston nimen alkuosa
     * @throws SailoException jos lukeminen eponnistuu
     * 
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * #import java.io.File;
     *  Setit setit = new Setit();
     *  Setti s1 = new Setti(); s1.taytaSettiTiedoilla();
     *  Setti s2 = new Setti(); s2.taytaSettiTiedoilla();
     *  Setti s3 = new Setti(); s3.taytaSettiTiedoilla();
     *  Setti s4 = new Setti(); s4.taytaSettiTiedoilla();
     *  Setti s5 = new Setti(); s5.taytaSettiTiedoilla();
     *  String tiedNimi = "testisetit";
     *  File ftied = new File(tiedNimi + "/setit.dat");
     *  ftied.delete();
     *  setit.lueTiedostosta(tiedNimi); #THROWS SailoException
     *  setit.lisaa(s1);
     *  setit.lisaa(s2);
     *  setit.lisaa(s3);
     *  setit.lisaa(s4);
     *  setit.lisaa(s5);
     *  setit.tallenna(tiedNimi);
     *  setit = new Setit();
     *  setit.lueTiedostosta(tiedNimi);
     * </pre>
     */
    public void lueTiedostosta(String hakemisto) throws SailoException {
        String nimi1 = hakemisto + "/setit.dat";
        File ftied = new File(nimi1);
        try (Scanner fi = new Scanner(new FileInputStream(ftied))) { // Jotta UTF8/ISO-8859 toimii'
            while ( fi.hasNext() ) {
                String s = "";
                s = fi.nextLine().trim();
                if ( "".equals(s) || s.charAt(0) == ';' ) continue;
                Setti setti = new Setti();
                setti.parse(s); // voisi olla virheksittely
                lisaa(setti);
            }
            muutettu = false;
        } catch ( FileNotFoundException e ) {
            throw new SailoException("Ei saa luettua tiedostoa " + nimi1);
        }
    }
    
    
    /**
     * Tallentaa setit tiedostoon.  
     * Tiedoston muoto:
     * <pre>
     * 2|Minimal|5
     * 3|Setti4|5
     * </pre>
     * @param tiednimi tallennettavan tiedoston hakemisto
     * @throws SailoException jos talletus eponnistuu
     */
    public void tallenna(String tiednimi) throws SailoException {
        //if ( !muutettu ) return;
        if (!new File(tiednimi).exists()) new File(tiednimi).mkdir();
        File ftied = new File(tiednimi + "/setit.dat");
        
        try (PrintStream fo = new PrintStream(new FileOutputStream(ftied, false))) {
            for (var sets: alkiot) {
                fo.println(sets.toString());
            }
        } catch (FileNotFoundException ex) {
            throw new SailoException("Tiedosto " + ftied.getAbsolutePath() + " ei aukea");
        }
        muutettu = false;
    }
    
    
    /**
     * Testiohjelma seteille
     * @param args ei kytss
     */
    public static void main(String[] args) {
        Setit setit = new Setit();
        
        try {
            setit.lueTiedostosta("musa");
        } catch (SailoException ex) {
            System.err.println(ex.getMessage());
        }

        Setti setti1 = new Setti();
        setti1.rekisteroi();
        setti1.taytaSettiTiedoilla();
        Setti setti2 = new Setti();
        setti2.rekisteroi();
        setti2.taytaSettiTiedoilla();
        Setti setti3 = new Setti();
        setti3.rekisteroi();
        setti3.taytaSettiTiedoilla();
        Setti setti4 = new Setti();
        setti4.rekisteroi();
        setti4.taytaSettiTiedoilla();
        
        setit.lisaa(setti1);
        setit.lisaa(setti2);
        setit.lisaa(setti3);
        setit.lisaa(setti2);

        
        System.out.println("============= Setit testi =================");
        
        List<Setti> settiLista = setit.annaSetit();
        for (Setti set : settiLista) {
            set.tulosta(System.out);
       }
        
        try {
            setit.tallenna("musa");
        } catch (SailoException e) {
            e.printStackTrace();
        }

    }

}

