package music2;

import java.io.OutputStream;
import java.io.PrintStream;
import fi.jyu.mit.ohj2.Mjonot;


/**
 * CRC-kortti
|------------------------------------------------------------------------|
| Luokan nimi: Setti                                 | Avustajat:        |
|-------------------------------------------------------------------------
| Vastuualueet:                                      |                   |
|                                                    | -                 |
| - pit‰‰ yll‰ varsinaista rekisteri‰ kappaleista    | -                 |
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
 * @version 19.4.2021
 *
 */
public class Setti {   

    private int     tunnusNro;
    private String  name    ="Setti1";

    private static int seuraavaNro  = 1;

    /**
     * Alustetaan setti.
     */
    public Setti() {
        //
    }
    

    /**
     * Annetaan setille uusi nimi
     * @param nimi setin uusi nimi
     */
    public void uusiNimi(String nimi) {
        name = nimi;       
    }


    /**
     * Tulostetaan setin tiedot
     * @param out tietovirta johon tulostetaan 
     */
    public void tulosta(PrintStream out) {
        out.println(String.format("%03d", tunnusNro) + " Setin nimi: " + name);       
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
     *  setti1.rekisteroi();
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
     * Asettaa tunnusnumeron ja samalla varmistaa ett‰
     * seuraava numero on aina suurempi kuin t‰h‰n menness‰ suurin.
     * @param nr asetettava tunnusnumero
     */
    private void setTunnusNro(int nro) {
        tunnusNro = nro;
        if ( tunnusNro >= seuraavaNro ) seuraavaNro = tunnusNro + 1;
    }


    /**
     * Apumetodi, jolla saadaan t‰yetty‰ testiarvot Setille.
     */
    public void taytaSettiTiedoilla() {
        name    = "Setti1";
    }
    
    
    /**
     * Palauttaa setin nimen
     * @return setin nimi
     * @example
     * <pre name="test">
     *   Setti setti = new Setti();
     *   setti.taytaSettiTiedoilla();
     *   setti.getNimi() =R= "Setti1.*";
     * </pre>
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
     * Palauttaa setin tiedot merkkijonona jonka voi tallentaa tiedostoon.
     * @return setti tolppaeroteltuna merkkijonona 
     * @example
     * <pre name="test">
     *   Setti setti = new Setti();
     *   setti.parse("   2   |  Setti1");
     *   setti.toString()    === "2|Setti1";
     * </pre>
     */
    @Override
    public String toString() {
        return "" + getTunnusNro() + "|" + name;
    }


    /**
     * Selvit‰‰ setin tiedot | erotellusta merkkijonosta.
     * Pit‰‰ huolen ett‰ seuraavaNro on suurempi kuin tuleva tunnusnro.
     * @param rivi josta setin tiedot otetaan
     * @example
     * <pre name="test">
     *   Setti setti = new Setti();
     *   setti.parse("   2   |  Setti1");
     *   setti.toString()    === "2|Setti1";
     *   
     *   setti.rekisteroi();
     *   int n = setti.getTunnusNro();
     *   setti.parse(""+(n+20));
     *   setti.rekisteroi();
     *   setti.getTunnusNro() === n+20+1;
     *   setti.toString()     === "" + (n+20+1) + "|Setti1";
     * </pre>
     */
    public void parse(String rivi) {
        StringBuilder sb = new StringBuilder(rivi);
        setTunnusNro(Mjonot.erota(sb, '|', getTunnusNro()));
        name = Mjonot.erota(sb, '|', name);
    }
    
    
    /**
     * Testiohjelma setille
     * @param args ei k‰ytˆss‰
     */
    public static void main(String[] args) {
        Setti setti1 = new Setti();
        setti1.rekisteroi();
        setti1.taytaSettiTiedoilla();
        setti1.tulosta(System.out);
    }
}
