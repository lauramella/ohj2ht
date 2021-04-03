/**
 * 
 */
package music2;

import java.io.OutputStream;
import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;
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
    private String  bpm     ="";
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
        bpm     ="000";
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
    public String getName() {
        return name;
    }
    
    
    /**
     * @return artisti
     */
    public String getArtist() {
        return artist;
    }
    
    
    /**
     * @return format
     */
    public String getFormat() {
        return format;
    }
    
  
    /**
     * @return label
     */
    public String getLabel() {
        return label;
    }
    
    
    /**
     * @return kappaleen bpm
     */
    public String getBpm() {
        return bpm;
    }
    
    
    /**
     * @return kappaleen pituus
     */
    public String getLength() {
        return length;
    }
    
    
    /**
     * @return kappaleen genre
     */
    public String getGenre() {
        return genre;
    }
    
    
    /**
     * @return style
     */
    public String getStyle() {
        return style;
    }
    
    
    /**
     * @return julkaisuvuosi
     */
    public String getReleased() {
        return released;
    }
    
    
    /**
     * @return maa
     */
    public String getCountry() {
        return country;
    }

    
    /**
     * @return kappaleen info
     */
    public String getInfo() {
        return info;
    }
    
       
    @Override
    public String toString() {
        //return artist + " | " + name;
        return "" +
            getTunnusNro() + "|" +
            artist + "|" +
            name + "|" +
            format + "|" +
            label + "|" +
            bpm + "|" +
            length + "|" +
            genre + "|" +
            style + "|" +
            released + "|" +
            country + "|" + 
            info;
    }
    
    
    /**
     * Asettaa tunnusnumeron ja samalla varmistaa että
     * seuraava numero on aina suurempi kuin tähän mennessä suurin.
     * @param nr asetettava tunnusnumero
     */
    private void setTunnusNro(int nr) {
        tunnusNro = nr;
        if (tunnusNro >= seuraavaNro) seuraavaNro = tunnusNro + 1;
    }
    
    
    /**
     * Selvitää kappaleen tiedot | erotellusta merkkijonosta
     * Pitää huolen että seuraavaNro on suurempi kuin tuleva tunnusNro.
     * @param rivi josta kappaleen tiedot otetaan
     * 
     * @example
     * <pre name="test">
     *   Kappale kappale = new Kappale();
     *   kappale.parse("   3  |  Guy From Downstairs   | Nokia");
     *   kappale.getTunnusNro() === 3;
     *   kappale.toString().startsWith("3|Guy From Downstairs|Nokia|") === true; // on enemmäkin kuin 3 kenttää, siksi loppu |
     *
     *   kappale.rekisteroi();
     *   int n = kappale.getTunnusNro();
     *   kappale.parse(""+(n+20));       // Otetaan merkkijonosta vain tunnusnumero
     *   kappale.rekisteroi();           // ja tarkistetaan että seuraavalla kertaa tulee yhtä isompi
     *   kappale.getTunnusNro() === n+20+1;
     *     
     * </pre>
     */

    public void parse(String rivi) {
        var sb = new StringBuilder(rivi);
        setTunnusNro(Mjonot.erota(sb, '|', getTunnusNro()));
        artist = Mjonot.erota(sb, '|', artist);
        name = Mjonot.erota(sb, '|', name);
        format = Mjonot.erota(sb, '|', format);
        label = Mjonot.erota(sb, '|', label);
        bpm = Mjonot.erota(sb, '|', bpm);
        length = Mjonot.erota(sb, '|', length);
        genre = Mjonot.erota(sb, '|', genre);
        style = Mjonot.erota(sb, '|', style);
        released = Mjonot.erota(sb, '|', released);
        country = Mjonot.erota(sb, '|', country);
        info = Mjonot.erota(sb, '|', info);
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
