/**
 * 
 */
package music2;

import java.util.List;

/**
 * CRC - kortti
|------------------------------------------------------------------------|
| Luokan nimi: Music                                 | Avustajat:        |
|-------------------------------------------------------------------------
| Vastuualueet:                                      |                   |
|                                                    | - Kappale         |
| - Huolehtii Kappaleet ja Setit -luokkien yhteis-   | - Kappaleet       |
|   tyˆst‰                                           | - Setti           |
| - Lukee ja kirjoittaa tiedostoon kysym‰ll‰ apua    | - Setit           |
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
    private Setit setit = new Setit();
    private Relaatiot relaatiot = new Relaatiot();
    
    /**
     * Lis‰t‰‰n uusi kappale
     * @param kappale lis‰tt‰v‰ kappale
     * @throws SailoException js lis‰‰minen ei onnistu
     */
    public void lisaa(Kappale kappale) throws SailoException {
        this.kappaleet.lisaa(kappale);
    }
    
    /**
     * Lis‰t‰‰n uusi setti
     * @param setti lis‰tt‰v‰ setti
     */
    public void lisaa(Setti setti) {
        setit.lisaa(setti);
    }
    
    /**
     * Lis‰t‰‰n uusi relaatio
     * @param rel lis‰tt‰v‰ relaatio
     */
    public void lisaa(Relaatio rel) {
        relaatiot.lisaa(rel);
    }
   
    

    /**
     * @param args ei k‰ytˆss‰
     */
    public static void main(String[] args) {
        Music music = new Music();
        Kappale kappale1 = new Kappale();
        Kappale kappale2 = new Kappale();
        Kappale kappale3 = new Kappale();
        Kappale kappale4 = new Kappale();
        Kappale kappale5 = new Kappale();
        kappale1.rekisteroi();
        kappale1.taytaKappaleTiedoilla();
        kappale2.rekisteroi();
        kappale2.taytaKappaleTiedoilla();
        kappale3.rekisteroi();
        kappale3.taytaKappaleTiedoilla();
        kappale4.rekisteroi();
        kappale4.taytaKappaleTiedoilla();
        kappale5.rekisteroi();
        kappale5.taytaKappaleTiedoilla();
        
        try {
            music.lisaa(kappale1);
            music.lisaa(kappale2);
            music.lisaa(kappale3);
            music.lisaa(kappale4);
            music.lisaa(kappale5);
            music.lisaa(kappale2);
        } catch (SailoException e) {
            System.err.println(e.getMessage());
        }

        
        for (int i=0; i < music.getKappaleet(); i++) {
            Kappale kappale = music.annaKappale(i);
            kappale.tulosta(System.out);
        }
       
        // ============================SETIT===================================
        
        Setti setti1 = new Setti();
        Setti setti2 = new Setti();
        Setti setti3 = new Setti();
        setti1.rekisteroi();
        setti2.rekisteroi();
        setti3.rekisteroi();
        setti1.taytaSettiTiedoilla();   
        setti2.taytaSettiTiedoilla();
        setti3.taytaSettiTiedoilla();
        music.lisaa(setti1);
        music.lisaa(setti2);
        music.lisaa(setti3);
        
        System.out.println("============= Setit testi =================");
        
        List<Setti> settiLista = music.annaSetit();
        for (Setti set : settiLista) {
            set.tulosta(System.out);
       }
        
       //====================RELAATIOT===========================================
        Relaatio rel1 = new Relaatio(1,1);
        Relaatio rel2 = new Relaatio(2,1);
        Relaatio rel3 = new Relaatio(3,1);
        Relaatio rel4 = new Relaatio(1,2);
        Relaatio rel5 = new Relaatio(4,1);
        rel1.rekisteroi();
        rel2.rekisteroi();
        rel3.rekisteroi();
        rel4.rekisteroi();
        rel5.rekisteroi();
        
        music.lisaa(rel1);
        music.lisaa(rel2);
        music.lisaa(rel3);
        music.lisaa(rel4);
        
        System.out.println("============= Setit testi =================");
        
        List<Relaatio> relaatioLista = music.annaRelaatiot(1);                        //anna kaikki relaatiot, jotka setId=1
        
          for (Relaatio rel : relaatioLista) {
                   rel.tulosta(System.out);
              }
    
    }
    
    
    
    /**
     * @return lista seteist‰
     */
    public List<Setti> annaSetit () {
        return setit.annaSetit();
    }
    
    /**
     * @param setId setin tunnusnumero
     * @return lista tietyn setin relaatioista
     */
    public List<Relaatio> annaRelaatiot(int setId) {
        return relaatiot.annaRelaatiot(setId);
    }
    
    
    /**
     * @return kappaleiden lukum‰‰r‰
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

}
