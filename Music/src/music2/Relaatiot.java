package music2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
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
| - huolehtii Kappaleet ja Setit -luokkien v?lisest? |                   |
|   yhteisty?st? ja v?litt?? niit? tietoja pyydet-   |                   |
|   tess?                                            |                   |
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
    // private Collection<Relaatio> alkiot = new ArrayList<Relaatio>();
    private boolean muutettu = false;
    private static final int MAX_RELAATIOT = 20;
    private int lkm = 0;
    private Relaatio[] alkiot2;

    /**
     * Alustaminen
     */
    public Relaatiot() {
        alkiot2 = new Relaatio[MAX_RELAATIOT];
    } 


    /**
     * Lis?? ja rekister?i relaation
     * @param rel lis?tt?v? relaatio
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * Relaatiot relaatiot = new Relaatiot();
     * Relaatio rel1 = new Relaatio(1,1), rel2 = new Relaatio(3,3);
     * relaatiot.getLkm() === 0;
     * relaatiot.lisaa(rel1); relaatiot.getLkm() === 1;
     * relaatiot.lisaa(rel2); relaatiot.getLkm() === 2;
     * relaatiot.lisaa(rel1); relaatiot.getLkm() === 3;  
     * relaatiot.lisaa(rel1); relaatiot.getLkm() === 4;
     * relaatiot.lisaa(rel1); relaatiot.getLkm() === 5;
     * </pre>
     */
    public void lisaa(Relaatio rel) {
        rel.rekisteroi();
        if (lkm >= alkiot2.length) 
            alkiot2 = Arrays.copyOf(alkiot2, alkiot2.length+10);
        this.alkiot2[this.lkm] = rel;
        lkm++;
        muutettu = true;
    }

 
    /** 
     * Poistaa relaation jolla on valittu tunnusnumero  
     * @param id poistettavan relaation tunnusnumero 
     * @example 
     * <pre name="test">  
     * Relaatiot relaatiot = new Relaatiot(); 
     * Relaatio rel1 = new Relaatio(1,1), rel2 = new Relaatio(2,4), rel3 = new Relaatio(7,8); 
     * rel1.rekisteroi(); rel2.rekisteroi(); rel3.rekisteroi();  
     * relaatiot.lisaa(rel1); relaatiot.lisaa(rel2); relaatiot.lisaa(rel3); 
     * relaatiot.poista(rel2.getTunnusNro()); relaatiot.getLkm() === 2; 
     * relaatiot.poista(rel1.getTunnusNro()); relaatiot.getLkm() === 1; 
     * </pre> 
     *  
     */
    public void poista(int id) { 
        int ind = etsiId(id); 
        if (ind < 0) return; 
        lkm--; 
        for (int i = ind; i < lkm; i++) 
            alkiot2[i] = alkiot2[i + 1]; 
        alkiot2[lkm] = null; 
        muutettu = true;  
    }
    
    
    /**
     * Poistetaan kaikki setin relaatiot
     * @param setId setin tunnusnumero
     */
    public void poistaKaikki(int setId){
        int poistettu = 0;
        for (int i=0; i < lkm; i++) {
            if (alkiot2[i].getSettiNro()== setId) {
                poistettu ++;
            }
            else  {
                alkiot2[i-poistettu] = alkiot2[i];
            }
        }
        for (; poistettu > 0; poistettu--) {
            alkiot2[--lkm] = null;
        }
        muutettu = true;
    }



    /** 
     * Etsii relaation id:n perusteella 
     * @param id tunnusnumero, jonka mukaan etsit??n 
     * @return l?ytyneen relaation indeksi tai -1 jos ei l?ydy 
     * <pre name="test">  
     * Relaatiot relaatiot = new Relaatiot(); 
     * Relaatio rel1 = new Relaatio(1,1), rel2 = new Relaatio(2,4), rel3 = new Relaatio(3,4); 
     * relaatiot.lisaa(rel1); relaatiot.lisaa(rel2); relaatiot.lisaa(rel3); 
     * relaatiot.etsiId(rel2.getTunnusNro()) === 1; 
     * relaatiot.etsiId(rel3.getTunnusNro()) === 2; 
     * </pre> 
     */
    public int etsiId(int id) { 
        for (int i = 0; i < lkm; i++) 
            if (id == alkiot2[i].getTunnusNro()) return i; 
        return -1; 
    } 


    /**
     * Lukee relaatiot tiedostosta
     * @param hakemisto tiedoston hakemisto
     * @throws SailoException jos lukeminen eponnistuu
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
     * @throws SailoException jos talletus eponnistuu
     */
    public void tallenna(String tiednimi) throws SailoException {
        if ( !muutettu ) return;
        if (!new File(tiednimi).exists()) new File(tiednimi).mkdir();
        File ftied = new File(tiednimi + "/relaatiot.dat");
        try (PrintStream fo = new PrintStream(new FileOutputStream(ftied, false))) {
            for (int i = 0; i < getLkm(); i++) {
                Relaatio rel = anna(i);
                fo.println(rel.toString());
            }          
        } catch (FileNotFoundException ex) {
            System.err.println("Tiedosto " + ftied.getAbsolutePath() + " ei aukea.");
            return;
        } 
        muutettu = false;
    }

    /**
     * Relaatioiden lukum??r?
     * @return relaatioiden lukum??r?
     */
    public int getLkm() {
        return lkm;
    }

    /**
     * Palauttaa relaation, joka on paikassa i
     * @param i relaation indeksi taulukossa
     * @return relaatio kohdassa i
     * @throws IndexOutOfBoundsException jos indeksi? ei ole
     */
    public Relaatio anna(int i) throws IndexOutOfBoundsException{
        if (i < 0 || lkm <=i)
            throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
        return alkiot2[i];
    }
   

    /**
     * Haetaan kaikki setin relaatiot
     * @param settiTunnusNro setin tunnusnumero jolle relaatioita haetaan
     * @return tietorakenne jossa viitteet l?ydettyihin relaatioihin
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
        for (int i = 0; i < getLkm(); i++) {
            Relaatio rel = anna(i);
            if (rel.getSettiNro()== settiTunnusNro) loydetyt.add(rel);
        }
        return loydetyt;
    }



    /**
     * Haetaan kaikki kappaleen relaatiot
     * @param kappaleTunnusNro kappaleen tunnusnumero jolle relaatioita haetaan
     * @return tietorakenne jossa viitteet lydetteyihin relaatioihin
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
     *  loytyneet = relaatiot.annaKappaleenRelaatiot(5);
     *  loytyneet.size() === 0; 
     *  loytyneet = relaatiot.annaKappaleenRelaatiot(2);
     *  loytyneet.size() === 2; 
     *  loytyneet.get(0) == rel2 === true;
     *  loytyneet.get(1) == rel5 === true;
     *  loytyneet = relaatiot.annaKappaleenRelaatiot(4);
     *  loytyneet.size() === 1; 
     *  loytyneet.get(0) == rel3 === true;
     * </pre> 
     */  
    public List<Relaatio> annaKappaleenRelaatiot(int kappaleTunnusNro) {
        List <Relaatio> loydetyt = new ArrayList<Relaatio>();
        for (int i = 0; i < getLkm(); i++) {
            Relaatio rel = anna(i);
            if (rel.getKNro() == kappaleTunnusNro) loydetyt.add(rel);
        }
        return loydetyt;
    }


    @Override
    public Iterator<Relaatio> iterator() {
        return null;
    }
    
    
    /**
     * Testiohjelma seteille
     * @param args ei k?yt?ss?
     */
    public static void main(String[] args) {
        Relaatiot relaatiot = new Relaatiot();

        try {
            relaatiot.lueTiedostosta("musa");
        } catch (SailoException e1) {
            System.err.println(e1.getMessage());
        }

        //Relaatio rel1 = new Relaatio(1,1);
        //Relaatio rel2 = new Relaatio(2,1);
        //Relaatio rel3 = new Relaatio(3,1);
        //Relaatio rel4 = new Relaatio(3,2);
        //rel1.rekisteroi();
        //rel2.rekisteroi();
        //rel3.rekisteroi();
        //rel4.rekisteroi();

        //relaatiot.lisaa(rel1);
        //relaatiot.lisaa(rel2);
        //relaatiot.lisaa(rel3);
        //relaatiot.lisaa(rel4);

        //List<Relaatio> relaatioLista = relaatiot.annaRelaatiot(1);       

        //for (Relaatio rel : relaatioLista) {
            //rel.tulosta(System.out);
        //}

        try {
            relaatiot.tallenna("musa");
        } catch (SailoException e) {
            e.printStackTrace();
        }
    }
}
