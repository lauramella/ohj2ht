/**
 * 
 */
package music2;

import java.util.Collection;
import java.util.List;

/**
 * CRC - kortti
|------------------------------------------------------------------------|
| Luokan nimi: Music                                 | Avustajat:        |
|-------------------------------------------------------------------------
| Vastuualueet:                                      |                   |
|                                                    | - Kappale         |
| - Huolehtii Kappaleet ja Setit -luokkien yhteis-   | - Kappaleet       |
|   tyst                                           | - Setti           |
| - Lukee ja kirjoittaa tiedostoon kysymll apua    | - Setit           |
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
    private String hakemisto = "musa";
    
    /**
     * Listn uusi kappale
     * @param kappale listtv kappale
     * @throws SailoException js lisminen ei onnistu
     */
    public void lisaa(Kappale kappale) throws SailoException {
        this.kappaleet.lisaa(kappale);
    }
    
    
    /** 
     * Korvaa kappaleen tietorakenteessa.  Ottaa kapapleen omistukseensa. 
     * Etsitn samalla tunnusnumerolla oleva kappale.  Jos ei lydy, 
     * niin listn uutena kappaleena. 
     * @param kappale listtvn kappaleen viite.  Huom tietorakenne muuttuu omistajaksi 
     * @throws SailoException jos tietorakenne on jo tynn 
     */
    public void korvaaTaiLisaa(Kappale kappale) throws SailoException {
        kappaleet.korvaaTaiLisaa(kappale);
    }
    
    /**
     * @param rel poistettava relaatio
     */
    public void poistaRelaatio(Relaatio rel) { 
        relaatiot.poista(rel); 
    }


    /**
     * @param setti poistettava setti
     */
    public void poistaSetti(Setti setti) {
        List<Relaatio> relaatioLista = annaRelaatiot(setti.getTunnusNro());
        if (!relaatioLista.isEmpty()) {       
            for (Relaatio rel:relaatioLista) {
                poistaRelaatio(rel);
            }
        }
        setit.poista(setti);

    }


        /**
        * @param kap poistettava kappale
         * @return kokonaisluvun
        */
       public int poistaKappale(Kappale kap) {
           if ( kap == null ) return 0;
           List<Relaatio> relaatioLista = annaKappaleenRelaatiot(kap.getTunnusNro());        
           if (!relaatioLista.isEmpty()) {       
               for (Relaatio rel:relaatioLista) {                   
                   poistaRelaatio(rel);
               }
           }
              int ret = kappaleet.poista(kap.getTunnusNro()); 
                return ret;
       }
    
    
    /** 
    * Palauttaa "taulukossa" hakuehtoon vastaavien kappaleiden viitteet 
    * @param hakuehto hakuehto  
    * @param k etsittvn kentn indeksi  
    * @return tietorakenteen lytyneist kappaleista
    * @throws SailoException Jos jotakin menee vrin
    */ 
    public Collection<Kappale> etsi(String hakuehto, int k) throws SailoException { 
        return kappaleet.etsi(hakuehto, k); 
    } 
    
    
    /**
     * Lukee kappaleiden tiedot tiedostosta
     * @param nimi jota kyten lukemisessa
     * @throws SailoException jos lukeminen eponnistuu
     */
    public void lueTiedostosta(String nimi) throws SailoException {
        kappaleet = new Kappaleet();
        setit = new Setit();
        relaatiot = new Relaatiot();
 
        kappaleet.lueTiedostosta(nimi);
        setit.lueTiedostosta(nimi);
        relaatiot.lueTiedostosta(nimi);
    }
    
    
    /**
     * @param hakemisto hakemisto
     */
    public void setHakemisto(String hakemisto) {
        this.hakemisto = hakemisto;
    }
    
    

    /**
     * Tallettaa kerhon tiedot tiedostoon
     * @throws SailoException jos tallettamisessa ongelmia
     */
    public void tallenna() throws SailoException {
        String virhe = "";
        try {
            kappaleet.tallenna(hakemisto);
        } catch ( SailoException ex ) {
            virhe = ex.getMessage();
        }

        try {
            setit.tallenna(hakemisto);
        } catch ( SailoException ex ) {
            virhe += ex.getMessage();
        }
        
        try {
            relaatiot.tallenna(hakemisto);
        } catch ( SailoException ex ) {
            virhe += ex.getMessage();
        }
        
        if ( !"".equals(virhe) ) throw new SailoException(virhe);
    }

   
    /**
     * Listn uusi setti
     * @param setti listtv setti
     */
    public void lisaa(Setti setti) {
        setit.lisaa(setti);
    }
    
    /**
     * Listn uusi relaatio
     * @param rel listtv relaatio
     */
    public void lisaa(Relaatio rel) {
        relaatiot.lisaa(rel);
    }
    

    /**
     * @param args ei kytss
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
        music.lisaa(rel5);
        
        System.out.println("============= Setit testi =================");
        
        List<Relaatio> relaatioLista = music.annaRelaatiot(1);                        //anna kaikki relaatiot, jotka setId=1
        
          for (Relaatio rel : relaatioLista) {
                   rel.tulosta(System.out);
              }
    
    }
    
    /**
     * @param settiTunnusNro setin tunnusnumero
     * @return lista tietyn setin relaatioista
     */
    public List<Relaatio> annaRelaatiot(int settiTunnusNro) {
        return relaatiot.annaRelaatiot(settiTunnusNro);
    }
    
    
    /**
     * @param kapTunnusNro kappaleen tunnusnumero
     * @return lista tietyn kappaleen relaatioista
     */
    public List<Relaatio> annaKappaleenRelaatiot(int kapTunnusNro) {
        return relaatiot.annaKappaleenRelaatiot(kapTunnusNro);
    }
    
    
    
    /**
     * @return lista seteist
     */
    public List<Setti> annaSetit () {
        return setit.annaSetit();
    }
    
    
    /**
     * @return kappaleiden lukumr
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
     * @param knro kappalenumero
     * @return palauttaa kappaleen jolla sama tunnusnumero
     */
    public Kappale kappaleTunnus(int knro) {
        return kappaleet.kappaleTunnus(knro);
}
}
