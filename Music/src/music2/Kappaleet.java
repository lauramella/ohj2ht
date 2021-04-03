/**
 * 
 */
package music2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * CRC-kortti
|------------------------------------------------------------------------|
| Luokan nimi: Kappaleet                             | Avustajat:        |
|-------------------------------------------------------------------------
| Vastuualueet:                                      |                   |
|                                                    | - Kappale         |
| - pitää yllä varsinaista rekisteriä kappaleista    | -                 |
| - voi lisätä ja poistaa kappaleen                  | -                 |
| - lukee ja kirjoittaa kappaleet tiedostoon         |                   |
| - osaa etsiä ja lajitella                          |                   |
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
 * @version 27.2.2021
 *
 */
public class Kappaleet implements Iterable<Kappale> {
    
    private static final int MAX_KAPPALEITA = 5;
    private int lkm = 0;
    private String kokoNimi = "";
    private String tiedostonNimi = "kappaleet";
    private Kappale[] alkiot;
    private boolean muutettu = false;

    
    /**
     * Luodaan alustava taulukko
     */
    public Kappaleet() {
        alkiot = new Kappale[MAX_KAPPALEITA];
    }
    
    /**
     * Lisää uuden kappaleen tietorakenteeseen. Ottaa kappaleen omistukseensa.
     * @param kappale lisättävän kappaleen viite.
     * @throws SailoException  jos tietorakenne on jo täynnä
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * Kappaleet kappaleet = new Kappaleet();
     * Kappale kappale1 = new Kappale(), kappale2 = new Kappale();
     * kappaleet.getLkm() === 0;
     * kappaleet.lisaa(kappale1); kappaleet.getLkm() ===1;
     * kappaleet.lisaa(kappale2); kappaleet.getLkm() ===2;
     * kappaleet.lisaa(kappale1); kappaleet.getLkm() ===3;
     * Iterator<Kappale> it = kappaleet.iterator(); 
     * it.next() === kappale1;
     * it.next() === kappale2; 
     * it.next() === kappale1;  
     * kappaleet.lisaa(kappale1); kappaleet.getLkm() === 4;
     * kappaleet.lisaa(kappale1); kappaleet.getLkm() === 5;
     */   
    public void lisaa(Kappale kappale) throws SailoException{
        if (lkm >= alkiot.length) 
            alkiot = Arrays.copyOf(alkiot, alkiot.length+10);
            //throw new SailoException("Liikaa alkioita");
        this.alkiot[this.lkm] = kappale;
        lkm++;
        muutettu = true;
    }
    
    /**
     * Palauttaa viitteen i:teen jäseneen
     * @param i monennenko jäsenen viite halutaan
     * @return viite kappaleeseen, jonka indeksi on i
     * @throws IndexOutOfBoundsException jos i ei ole sallitulla alueella
     */
    public Kappale anna(int i) throws IndexOutOfBoundsException{
        if (i < 0 || lkm <=i)
            throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
        return alkiot[i];
    }
    
    /**
     * @param knro kappaleen tunnusnumero
     * @return palauttaa kappaleen, jolla sama tunnusnumero
     */
    public Kappale kappaleTunnus(int knro) {
        for (int i=0; i < this.getLkm(); i++) {
        if (alkiot[i].getTunnusNro()== knro)
            return alkiot[i];
        }
        return null;
    }
    
    
    /**
     * Palauttaa kappaleiden lukumäärän
     * @return kappaleiden lukumäärä
     */
    public int getLkm() {
        return lkm;
    }
    
    
    /**
     * Lukee kappaleet tiedostosta
     * @param hakemisto tiedoston hakemisto
     * @throws SailoException jos lukeminen epäonnistuu
     */
    public void lueTiedostosta(String hakemisto) throws SailoException {
        String nimi = hakemisto + "/kappaleet.dat";
        File ftied = new File(nimi);
        try (Scanner fi = new Scanner(new FileInputStream(ftied))) { 
            while ( fi.hasNext() ) {
                String s = "";
                s = fi.nextLine();
                Kappale kappale = new Kappale();
                kappale.parse(s); //vois palauttaa jotain?
                lisaa(kappale);
            }
            muutettu = false;
        } catch ( FileNotFoundException e ) {
            throw new SailoException("Ei saa luettua tiedostoa " + nimi);
        //} catch ( IOException e ) {
            //throw new SailoException("Ongelmia tiedoston kanssa: " + e.getMessage());
        }
    }
    
    
    /**
     * Tallentaa kappaleet tiedostoon.
     * Tiedoston muoto:
     * <pre>
     * 1 |Hoodlove      |Kozac          |Fasten Musique    |   |    |vinyl |    |      |Electronic|Techno |           |Germany |infoa..
     * 3 |This for B    |Alex Pervukhin |Lac002            |   |    |vinyl |    |      |Electronic|Minimal|2018       |Ukraine |  
     * </pre> 
     * @param tiednimi tallennettavan tiedoston nimi
     * @throws SailoException jos talletus epäonnistuu
     */
    public void tallenna(String tiednimi) throws SailoException {
        if ( !muutettu ) return;
        if (!new File(tiednimi).exists()) new File(tiednimi).mkdir();
        //boolean muutettu true tallenna
        File ftied = new File(tiednimi + "/kappaleet.dat");
        try (PrintStream fo = new PrintStream(new FileOutputStream(ftied, false))) {
            for (int i = 0; i < getLkm(); i++) {
                Kappale kappale = anna(i);
                fo.println(kappale);
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Tiedosto " + ftied.getAbsolutePath() + " ei aukea.");
            return;
        } 
        muutettu = false;
    }
    
    
    /**
     * Korvaa kappaleen tietorakenteessa.  Ottaa kappaleen omistukseensa.
     * Etsitään samalla tunnusnumerolla oleva kappale.  Jos ei löydy,
     * niin lisätään uutena jäsenenä.
     * @param kappale lisätäävän kappaleen viite.  Huom tietorakenne muuttuu omistajaksi
     * @throws SailoException jos tietorakenne on jo täynnä
     * <pre name="test">
     * #THROWS SailoException,CloneNotSupportedException
     * #PACKAGEIMPORT
     * Kappaleet kappaleet = new Kappaleet();
     * Kappale kappale1 = new Kappale(), kappale2 = new Kappale();
     * kappale1.rekisteroi(); kappale2.rekisteroi();
     * kappaleet.getLkm() === 0;
     * kappaleet.korvaaTaiLisaa(kappale1); kappaleet.getLkm() === 1;
     * kappaleet.korvaaTaiLisaa(kappale2); kappaleet.getLkm() === 2;
     * Kappale kappale3 = kappale1.clone();
     * kappale3.aseta(3,"kkk");
     * Iterator<Kappale> it = kappaleet.iterator();
     * it.next() == kappale1 === true;
     * kappaleet.korvaaTaiLisaa(kappale3); kappaleet.getLkm() === 2;
     * it = kappaleet.iterator();
     * Kappale k0 = it.next();
     * k0 === kappale3;
     * k0 == kappale3 === true; 
     * k0 == kappale1 === false;
     * </pre>
     */
    public void korvaaTaiLisaa(Kappale kappale) throws SailoException {
       int id = kappale.getTunnusNro();
       for (int i = 0; i < lkm; i++) {
           if ( alkiot[i].getTunnusNro() == id ) {
               alkiot[i] = kappale;
               muutettu = true;
               return;
           }
       }
       lisaa(kappale);
    }
    
    
    /**
     * Luokka kappaleiden iteroimiseksi.
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * #PACKAGEIMPORT
     * #import java.util.*;
     * 
     * Kappaleet kappaleet = new Kappaleet();
     * Kappale kappale1 = new Kappale(), kappale2 = new Kappale();
     * kappale1.rekisteroi(); kappale2.rekisteroi();
     *
     * kappaleet.lisaa(kappale1); 
     * kappaleet.lisaa(kappale2); 
     * kappaleet.lisaa(kappale1); 
     * 
     * StringBuilder ids = new StringBuilder(30);
     * for (Kappale kappale:kappaleet)   // Kokeillaan for-silmukan toimintaa
     *   ids.append(" "+kappale.getTunnusNro());           
     * 
     * String tulos = " " + kappale1.getTunnusNro() + " " + kappale2.getTunnusNro() + " " + kappale1.getTunnusNro();
     * 
     * ids.toString() === tulos; 
     * 
     * ids = new StringBuilder(30);
     * for (Iterator<Kappale>  i=kappaleet.iterator(); i.hasNext(); ) { // ja iteraattorin toimintaa
     *   Kappale kappale = i.next();
     *   ids.append(" "+kappale.getTunnusNro());           
     * }
     * 
     * ids.toString() === tulos;
     * 
     * Iterator<Kappale>  i=kappaleet.iterator();
     * i.next() == kappale1  === true;
     * i.next() == kappale2  === true;
     * i.next() == kappale1  === true;
     * 
     * i.next();  #THROWS NoSuchElementException
     *  
     * </pre>
     */
    public class KappaleetIterator implements Iterator<Kappale> {
        private int kohdalla = 0;
    
        /**
         * Onko olemassa vielä seuraavaa kappaletta
         * @see java.util.Iterator#hasNext()
         * @return true jos on vielä kappaleita
         */
        @Override
        public boolean hasNext() {
            return kohdalla < getLkm();
        }


        /**
         * Annetaan seuraava kappale
         * @return seuraava kappale
         * @throws NoSuchElementException jos seuraava alkiota ei enää ole
         * @see java.util.Iterator#next()
         */
        @Override
        public Kappale next() throws NoSuchElementException {
            if ( !hasNext() ) throw new NoSuchElementException("Ei oo");
            return anna(kohdalla++);
        }
        
        
        /**
         * Tuhoamista ei ole toteutettu
         * @throws UnsupportedOperationException aina
         * @see java.util.Iterator#remove()
         */
        @Override
        public void remove() throws UnsupportedOperationException {
            throw new UnsupportedOperationException("Me ei poisteta");
            }
        }
    
    /**
     * Palautetaan iteraattori kappaleistaan.
     * @return kappale iteraattori
     */
    @Override
    public Iterator<Kappale> iterator() {
        return new KappaleetIterator();
    }
    
    
    /** 
     * Palauttaa "taulukossa" hakuehtoon vastaavien kappaleiden viitteet 
     * @param hakuehto hakuehto 
     * @param k etsittävän kentän indeksi  
     * @return tietorakenteen löytyneistä kappaleista 
     * @example 
     * <pre name="test"> 
     * #THROWS SailoException  
     *   Kappaleet kappaleet = new Kappaleet(); 
     *   Kappale kappale1 = new Kappale(); kappale1.parse("1|Alex|This For B|Vinyl|"); 
     *   Kappale kappale2 = new Kappale(); kappale2.parse("2|Guy From Downstairs||Vinyl|"); 
     *   Kappale kappale3 = new Kappale(); kappale3.parse("3|Sebastian Eric|Not This Time||Tzinah Records|130"); 
     *   Kappale kappale4 = new Kappale(); kappale4.parse("4|Runy|Ice Queen|digi"); 
     *   Kappale kappale5 = new Kappale(); kappale5.parse("5|Robag Wruhme|Yes|Digi"); 
     *   kappaleet.lisaa(kappale1); kappaleet.lisaa(kappale2); kappaleet.lisaa(kappale3); kappaleet.lisaa(kappale4); kappaleet.lisaa(kappale5);
     *   // TODO: toistaiseksi palauttaa kaikki kappaleet 
     * </pre> 
     */ 
    @SuppressWarnings("unused")
    public Collection<Kappale> etsi(String hakuehto, int k) { 
        Collection<Kappale> loytyneet = new ArrayList<Kappale>(); 
        for (Kappale kappale : this) { 
            loytyneet.add(kappale);  
        } 
        return loytyneet; 
    }
    
    
    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Kappaleet kappaleet = new Kappaleet();
        
        try {
            kappaleet.lueTiedostosta("musa");
        } catch (SailoException e1) {
            System.err.println(e1.getMessage());
        }
        
        Kappale kappale1 = new Kappale();
        Kappale kappale2 = new Kappale();
        kappale1.rekisteroi();
        kappale1.taytaKappaleTiedoilla();
        kappale2.rekisteroi();
        kappale2.taytaKappaleTiedoilla();

        try {
            kappaleet.lisaa(kappale1);
            kappaleet.lisaa(kappale2);

            System.out.println("======== Kappaleet testi ========");

            for (int i=0; i < kappaleet.getLkm(); i++) {
                Kappale kappale = kappaleet.anna(i);
                System.out.println("Kappaleen indeksi: " + i);
                kappale.tulosta(System.out);
            }
        }catch (SailoException e) {
            System.err.println(e.getMessage());
        }
        
        try {
            kappaleet.tallenna("musa");
        } catch (SailoException e) {
            e.printStackTrace();
        }        
    }

}
