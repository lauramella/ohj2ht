/**
 * 
 */
package music2;

import java.io.OutputStream;
import java.io.PrintStream;

import kanta.*;

/**
CRC-kortti
|------------------------------------------------------------------------|
| Luokan nimi: Kappale                               | Avustajat:        |
|-------------------------------------------------------------------------
| Vastuualueet:                                      |                   |
|                                                    | -                 |
| - Tietää kappaleen kentät (nimi, artisti, genre    | -                 |
|   jne.)                                            |                   |
| - Osaa muuttaa merkkijonon (esim|esim|esim jne.)   |                   |
|   tiedoiksi                                        |                   |
| - Osaa tarkistaa tietyn kentän oikeellisuuden      |                   |
| - Osaa antaa merkkijono i:n kentän tiedot          |                   |
| - Osaa laittaa merkkijonon i:neksi kentäksi        |                   |
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
public class Kappale {
    
    private int     tunnusNro;
    private String  artist  ="";
    private String  name    ="";
    private String  format  ="";
    private String  label   ="";
    private int     bpm;
    private String  length  ="";
    private String  genre   ="";
    private String  style   ="";
    private String  released=""; 
    private String  country =""; 
    private String  info    ="";
    
    private static int seuraavaNro  = 1;
    
    /**
     * Tulostetaan henkilön tiedot
     * @param out tietovirta johon tulostetaan 
     */
    public void tulosta(PrintStream out) {
        out.println(String.format("%03d", tunnusNro) + " " + artist + " " + name);
        out.println("Format: " + format);
        out.println("Label: " + label);
        out.println("Bpm: " + String.format("%03d", bpm));
        out.println("Length: " + length);
        out.println("Genre: " + genre);
        out.println("Style: " + style);
        out.println("Released: " + released);
        out.println("Country: " + country);
        out.println("Info: " + info);
    }
    
    
    /**
     * Tulostetaan kappaleen tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
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
     * Apumetodi, jolla saadaan täyettyä testiarvot jäsenelle.
     */
    public void taytaKappaleTiedoilla() {
        artist  ="Alex Pervukhin " + Tarkistus.rand(1000, 9999);
        name    ="This for B";
        format  ="Vinyl";
        label   ="Lac002";
        bpm     =000;
        length  ="";
        genre   ="Electronic";
        style   ="Minimal";
        released="2018"; 
        country ="Ukraine";
        info    ="";
    }
       
    
    /**
     * @return Kappaleen nimi
     */
    public String getNimi() {
        return name;
    }
    

    /**
     * Palauttaa kappaleen tunnusnumeron
     * @return kappaleen tunnusnumero
     */
    public int getTunnusNro() {
        return tunnusNro;
    }
    
    
    /**
     * @param args ei käytössä
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

}
