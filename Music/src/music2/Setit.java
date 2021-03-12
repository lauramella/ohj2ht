package music2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * CRC-kortti
|------------------------------------------------------------------------|
| Luokan nimi: Setit                                 | Avustajat:        |
|-------------------------------------------------------------------------
| Vastuualueet:                                      |                   |
|                                                    | - Setti           |
|- pitää yllä varsinaista rekisteriä seteistä eli    | -                 |
|  osaa lisätä ja poistaa setin                      | -                 |
|- lukee ja kirjoittaa setit tiedostoon              |                   |
|- osaa etsiä ja lajitella                           |                   |
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
    private String nimi;


    /**
     * Alustaminen
     */
    public Setit() {
       // ei vielä tarvii mitään.
    }
    
    
    /**
     * @param setti lisättävä setti
     */
    public void lisaa(Setti setti) {
        alkiot.add(setti);
    }


    /**
     * Testiohjelma seteille
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Setit setit = new Setit();
        Setti setti1 = new Setti();
        setti1.taytaSettiTiedoilla();
        Setti setti2 = new Setti();
        setti2.taytaSettiTiedoilla();
        Setti setti3 = new Setti();
        setti3.taytaSettiTiedoilla();
        Setti setti4 = new Setti();
        setti4.taytaSettiTiedoilla();
        
        setit.lisaa(setti1);
        setit.lisaa(setti2);
        setit.lisaa(setti3);
        setit.lisaa(setti2);
        setit.lisaa(setti4);
        
        System.out.println("============= Setit testi =================");
        
        List<Setti> settiLista = setit.annaSetit();
        for (Setti set : settiLista) {
            set.tulosta(System.out);
       }
    }
  

   /**
    * Haetaan kaikki setit
    * @return tietorakenne jossa viitteet löydetteyihin setteihin
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
    public List<Setti> annaRelaatiot() {
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
     * @return lista seteistä
     */
    public List<Setti> annaSetit() {
        List<Setti> listaSetit = new ArrayList<Setti>();
        for (Setti setti : alkiot)
            listaSetit.add(setti);
        return listaSetit;
    }
    
    

}
