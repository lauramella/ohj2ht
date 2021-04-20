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
| - Huolehtii Kappaleet, Relaatiot ja Setit          | - Kappaleet       |
|   luokkien yhteistyˆst‰                            | - Setti           |
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
 *   * Testien alustus
 * @example
 * <pre name="testJAVA">
 * #import music2.SailoException;
 *  private Music music;
 *  private Kappale kap1;
 *  private Kappale kap2;
 *  private int kid1;
 *  private int kid2;
 *  private Setti setti1;
 *  private Setti setti2;
 *  private Relaatio rel1; 
 *  private Relaatio rel2; 
 *  private Relaatio rel3;
 *  
 *  @SuppressWarnings("javadoc")
 *  public void alustaMusic() {
 *    music = new Music();
 *    kap1 = new Kappale(); kap1.taytaKappaleTiedoilla(); kap1.rekisteroi();
 *    kap2 = new Kappale(); kap2.taytaKappaleTiedoilla(); kap2.rekisteroi();
 *    kid1 = kap1.getTunnusNro();
 *    kid2 = kap2.getTunnusNro();
 *    setti1 = new Setti(); setti1.taytaSettiTiedoilla();
 *    setti2 = new Setti(); setti2.taytaSettiTiedoilla();
 *    rel1 = new Relaatio(1,1); 
 *    rel2 = new Relaatio(2,1); 
 *    rel3 = new Relaatio(3,2);
 *    try {
 *    music.lisaa(kap1);
 *    music.lisaa(kap2);
 *    music.lisaa(setti1);
 *    music.lisaa(setti2);
 *    music.lisaa(rel1);
 *    music.lisaa(rel2);
 *    music.lisaa(rel3);
 *    } catch ( Exception e) {
 *       System.err.println(e.getMessage());
 *    }
 *  }
 * </pre>
 */
public class Music {   
    private Kappaleet kappaleet = new Kappaleet();
    private Setit setit = new Setit();
    private Relaatiot relaatiot = new Relaatiot();
    private String hakemisto = "musa";



    /**
     * Lis‰t‰‰n uusi kappale
     * @param kappale lis‰tt‰v‰ kappale
     * @throws SailoException js lis‰‰minen ei onnistu
     * @example
     * <pre name="test">
     * #THROWS SailoException  
     *  alustaMusic();
     *  music.etsi("*",0).size() === 2;
     *  music.lisaa(kap1);
     *  music.etsi("*",0).size() === 3;
     */
    public void lisaa(Kappale kappale) throws SailoException {
        this.kappaleet.lisaa(kappale);
    }


    /** 
     * Korvaa kappaleen tietorakenteessa.  Ottaa kappaleen omistukseensa. 
     * Etsit‰‰n samalla tunnusnumerolla oleva kappale.  Jos ei lˆydy, 
     * niin lis‰t‰‰n uutena kappaleena. 
     * @param kappale lis‰tt‰v‰n kappaleen viite.  Huom tietorakenne muuttuu omistajaksi 
     * @throws SailoException jos tietorakenne on jo t‰ynn‰
     * @example
     * <pre name="test">
     * #THROWS SailoException  
     *  alustaMusic();
     *  music.etsi("*",0).size() === 2;
     *  music.korvaaTaiLisaa(kap1);
     *  music.etsi("*",0).size() === 2;
     * </pre>
     */  
    public void korvaaTaiLisaa(Kappale kappale) throws SailoException {
        kappaleet.korvaaTaiLisaa(kappale);
    }


    /** 
     * Palauttaa "taulukossa" hakuehtoon vastaavien kappaleiden viitteet 
     * @param hakuehto hakuehto  
     * @param k etsitt‰v‰n kentn indeksi  
     * @return tietorakenteen lˆytyneist‰ kappaleista
     * @throws SailoException Jos jotakin menee v‰‰rin
     */ 
    public Collection<Kappale> etsi(String hakuehto, int k) throws SailoException { 
        return kappaleet.etsi(hakuehto, k); 
    } 


    /**
     * Poistetaan relaatio
     * @param rel poistettava relaatio
     */
    public void poistaRelaatio(Relaatio rel) { 
        if ( rel == null ) return;
        relaatiot.poista(rel.getTunnusNro()); 
    }


    /**
     * Poistetaan setti ja settiin kuuluvat relaatiot
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
     * Poistetaan kappale sek‰ sen relaatiot
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
     * Haetaan kaikki setin relaatiot
     * @param settiTunnusNro setin tunnusnumero
     * @return lista tietyn setin relaatioista
     * @example
     * <pre name="test">
     * #import java.util.*;
     * 
     *  alustaMusic();

     *  List<Relaatio> loytyneet;
     *  loytyneet = music.annaRelaatiot(3);
     *  loytyneet.size() === 0; 
     *  loytyneet = music.annaRelaatiot(1);
     *  loytyneet.size() === 2; 
     *  loytyneet.get(0) == rel1 === true;
     *  loytyneet.get(1) == rel2 === true;
     *  loytyneet = music.annaRelaatiot(2);
     *  loytyneet.size() === 1; 
     *  loytyneet.get(0) == rel3 === true;
     * </pre> 
     */
    public List<Relaatio> annaRelaatiot(int settiTunnusNro) {
        return relaatiot.annaRelaatiot(settiTunnusNro);
    }


    /**
     * Haetaan kaikki kappaleen relaatiot
     * @param kapTunnusNro kappaleen tunnusnumero
     * @return lista tietyn kappaleen relaatioista
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * #import java.util.*;
     * 
     *  alustaMusic();
     *  Kappale kap3 = new Kappale();
     *  kap3.rekisteroi();
     *  music.lisaa(kap3);
     *  List<Relaatio> loytyneet;
     *  loytyneet = music.annaKappaleenRelaatiot(4);
     *  loytyneet.size() === 0; 
     *  loytyneet = music.annaKappaleenRelaatiot(1);
     *  loytyneet.size() === 1; 
     *  loytyneet.get(0) == rel1 === true;
     * </pre> 
     */
    public List<Relaatio> annaKappaleenRelaatiot(int kapTunnusNro) {
        return relaatiot.annaKappaleenRelaatiot(kapTunnusNro);
    }


    /**
     * Palauttaa listan seteist‰
     * @return lista seteist‰
     */
    public List<Setti> annaSetit () {
        return setit.annaSetit();
    }


    /**
     * Palauttaa kapapleiden lukum‰‰r‰n
     * @return kappaleiden lukum‰‰r‰
     */
    public int getKappaleet() {
        return this.kappaleet.getLkm();
    }


    /**
     * Antaa tiedoston i:n kappaleen
     * @param i monesko kappale (alkaa 0:sta)
     * @return kappale paikasta i
     */
    public Kappale annaKappale(int i) {
        return kappaleet.anna(i);
    }


    /**
     * Antaa tiedoston i:n relaation
     * @param i monesko kappale (alkaa 0:sta)
     * @return kappale paikasta i
     */
    public Relaatio annaRelaatio(int i) {
        return relaatiot.anna(i);
    }


    /**
     * Palauttaa kappaleen, jolla parametrina saatu kappalenumero
     * @param knro kappalenumero
     * @return palauttaa kappaleen jolla sama tunnusnumero
     */
    public Kappale kappaleTunnus(int knro) {
        return kappaleet.kappaleTunnus(knro);
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
     * Asettaa hakemiston
     * @param hakemisto hakemisto
     */
    public void setHakemisto(String hakemisto) {
        this.hakemisto = hakemisto;
    }



    /**
     * Tallettaa musiikkitiedot tiedostoon
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
     * Testiohjelma
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

        //====================RELAATIOT===================
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
}
