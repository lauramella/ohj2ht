package music2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * CRC-kortti
|------------------------------------------------------------------------|
| Luokan nimi: Relaatiot                             | Avustajat:        |
|-------------------------------------------------------------------------
| Vastuualueet:                                      |                   |
|                                                    | - Relaatio        |
| - huolehtii Kappaleet ja Setit -luokkien välisestä |                   |
|   yhteistyöstä ja välittää näitä tietoja pyydet-   |                   |
|   täessä                                           |                   |
|                                                    |                   |
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
 * @version 12.3.2021
 *
 */
public class Relaatiot implements Iterable<Relaatio> {
    private Collection<Relaatio> alkiot = new ArrayList<Relaatio>();
    private String nimi = "";
    private boolean muutettu = false;


    /**
     * Alustaminen
     */
    public Relaatiot() {
       // ei vielä tarvii mitään.
    }
    
    
    /**
     * @param rel lisättävä setti
     */
    public void lisaa(Relaatio rel) {
        alkiot.add(rel);
        muutettu = true;
    }
    
    
    /**
     * Lukee relaatiot tiedostosta
     * @param hakemisto tiedoston hakemisto
     * @throws SailoException jos lukeminen epäonnistuu
     */
    public void lueTiedostosta(String hakemisto) throws SailoException {
        String nimi1 = hakemisto + "/relaatiot.dat";
        File ftied = new File(nimi1);
        try (Scanner fi = new Scanner(new FileInputStream(ftied))) { 
            while ( fi.hasNext() ) {
                String s = "";
                s = fi.nextLine().trim();
                if ( "".equals(s) || s.charAt(0) == ';' ) continue;
                Relaatio relaatio = new Relaatio(1,1);
                relaatio.parse(s); //vois palauttaa jotain?
                lisaa(relaatio);
            }
            muutettu = false;
        } catch ( FileNotFoundException e ) {
            throw new SailoException("Ei saa luettua tiedostoa " + nimi1);
        //} catch ( IOException e ) {
            //throw new SailoException("Ongelmia tiedoston kanssa: " + e.getMessage());
        }
    }

    
    /**
     * Tallentaa relaatiot tiedostoon.
     * Tiedoston muoto:
     * <pre>
     * 1 |2  |1
     * 3 |4  |1
     * </pre> 
     * @param tiednimi tallennettavan tiedoston nimi
     * @throws SailoException jos talletus epäonnistuu
     */
    public void tallenna(String tiednimi) throws SailoException {
        if ( !muutettu ) return;
        if (!new File(tiednimi).exists()) new File(tiednimi).mkdir();
        File ftied = new File(tiednimi + "/relaatiot.dat");
        try (PrintStream fo = new PrintStream(new FileOutputStream(ftied, false))) {
            for (var relaatiot: alkiot) {
                fo.println(relaatiot.toString());
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Tiedosto " + ftied.getAbsolutePath() + " ei aukea.");
            return;
        } 
        muutettu = false;
    }


    /**
     * Testiohjelma seteille
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Relaatiot relaatiot = new Relaatiot();
        
        try {
            relaatiot.lueTiedostosta("musa");
        } catch (SailoException e1) {
            System.err.println(e1.getMessage());
        }
        
        Relaatio rel1 = new Relaatio(1,1);
        Relaatio rel2 = new Relaatio(2,1);
        Relaatio rel3 = new Relaatio(3,1);
        Relaatio rel4 = new Relaatio(3,2);
        rel1.rekisteroi();
        rel2.rekisteroi();
        rel3.rekisteroi();
        rel4.rekisteroi();
        
        relaatiot.lisaa(rel1);
        relaatiot.lisaa(rel2);
        relaatiot.lisaa(rel3);
        relaatiot.lisaa(rel4);
        
        
        List<Relaatio> relaatioLista = relaatiot.annaRelaatiot(1);                        //anna kaikki relaatiot, jotka setId=1
        
          for (Relaatio rel : relaatioLista) {
                   rel.tulosta(System.out);
              }
          
          try {
              relaatiot.tallenna("musa");
          } catch (SailoException e) {
              e.printStackTrace();
          }

    }   

         /**
          * Haetaan kaikki setin relaatiot
          * @param settiTunnusNro setin tunnusnumero jolle relaatioita haetaan
          * @return tietorakenne jossa viitteet löydetteyihin relaatioihin
          * @example
          * <pre name="test">
          * #import java.util.*;
          * 
          *  Relaatiot relaatiot = new Relaatiot();
          *  Relaatio rel1 = new Relaatio(1,2); relaatiot.lisaa(rel1);
          *  Relaatio rel2 = new Relaatio(2,2); relaatiot.lisaa(rel2);
          *  Relaatio rel3 = new Relaatio(4,2); relaatiot.lisaa(rel3);
          *  Relaatio rel4 = new Relaatio(1,1); relaatiot.lisaa(rel4);
          *  Relaatio rel5 = new Relaatio(2,4); relaatiot.lisaa(rel5);
          *  Relaatio rel6 = new Relaatio(1,2); relaatiot.lisaa(rel6);
          *  
          *  List<Relaatio> loytyneet;
          *  loytyneet = relaatiot.annaRelaatiot(3);
          *  loytyneet.size() === 0; 
          *  loytyneet = relaatiot.annaRelaatiot(2);
          *  loytyneet.size() === 4; 
          *  loytyneet.get(0) == rel1 === true;
          *  loytyneet.get(1) == rel2 === true;
          *  loytyneet = relaatiot.annaRelaatiot(4);
          *  loytyneet.size() === 1; 
          *  loytyneet.get(0) == rel5 === true;
          * </pre> 
          */   
    public List<Relaatio> annaRelaatiot(int settiTunnusNro) {
        List <Relaatio> loydetyt = new ArrayList<Relaatio>();
        for (Relaatio rel : alkiot)
            if (rel.getSettiNro()== settiTunnusNro) loydetyt.add(rel);
        return loydetyt;
    }


        @Override
        public Iterator<Relaatio> iterator() {
            return alkiot.iterator();
        }  


}