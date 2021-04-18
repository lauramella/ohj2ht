
/**
 * 
 */
package music2;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Comparator;

import fi.jyu.mit.ohj2.Mjonot;
import kanta.*;

/**
CRC-kortti
|------------------------------------------------------------------------|
| Luokan nimi: Kappale                               | Avustajat:        |
|-------------------------------------------------------------------------
| Vastuualueet:                                      |                   |
|                                                    | -                 |
| - Tiet kappaleen kentt (nimi, artisti, genre    | -                 |
|   jne.)                                            |                   |
| - Osaa muuttaa merkkijonon (esim|esim|esim jne.)   |                   |
|   tiedoiksi                                        |                   |
| - Osaa tarkistaa tietyn kentn oikeellisuuden      |                   |
| - Osaa antaa merkkijono i:n kentn tiedot          |                   |
| - Osaa laittaa merkkijonon i:neksi kentksi        |                   |
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
public class Kappale implements Cloneable {
    private int     tunnusNro;
    private String  artist  ="";
    private String  name    ="";
    private String  format  ="";
    private String  label   ="";
    private int     bpm;
    private String  style   ="";
    private String  genre   ="";
    private int     length;
    private String  released=""; 
    private String  country =""; 
    private String  info    ="";
    
    private static int seuraavaNro  = 1;
    
    
    /**
     * Alustetaan kappaleen merkkijono-attribuuti tyhjiksi jonoiksi
     * ja tunnusnro = 0.
     */
    public Kappale() {
        // Toistaiseksi ei tarvita mitn
    }
    
    
    /**
     * Palauttaa jsenen kenttien lukumrn
     * @return kenttien lukumr
     */
    public int getKenttia() {
        return 12;
    }
    
    
    /**
    * Eka kentt joka on mieleks kysyttvksi
    * @return ekan kentn indeksi
    */
    public int ekaKentta() {
        return 1;
    }
    
    
    /**
     * Antaa kappaleelle seuraavan tunnusnumeron.
     * @return kappaleen uusi tunnusNro
     * @example
     * <pre name="test">
     *  Kappale kappale1 = new Kappale();
     *  kappale1.getTunnusNro() === 0;
     *  kappale1.rekisteroi();
     *  Kappale kappale2 = new Kappale();
     *  kappale2.rekisteroi();
     *  int n1 = kappale1.getTunnusNro();
     *  int n2 = kappale2.getTunnusNro();
     *  n1 === n2-1;
     *  </pre>
     */
    public int rekisteroi() {
        this.tunnusNro = seuraavaNro;
        seuraavaNro++;
        return this.tunnusNro;
    } 
    
    
    /**
     * Antaa k:n kentn sislln merkkijonona
     * @param k monenenko kentn sislt palautetaan
     * @return kentn sislt merkkijonona
     */
    public String anna(int k) {
        switch ( k ) {
        case 0: return "" + tunnusNro;
        case 1: return "" + artist;
        case 2: return "" + name;
        case 3: return "" + format;
        case 4: return "" + label;
        case 5: return "" + bpm;
        case 6: return "" + style;
        case 7: return "" + genre;
        case 8: return "" + length;
        case 9: return "" + released;
        case 10: return "" + country;
        case 11: return "" + info;
        default: return "Moikka";
        }
    }
    
    
    /** 
     * Antaa k:n kentn sislln merkkijonona 
     * @param k monenenko kentn sislt palautetaan 
     * @return kentn sislt merkkijonona 
     */ 
    public String getAvain(int k) { 
        switch ( k ) { 
        case 0: return "" + tunnusNro; 
        case 1: return "" + artist.toUpperCase(); 
        case 2: return "" + name.toUpperCase(); 
        case 3: return "" + format; 
        case 4: return "" + label; 
        case 5: return "" + bpm; 
        case 6: return "" + style; 
        default: return "Terve"; 
        } 
    }

    
    
    /**
     * Asettaa k:n kentn arvoksi parametrina tuodun merkkijonon arvon
     * @param k kuinka monennen kentn arvo asetetaan
     * @param jono jonoa joka asetetaan kentn arvoksi
     * @return null jos asettaminen onnistuu, muuten vastaava virheilmoitus.
     * @example
     * <pre name="test">
     *   Kappale kappale = new Kappale();
     *   kappale.aseta(1,"Alex Pervukhin") === null;
     *   kappale.aseta(2,"This for B") === null;
     *   kappale.aseta(3,"Vinyl") === null; 
     * </pre>
     */
    public String aseta(int k, String jono) {
        String tjono = jono.trim();
        StringBuilder sb = new StringBuilder(tjono);
        switch ( k ) {
        case 0:
            setTunnusNro(Mjonot.erota(sb, '§', getTunnusNro()));
            return null;
        case 1:
            artist = tjono;
            return null;
        case 2:
            name = tjono;
            return null;
        case 3:
            format = tjono;
            return null;
        case 4:
            label = tjono;
            return null;
        case 5:
            bpm = Mjonot.erota(sb, '§', bpm);
            return null;
        case 6:
            style = tjono;
            return null;
        case 7:
            genre = tjono;
            return null;
        case 8:
            length = Mjonot.erota(sb, '§', length);
            return null;
        case 9:
            released = tjono;
            return null;
        case 10:
            country = tjono;
            return null;
        case 11:
            info = tjono;
            return null;
        default:
            return "Moikka";
        }
    }
    
    
    /**
     * Palauttaa k:tta kappaleen kentt vastaavan kysymyksen
     * @param k kuinka monennen kentn kysymys palautetaan (0-alkuinen)
     * @return k:netta kentt vastaava kysymys
     */
    public String getKysymys(int k) {
        switch ( k ) {
        case 0: return "tunnus nro";
        case 1: return "artist";
        case 2: return "name";
        case 3: return "format";
        case 4: return "label";
        case 5: return "bpm";
        case 6: return "style";
        case 7: return "genre";
        case 8: return "length";
        case 9: return "released";
        case 10: return "country";
        case 11: return "info";
        default: return "Moikka";
        }
    }
    
    
    /**
     * Tehdn identtinen klooni kappaleesta
     * @return Object kloonattu kappale
     * @example
     * <pre name="test">
     * #THROWS CloneNotSupportedException 
     *   Kappale kappale = new Kappale();
     *   kappale.parse("   3  |  Alex Pervukhin   | This For B");
     *   Kappale kopio = kappale.clone();
     *   kopio.toString() === kappale.toString();
     *   kappale.parse("   4  |  artisti2  | biisi1");
     *   kopio.toString().equals(kappale.toString()) === false;
     * </pre>
     */
    @Override
    public Kappale clone() throws CloneNotSupportedException {
        Kappale uusi;
        uusi = (Kappale) super.clone();
        return uusi;
    }
    
    
    /**
     * Tutkii onko kappaleen tiedot samat kuin parametrina tuodun kappaleen tiedot
     * @param kappale johon verrataan
     * @return true jos kaikki tiedot samat, false muuten
     * @example
     * <pre name="test">
     *   Kappale kappale1 = new Kappale();
     *   kappale1.parse("   3  |  Alex   | This For B");
     *   Kappale kappale2 = new Kappale();
     *   kappale2.parse("   3  |  Alex   | This For B");
     *   Kappale kappale3 = new Kappale();
     *   kappale3.parse("   3  |  Alex   | Juuu");
     *   
     *   kappale1.equals(kappale2) === true;
     *   kappale2.equals(kappale1) === true;
     *   kappale1.equals(kappale3) === false;
     *   kappale3.equals(kappale2) === false;
     * </pre>
     */
    public boolean equals(Kappale kappale) {
        if ( kappale == null ) return false;
        for (int k = 0; k < getKenttia(); k++)
            if ( !anna(k).equals(kappale.anna(k)) ) return false;
        return true;
    }
    
    
    @Override
        public boolean equals(Object kappale) {
        if ( kappale instanceof Kappale ) return equals((Kappale)kappale);
        return false;
    }

    
    
    /**
     * Apumetodi, jolla saadaan tyetty testiarvot jsenelle.
     */
    public void taytaKappaleTiedoilla() {
        artist  ="Alex Pervukhin " + Tarkistus.rand(1000, 9999);
        name    ="This for B";
        format  ="Vinyl";
        label   ="Lac002";
        bpm     = 0;
        style   ="Minimal";
        genre   ="Electronic";
        length  = 0;
        released="2018"; 
        country ="Ukraine";
        info    ="";
    }
       
    
    /**
     * @return Kappaleen nimi
     */
    public String getName() {
        return artist + " - " + name;
    }
    
    
    /**
     * Palauttaa kappaleen tiedot merkkijonona jonka voi tallentaa tiedostoon.
     * @return kappale tolppaeroteltuna merkkijonona 
     * @example
     * <pre name="test">
     *   Kappale kappale = new Kappale();
     *   kappale.parse("   3  |  Alex   | This For B");
     *   kappale.toString().startsWith("3|Alex|This For B|") === true; // on enemmkin kuin 3 kentt, siksi loppu |
     * </pre>  
     */
    @Override
    public String toString() {
        //return artist + " - " + name;
        StringBuilder sb = new StringBuilder("");
        String erotin = "";
        for (int k = 0; k < getKenttia(); k++) {
            sb.append(erotin);
            sb.append(anna(k));
            erotin = "|";
        }
       return sb.toString();            
    }  
    
    
    /**
     * Asettaa tunnusnumeron ja samalla varmistaa ett
     * seuraava numero on aina suurempi kuin thn menness suurin.
     * @param nr asetettava tunnusnumero
     */
    private void setTunnusNro(int nr) {
        tunnusNro = nr;
        if (tunnusNro >= seuraavaNro) seuraavaNro = tunnusNro + 1;
    }
    
    
    /**
     * Palauttaa kappaleen tunnusnumeron
     * @return kappaleen tunnusnumero
     */
    public int getTunnusNro() {
        return tunnusNro;
    }
    
    
    /**
     * Selvit kappaleen tiedot | erotellusta merkkijonosta
     * Pit huolen ett seuraavaNro on suurempi kuin tuleva tunnusNro.
     * @param rivi josta kappaleen tiedot otetaan
     * 
     * @example
     * <pre name="test">
     *   Kappale kappale = new Kappale();
     *   kappale.parse("   3  |  Guy From Downstairs   | Nokia");
     *   kappale.getTunnusNro() === 3;
     *   kappale.toString().startsWith("3|Guy From Downstairs|Nokia|") === true; // on enemmkin kuin 3 kentt, siksi loppu |
     *
     *   kappale.rekisteroi();
     *   int n = kappale.getTunnusNro();
     *   kappale.parse(""+(n+20));       // Otetaan merkkijonosta vain tunnusnumero
     *   kappale.rekisteroi();           // ja tarkistetaan ett seuraavalla kertaa tulee yht isompi
     *   kappale.getTunnusNro() === n+20+1;
     *     
     * </pre>
     */

    public void parse(String rivi) {
        StringBuffer sb = new StringBuffer(rivi);
        for (int k = 0; k < getKenttia(); k++)
            aseta(k, Mjonot.erota(sb, '|'));
    }
    
    
    /**
     * @param args ei kytss
     */
    public static void main(String[] args) {
        Kappale kappale1 = new Kappale();
        Kappale kappale2 = new Kappale();
        
        kappale1.rekisteroi();
        kappale2.rekisteroi();
        
        kappale1.tulosta(System.out);
        kappale1.taytaKappaleTiedoilla(); 
        kappale1.tulosta(System.out);
        
        kappale2.tulosta(System.out);
        kappale2.taytaKappaleTiedoilla();
        kappale2.tulosta(System.out);
          
    }
    
    
    /**
     * Tulostetaan henkiln tiedot
     * @param out tietovirta johon tulostetaan 
     */
    public void tulosta(PrintStream out) {
        out.println(String.format("%03d", tunnusNro) + " " + artist + " " + name);
        out.println("Format: " + format);
        out.println("Label: " + label);
        out.println("Bpm: " + bpm);
        out.println("Length: " + length);
        out.println("Genre: " + genre);
        out.println("Style: " + style);
        out.println("Released: " + released);
        out.println("Country: " + country);
        out.println("Info: " + info);
    }
    
    
    /**
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta1(PrintStream out) {
        out.println(artist + " - " + name);
    }
    
    
    /**
     * Tulostetaan kappaleen tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta1(OutputStream os) {
        tulosta1(new PrintStream(os));
    }
        
    
    /**
     * Tulostetaan kappaleen tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }


    @Override
    public int hashCode() {
        return tunnusNro;
    }
    
    /**
     * @author laura
     * @version 14.4.2021
     *
     */
    public static class Vertailija implements Comparator<Kappale> {

        private int k;
        /**
         * @param k mink kentn mukaan vertaillaan
         */
        public Vertailija(int k) {
            this.k = k;
        }
        
        /**
         * @return kokonaisluku
         */
        @Override
        public int compare(Kappale kappale1, Kappale kappale2) {
            return kappale1.getAvain(k).compareTo(kappale2.getAvain(k));
        }  

        /**
         * @param o1 eka
         * @param o2 toka
         * @return kokonaisluku
         */
        @SuppressWarnings("unused")
        public int compare1(Kappale o1, Kappale o2) {
            // TODO Auto-generated method stub
            return 0;
        }}

}

