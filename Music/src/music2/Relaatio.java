package music2;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * CRC-kortti
|------------------------------------------------------------------------|
| Luokan nimi: Relaatio                              | Avustajat:        |
|-------------------------------------------------------------------------
| Vastuualueet:                                      |                   |
|                                                    | - Kappale         |
| - yhdistää Kappale:en tiettyyn Setti:in            | - Setti           |
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
 * @version 12.3.2021
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
     * Testiohjelma Setille
     * @param args ei käytössä
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
    
    
    
    @Override
    public String toString() {
        return "Relaatio: " + tunnusNro;
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
    
}   
    
    
    
    
    
    
