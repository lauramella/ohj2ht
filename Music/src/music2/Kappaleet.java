/**
 * 
 */
package music2;

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
public class Kappaleet {
    
    private static final int MAX_KAPPALEITA = 5;
    private int lkm = 0;
    private Kappale[] alkiot;

    
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
        if (lkm >= alkiot.length) throw new SailoException("Liikaa alkioita");
        this.alkiot[this.lkm] = kappale;
        lkm++;
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
     * Palauttaa kappaleiden lukum‰‰r‰n
     * @return kappaleiden lukum‰‰r‰
     */
    public int getLkm() {
        return lkm;
    }
    
    
    /**
     * @param args ei k‰ytˆss‰
     */
    public static void main(String[] args) {
        Kappaleet kappaleet = new Kappaleet();
        Kappale kappale1 = new Kappale();
        Kappale kappale2 = new Kappale();
        kappale1.rekisteroi();
        kappale1.taytaKappaleTiedoilla();
        kappale2.rekisteroi();
        kappale2.taytaKappaleTiedoilla();
       
        try {
        kappaleet.lisaa(kappale1);
        kappaleet.lisaa(kappale2);
        }catch (SailoException e) {
            System.err.println(e.getMessage());
        }
        
        System.out.println("======== Kappaleet testi ========");
   
        for (int i=0; i < kappaleet.getLkm(); i++) {
            Kappale kappale = kappaleet.anna(i);
            System.out.println("Kappaleen indeksi: " + i);
            kappale.tulosta(System.out);
        }
    }

}
