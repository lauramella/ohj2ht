/**
 * 
 */
package music2;

/**
 * CRC - kortti
|------------------------------------------------------------------------|
| Luokan nimi: Music                                 | Avustajat:        |
|-------------------------------------------------------------------------
| Vastuualueet:                                      |                   |
|                                                    | - Kappale         |
| - Huolehtii Kappaleet ja Setit -luokkien yhteis-   | - Kappaleet       |
|   ty�st�                                           | - Setti           |
| - Lukee ja kirjoittaa tiedostoon kysym�ll� apua    | - Setit           |
|   avustajiltaan                                    | - Relaatio        |
|                                                    | - Relaatiot       |
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
 * @version 28.2.2021
 *
 */
public class Music {
    
    private Kappaleet kappaleet = new Kappaleet();
    
    /**
     * Lis�t��n uusi kappale
     * @param kappale lis�tt�v� kappale
     * @throws SailoException js lis��minen ei onnistu
     */
    public void lisaa(Kappale kappale) throws SailoException {
        this.kappaleet.lisaa(kappale);
    }
    
    
    /**
     * @return kappaleiden lukum��r�
     */
    public int getKappaleet() {
        return this.kappaleet.getLkm();
    }
    
    
    /**
     * Antaa kerhon i:n kappaleen
     * @param i monesko kappale (alkaa 0:sta)
     * @return kappale paikasta i
     */
    public Kappale annaKappale(int i) {
        return kappaleet.anna(i);
    }
    

    /**
     * @param args ei k�yt�ss�
     */
    public static void main(String[] args) {
        Music music = new Music();
        Kappale kappale1 = new Kappale();
        Kappale kappale2 = new Kappale();
        kappale1.rekisteroi();
        kappale1.taytaKappaleTiedoilla();
        kappale2.rekisteroi();
        kappale2.taytaKappaleTiedoilla();
        
        try {
            music.lisaa(kappale1);
            music.lisaa(kappale2);
            music.lisaa(kappale2);
            music.lisaa(kappale2);
            music.lisaa(kappale2);
            music.lisaa(kappale2);
        } catch (SailoException e) {
            System.err.println(e.getMessage());
        }

        
        for (int i=0; i < music.getKappaleet(); i++) {
            Kappale kappale = music.annaKappale(i);
            kappale.tulosta(System.out);
        }
    
    }

}
