package music2;

import java.io.OutputStream;
import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * CRC-kortti
|------------------------------------------------------------------------|
| Luokan nimi: Relaatio                              | Avustajat:        |
|-------------------------------------------------------------------------
| Vastuualueet:                                      |                   |
|                                                    | - Kappale         |
| - yhdist‰‰ Kappale:en tiettyyn Setti:in            | - Setti           |
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
|                                                    |                   |
|-------------------------------------------------------------------------
 * @author laura
 * @version 19.4.2021
 *
 */

public class Relaatio {
    private int     tunnusNro;   
    private int     settiNro;
    private int     kappaleNro;

    private static int seuraavaNro  = 1;    

    /**
     * Alustetaan relaatio  
     * @param kappaleNro kappaleen tunnusnumero 
     * @param settiNro setin tunnusnumero
     */
    public Relaatio(int kappaleNro, int settiNro) {
        this.kappaleNro = kappaleNro;
        this.settiNro = settiNro;
    }


    /**
     * @return palauttaa kappaleen tunnusnumeron
     */
    public int getKNro() {
        return this.kappaleNro;
    }


    /**
     * Antaa relaatiolle seuraavan rekisterinumeron.
     * @return relaation uusi tunnus_nro
     * @example
     * <pre name="test">
     *  Relaatio rel1 = new Relaatio(1,1);
     *  rel1.getTunnusNro() === 0;
     *  rel1.rekisteroi();
     *  Relaatio rel2 = new Relaatio(2,1);
     *  rel2.rekisteroi();
     *  int n1 = rel1.getTunnusNro();
     *  int n2 = rel2.getTunnusNro();
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
    private void setTunnusNro(int nr) {
        tunnusNro = nr;
        if (tunnusNro >= seuraavaNro) seuraavaNro = tunnusNro + 1;
    }


    /**
     * Selvitt‰‰ relaation tiedot | erotellusta merkkijonosta
     * Pit‰‰ huolen ett‰ seuraavaNro on suurempi kuin tuleva tunnusNro.
     * @param rivi josta relaation tiedot otetaan
     * 
     * @example
     * <pre name="test">
     *   Relaatio relaatio = new Relaatio(1,1);
     *   relaatio.parse("   2  |  1   | 1");
     *   relaatio.getTunnusNro() === 2;
     *   relaatio.toString()    === "2|1|1";
     *   relaatio.rekisteroi();
     *   int n = relaatio.getTunnusNro();
     *   relaatio.parse(""+(n+20));       // Otetaan merkkijonosta vain tunnusnumero
     *   relaatio.rekisteroi();           // ja tarkistetaan ett‰ seuraavalla kertaa tulee yht‰ isompi
     *   relaatio.getTunnusNro() === n+20+1;
     *     
     * </pre>
     */
    public void parse(String rivi) {
        var sb = new StringBuilder(rivi);
        setTunnusNro(Mjonot.erota(sb, '|', getTunnusNro()));
        kappaleNro = Mjonot.erota(sb, '|', kappaleNro);
        settiNro = Mjonot.erota(sb, '|', settiNro);
    }


    /**
     * Testiohjelma Setille
     * @param args ei k‰ytˆss‰
     */
    public static void main(String[] args) {
        Relaatio rel1 = new Relaatio(1,1);
        Relaatio rel2 = new Relaatio(2,1);
        Relaatio rel3 = new Relaatio(3,1);
        rel1.rekisteroi();
        rel2.rekisteroi();
        rel3.rekisteroi();

        rel1.tulosta(System.out);
        rel2.tulosta(System.out);
        rel3.tulosta(System.out);
    }


    /**
     * Tulostetaan setin tiedot
     * @param out tietovirta johon tulostetaan 
     */
    public void tulosta(PrintStream out) { 
        out.println("Relaatio: " + tunnusNro);    
    }


    /**
     * Palauttaa relaation tiedot merkkijonona jonka voi tallentaa tiedostoon.
     * @return relaatio tolppaeroteltuna merkkijonona 
     * @example
     * <pre name="test">
     *   Relaatio relaatio = new Relaatio(1,2);
     *   relaatio.parse("   1   |  1 | 2 ");
     *   relaatio.toString()    === "1|1|2";
     * </pre>
     */
    @Override
    public String toString() {
        return "" + getTunnusNro() + "|" + kappaleNro + "|" + settiNro;
    }


    /**
     * Palautetaan setin tunnusNro
     * @return setin tunnusNro
     */
    public int getSettiNro() {
        return settiNro;
    }


    /**
     * Palautetaan relaation tunnusnumero
     * @return relaation tunnusnumero
     */
    public int getTunnusNro() {
        return tunnusNro;
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
        return super.hashCode();
    }    
}   

