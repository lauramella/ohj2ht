package music2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * CRC-kortti
 |------------------------------------------------------------------------|
| Luokan nimi: Relaatiot                             | Avustajat:        |
|-------------------------------------------------------------------------
| Vastuualueet:                                      |                   |
|                                                    | - Kappale         |
| - huolehtii Kappaleet ja Setit -luokkien välisestä | - Setti           |
|   yhteistyöstä ja välittää näitä tietoja pyydet-   | - Kappaleet       |
|   täessä                                           | - Setit           |
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
public class Relaatiot {
    private Collection<Relaatio> alkiot = new ArrayList<Relaatio>();
    private String nimi = "";
    private int lkm;


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
    }


    /**
     * Testiohjelma seteille
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Relaatiot relaatiot = new Relaatiot();
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

}