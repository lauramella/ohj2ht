/**
 * 
 */
package music2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * CRC-kortti
|------------------------------------------------------------------------|
| Luokan nimi: Kappaleet                             | Avustajat:        |
|-------------------------------------------------------------------------
| Vastuualueet:                                      |                   |
|                                                    | - Kappale         |
| - pit‰‰ yll‰ varsinaista rekisteri‰ kappaleista    | -                 |
| - voi lis‰t‰ ja poistaa kappaleen                  | -                 |
| - lukee ja kirjoittaa kappaleet tiedostoon         |                   |
| - osaa etsi‰ ja lajitella                          |                   |
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
public class Kappaleet implements Cloneable {
    
    private static final int MAX_KAPPALEITA = 5;
    private int lkm = 0;
    private String tiedostonNimi = "";
    private Kappale[] alkiot;
    private boolean muutettu = false;

    
    /**
     * Luodaan alustava taulukko
     */
    public Kappaleet() {
        alkiot = new Kappale[MAX_KAPPALEITA];
    }
    
    /**
     * Lis‰‰ uuden kappaleen tietorakenteeseen. Ottaa kappaleen omistukseensa.
     * @param kappale lis‰tt‰v‰n kappaleen viite.
     * @throws SailoException  jos tietorakenne on jo t‰ynn‰
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * Kappaleet kappaleet = new Kappaleet();
     * Kappale kappale1 = new Kappale(), kappale2 = new Kappale();
     * kappaleet.getLkm() === 0;
     * kappaleet.lisaa(kappale1); kappaleet.getLkm() ===1;
     * kappaleet.lisaa(kappale2); kappaleet.getLkm() ===2;
     * kappaleet.lisaa(kappale1); kappaleet.getLkm() ===3;
     * kappaleet.anna(0) === kappale1;
     * kappaleet.anna(1) === kappale2;
     * kappaleet.anna(2) === kappale1;
     * kappaleet.anna(1) == kappale1 === false;
     * kappaleet.anna(1) == kappale2 === true;
     * kappaleet.anna(3) === kappale1; #THROWS IndexOutOfBoundsException
     * kappaleet.lisaa(kappale1); kappaleet.getLkm() === 4;
     * kappaleet.lisaa(kappale1); kappaleet.getLkm() === 5;
     * kappaleet.lisaa(kappale1); #THROWS SailoException
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
     * Palauttaa viitteen i:teen j‰seneen
     * @param i monennenko j‰senen viite halutaan
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
     * Palauttaa kappaleiden lukum‰‰r‰n
     * @return kappaleiden lukum‰‰r‰
     */
    public int getLkm() {
        return lkm;
    }
    
    
    /**
     * Lukee kappaleet tiedostosta
     * @param hakemisto tiedoston hakemisto
     * @throws SailoException jos lukeminen ep‰onnistuu
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
     * @throws SailoException jos talletus ep‰onnistuu
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
     * @param args ei k‰ytˆss‰
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
