package music2;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import kanta.Tarkistus;


/**
 * CRC-kortti
|------------------------------------------------------------------------|
| Luokan nimi: Setti                                 | Avustajat:        |
|-------------------------------------------------------------------------
| Vastuualueet:                                      |                   |
|                                                    | - Kappale         |
| - pit‰‰ yll‰ varsinaista rekisteri‰ kappaleista    | - Kappaleet       |
|   tietyss‰ listassa                                | -                 |
| - lukee ja kirjoittaa kappaleet tiedostoon         |                   |
|   pyyt‰m‰ll‰ apua avustajiltaan                    |                   |
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
public class Setti {   
    
    private int     tunnusNro;
    private String  name    ="";
    private int     kappaleetMaara;
    
    private static int seuraavaNro  = 1;
    
    
    /**
     * Alustetaan setti. Toistaiseksi ei tarvitse tehd‰ mit‰‰n
     */
    public Setti() {
    // Viel‰ ei tarvita mit‰‰n
    }
    
        
    
    /**
     * Tulostetaan setin tiedot
     * @param out tietovirta johon tulostetaan 
     */
    public void tulosta(PrintStream out) {
        out.println(String.format("%03d", tunnusNro) + " Setin nimi: " + name);
        out.println("Kappaleita yhteens‰: " + kappaleetMaara);       
    }
     
    
    /**
     * Tulostetaan kappaleen tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    
    /**
     * Antaa setille seuraavan rekisterinumeron.
     * @return setin uusi tunnus_nro
     * @example
     * <pre name="test">
     *  Setti setti1 = new Setti();
     *  setti1.getTunnusNro() === 0;
     *  setti11.rekisteroi();
     *  Setti setti2 = new Setti();
     *  setti2.rekisteroi();
     *  int n1 = setti1.getTunnusNro();
     *  int n2 = setti2.getTunnusNro();
     *  n1 === n2-1;
     *  </pre>
     */
    public int rekisteroi() {
        this.tunnusNro = seuraavaNro;
        seuraavaNro++;
        return this.tunnusNro;
    }
    
    
    /**
     * Apumetodi, jolla saadaan t‰yetty‰ testiarvot Setille.
     */
    public void taytaSettiTiedoilla() {
        name    = "Setti1";
        kappaleetMaara = 0;
    }
    
    /**
     * @return Kappaleen nimi
     */
    public String getNimi() {
        return name;
    }
    

    /**
     * Palautetaan setin oma id
     * @return setin tunnusnumero
     */
    public int getTunnusNro() {
        return tunnusNro;
    }
  
    
    /**
     * Testiohjelma Setille
     * @param args ei k‰ytˆss‰
     */
    public static void main(String[] args) {
        Setti setti1 = new Setti();
        setti1.rekisteroi();
        setti1.taytaSettiTiedoilla();
    
        setti1.tulosta(System.out);
         
    }
    
    @Override
    public String toString() {
        return name;
    }
   
}
