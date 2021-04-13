/**
 * 
 */
package music2;

import java.util.Collection;
import java.util.List;
import java.util.Iterator;
import org.junit.*;

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
    private Setit setit = new Setit();
    private Relaatiot relaatiot = new Relaatiot();
    private String hakemisto = "musa";
    
    /**
     * Lis�t��n uusi kappale
     * @param kappale lis�tt�v� kappale
     * @throws SailoException js lis��minen ei onnistu
     */
    public void lisaa(Kappale kappale) throws SailoException {
        this.kappaleet.lisaa(kappale);
    }
    
    
    /** 
     * Korvaa kappaleen tietorakenteessa.  Ottaa kapapleen omistukseensa. 
     * Etsit��n samalla tunnusnumerolla oleva kappale.  Jos ei l�ydy, 
     * niin lis�t��n uutena kappaleena. 
     * @param kappale lis�tt�v�n kappaleen viite.  Huom tietorakenne muuttuu omistajaksi 
     * @throws SailoException jos tietorakenne on jo t�ynn� 
     */
    public void korvaaTaiLisaa(Kappale kappale) throws SailoException {
        kappaleet.korvaaTaiLisaa(kappale);
    }
    
    
    /** 
    * Palauttaa "taulukossa" hakuehtoon vastaavien kappaleiden viitteet 
    * @param hakuehto hakuehto  
    * @param k etsitt�v�n kent�n indeksi  
    * @return tietorakenteen l�ytyneist� kappaleista
    * @throws SailoException Jos jotakin menee v��rin
    */ 
    public Collection<Kappale> etsi(String hakuehto, int k) throws SailoException { 
        return kappaleet.etsi(hakuehto, k); 
    } 
    
    
    /**
     * Lukee kappaleiden tiedot tiedostosta
     * @param nimi jota k�yte��n lukemisessa
     * @throws SailoException jos lukeminen ep�onnistuu
     */
    public void lueTiedostosta(String nimi) throws SailoException {
        kappaleet = new Kappaleet();
        setit = new Setit();
        relaatiot = new Relaatiot();
        
       // setTiedosto(nimi);
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
     * Lis�t��n uusi setti
     * @param setti lis�tt�v� setti
     */
    public void lisaa(Setti setti) {
        setit.lisaa(setti);
    }
    
    /**
     * Lis�t��n uusi relaatio
     * @param rel lis�tt�v� relaatio
     */
    public void lisaa(Relaatio rel) {
        relaatiot.lisaa(rel);
    }
    

    /**
     * @param args ei k�yt�ss�
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
     * @return lista seteist�
     */
    public List<Setti> annaSetit () {
        return setit.annaSetit();
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
     * @param knro kappalenumero
     * @return palauttaa kappaleen jolla sama tunnusnumero
     */
    public Kappale kappaleTunnus(int knro) {
        return kappaleet.kappaleTunnus(knro);
    }
}


//chooserbiisiLista.clear();        
//int index = 0;
//for (Relaatio relaatio:relaatioLista) {
  //  chooserbiisiLista.add(music.kappaleTunnus(relaatio.getKappaleNro()).getName(), relaatio);
//}       
//chooserbiisiLista.setSelectedIndex(index); //t�st� tulee muutosviesti
