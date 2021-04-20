package music;

import java.io.PrintStream;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import fi.jyu.mit.fxgui.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import music2.Kappale;
import music2.Music;
import music2.Relaatio;
import music2.SailoException;
import music2.Setti;
import static music.EditTrackController.getFieldId;


/**
 * @author laura
 * @version 1.2.2021
 *
 */
public class MusicGUIController implements Initializable {
    @FXML private TextField hakuehto;
    @FXML private Label labelVirhe;
    @FXML private ComboBoxChooser<Kappale> comboTracks;
    @FXML private ComboBoxChooser<Setti> comboSets;
    @FXML private ListChooser<Kappale> chooserKappaleet;
    @FXML private ListChooser<Relaatio> chooserbiisiLista; 
    @FXML private GridPane gridKappale;
    @FXML private ScrollPane panelKappale;
    @FXML private ScrollPane panelSetti;   

    @FXML private void handlePrint() {
        PrintController tulostusCtrl = PrintController.tulosta(null); 
        tulostaSetti(tulostusCtrl.getTextArea());
    }   

    @FXML private void handleExit() {
        tallenna();
        Platform.exit();
    }

    @FXML private void handleNewSet() {
        var resurssi = MusicGUIController.class.getResource("NewSetView.fxml");
        ModalController.showModal(resurssi, "New set", null, "");
        uusiSetti();
    }

    @FXML private void handleRenameSet() {
        var resurssi3 = MusicGUIController.class.getResource("RenameSetView.fxml");
        ModalController.showModal(resurssi3, "Rename thisset", null, "");
        uusiNimi();
    }

    @FXML private void handleDeleteSet() {
        poistaSetti();
    }    

    @FXML private void handleHelp() {
        Dialogs.showMessageDialog("Ei toimi");
    }   

    @FXML private void handleAddTrack() {
        kappaleSettiin();
    }   

    @FXML private void handleDeleteTrack() {
        poistaRelaatio();
    }   

    @FXML private void handleNewTrack() {
        uusiKappale();
    } 

    @FXML private void handleEdit() {
        muokkaa(1);
    }

    @FXML private void handleHakuehto() {
        haeKappaleTiedot(0);
    }

    //==================================================   
    private Music music;    
    private Kappale kappaleKohdalla;
    private Setti settiKohdalla;
    private Relaatio relaatioKohdalla;
    private String username;
    private static Kappale apukappale = new Kappale();
    private TextField edits[];
    private int kentta = 0; 

    /**
     * @return Kysyt‰‰n tiedoston nimi ja luetaan se
     */
    public boolean avaa() {
        String uusinimi = LoginController.kysyNimi(null, username);
        uusinimi= LoginController.getNimi();       
        if (uusinimi == null) return false;
        music.setHakemisto(uusinimi);
        lueTiedosto(uusinimi);
        tallenna();
        return true;
    }


    /**
     * Tarkistetaan onko tallennus tehty
     * @return true jos saa sulkea sovelluksen, false jos ei
     */
    public boolean voikoSulkea() {
        tallenna();
        return true;
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        alusta();
    }


    /**
     * Tekee tarvittavat muut alustukset, nyt vaihdetaan GridPanen tilalle
     * yksi iso tekstikentt‰, johon voidaan tulostaa kappaleiden tiedot.
     * Alustetaan myˆs kappalelistan kuuntelija
     */
    private void alusta() {
        chooserKappaleet.clear();
        chooserKappaleet.addSelectionListener(e -> naytaKappale());
        chooserbiisiLista.clear();
        comboSets.addSelectionListener(e -> naytaSetti());
        edits = EditTrackController.luoKentat(gridKappale); 
        for (TextField edit: edits)  
            if ( edit != null ) {  
                edit.setEditable(false);  
                edit.setOnMouseClicked(e -> { if ( e.getClickCount() > 1 ) muokkaa(getFieldId(e.getSource(),0)); });  
                edit.focusedProperty().addListener((a,o,n) -> kentta = getFieldId(edit,kentta));  
            }
        comboTracks.clear(); 
        for (int k = apukappale.ekaKentta(); k < 7; k++) 
            comboTracks.add(apukappale.getKysymys(k), null);
        comboTracks.getSelectionModel().select(0);
    }


    /**
     * Poistetaan setti ja siihen kuuluvat relaatiot.
     */
    private void poistaSetti() {
        settiKohdalla = comboSets.getSelectedObject();
        if (settiKohdalla == null) return;
        Setti set = settiKohdalla;
        if ( !Dialogs.showQuestionDialog("Delete set", "Do you want to delete this set?", "Yes", "No") )
            return;
        music.poistaSetti(set);
        haeSetit();
        chooserbiisiLista.clear();
    }


    /**
     * Poistetaan kappale setist‰, eli relaatio.
     */
    private void poistaRelaatio() {
        settiKohdalla = comboSets.getSelectedObject();
        relaatioKohdalla = chooserbiisiLista.getSelectedObject();
        if (settiKohdalla == null) return;
        if (relaatioKohdalla == null) return;
        Relaatio relaatio = relaatioKohdalla;
        music.poistaRelaatio(relaatio);
        chooserbiisiLista.clear();
        int index = comboSets.getSelectedIndex();
        comboSets.setSelectedIndex(index);
        naytaSetti();               
    }   


    private void naytaVirhe(String virhe) {
        if ( virhe == null || virhe.isEmpty() ) {
            labelVirhe.setText("");
            labelVirhe.getStyleClass().removeAll("virhe");
            return;
        }
        labelVirhe.setText(virhe);
        labelVirhe.getStyleClass().add("virhe");
    }


    /**
     * Tietojen tallennus
     * @return null jos onnistuu, muuten virhe tekstin
     */
    private String tallenna() {
        try {
            music.tallenna();
            return null;
        } catch (SailoException ex) {
            Dialogs.showMessageDialog("Tallennuksessa ongelmia! " + ex.getMessage());
            return ex.getMessage();
        }
    }


    /**
     * Alustaa ohjelman lukemalla sen tiedot k‰ytt‰j‰n valitsemasta tiedostosta
     * @param nimi kayttajan nimi
     * @return palauttaa null muuten antaa ilmoituksen virhe
     */
    protected String lueTiedosto(String nimi) {
        username = nimi;
        try {
            music.lueTiedostosta(nimi);
            haeSetit();
            haeKappaleTiedot(0);
            naytaSetti();
            return null;
        } catch (SailoException e) {
            haeKappaleTiedot(0);
            String virhe = e.getMessage(); 
            if ( virhe != null ) Dialogs.showMessageDialog(virhe);
            return virhe;
        }
    }


    /**
     * Muokataan kappaletta
     * @param k muokattava kentt‰
     */
    private void muokkaa(int k) {
        if (kappaleKohdalla == null) return;
        try {
            Kappale kappale;
            kappale = EditTrackController.kysyKappale(null, kappaleKohdalla.clone(), k, music);
            int index = comboSets.getSelectedIndex();
            haeKappaleTiedot(0);
            comboSets.setSelectedIndex(index);
            chooserbiisiLista.clear();
            haeSetit();
            if (kappale == null) return;
            music.korvaaTaiLisaa(kappale);
            haeKappaleTiedot(kappale.getTunnusNro());
            EditTrackController.naytaKappale(edits, kappale);
            haeKappaleTiedot(0);
        } catch (CloneNotSupportedException e) {  
            // 
        } catch (SailoException e) { 
            Dialogs.showMessageDialog(e.getMessage()); 
        }
    }


    /**
     * Annetaan setille uusi nimi
     */
    private void uusiNimi() {
        settiKohdalla = comboSets.getSelectedObject();
        if (settiKohdalla == null) return;
        String uusiNimi = RenameSetController.getNimi();
        if (uusiNimi == null) return;
        settiKohdalla.uusiNimi(uusiNimi);
        int index = comboSets.getSelectedIndex();
        haeSetit();
        comboSets.setSelectedIndex(index);
        naytaSetti();
        tallenna();
    }


    /**
     * Lis‰t‰‰n uusi setti
     */
    private void uusiSetti() {
        Setti setti = new Setti();
        setti.rekisteroi();
        music.lisaa(setti);
        String setinNimi = NewSetController.getSetinNimi();
        if (setinNimi != null) setti.uusiNimi(setinNimi);
        int index = comboSets.getSelectedIndex();
        haeSetit();
        comboSets.setSelectedIndex(index);
        naytaSetti();
        tallenna();
    }


    /**
     * Lis‰t‰‰n tietty kappale settiin luomalla uusi relaatio.
     */
    private void kappaleSettiin() {
        kappaleKohdalla = chooserKappaleet.getSelectedObject();
        settiKohdalla = comboSets.getSelectedObject();
        if (kappaleKohdalla == null) return;
        if (settiKohdalla == null) return;
        Relaatio rel = new Relaatio(kappaleKohdalla.getTunnusNro(),settiKohdalla.getTunnusNro());
        music.lisaa(rel);
        naytaSetti();
    }


    /**
     * N‰ytet‰‰n valittu setti eli sen relaatiot.
     */
    private void naytaSetti() {
        settiKohdalla = comboSets.getSelectedObject();
        if (settiKohdalla == null) return;
        List<Relaatio> relaatioLista = music.annaRelaatiot(settiKohdalla.getTunnusNro());
        chooserbiisiLista.clear();        
        int index = 0;
        for (Relaatio relaatio:relaatioLista) {
            chooserbiisiLista.add(music.kappaleTunnus(relaatio.getKNro()).getName(), relaatio);
        }       
        chooserbiisiLista.setSelectedIndex(index); //tst tulee muutosviesti
    }


    /**
     * Hakee setit
     */
    public void haeSetit() {
        comboSets.clear();
        List<Setti> settiLista = music.annaSetit();
        for (Setti set : settiLista) {
            comboSets.add(set.getNimi(), set);
        }
    }


    /**
     * Haetaan kappale ja laitetaan se valituksi
     * @param knro kappaleen nro, joka aktivoidaan haun jlkeen
     * jos 0 aktivoidaan nykyinen kappale
     */ 
    private void haeKappaleTiedot(int knro) {        
        int k = comboTracks.getSelectionModel().getSelectedIndex() + apukappale.ekaKentta();
        String ehto = hakuehto.getText(); 
        if (ehto.indexOf('*') < 0) ehto = "*" + ehto + "*"; 

        chooserKappaleet.clear();        
        int index = 0;
        Collection<Kappale> kappaleet;
        try {
            kappaleet = music.etsi(ehto, k);
            int i = 0;
            for (Kappale kappale:kappaleet) {
                if (kappale.getTunnusNro() == knro) index = i;
                chooserKappaleet.add(kappale.getName(), kappale);
            }
        } catch (SailoException ex) {
            naytaVirhe("Jsenen hakemisessa ongelmia! " + ex.getMessage());
        }       
        chooserKappaleet.setSelectedIndex(index); //tst tulee muutosviesti
    }


    /**
     * Lis‰t‰‰n uusi kappale, jota voi muokata.
     */
    private void uusiKappale() {
        try {
            Kappale kappale = new Kappale();
            kappale = AddNewTrackController.kysyKappale(null, kappale, 1);
            if ( kappale == null ) return;
            kappale.rekisteroi();
            music.lisaa(kappale);
            tallenna();
            haeKappaleTiedot(kappale.getTunnusNro());
            EditTrackController.naytaKappale(edits, kappale);
        } catch (SailoException e) {
            Dialogs.showMessageDialog("Ongelmia kappaleen lismisess");
            return;
        }
    }


    /**
     * N‰ytt‰‰ valitun kappaleen tiedot tekstikenttiin.
     */
    protected void naytaKappale() {
        kappaleKohdalla = chooserKappaleet.getSelectedObject();       
        if (kappaleKohdalla == null) return;

        EditTrackController.naytaKappale(edits, kappaleKohdalla);
    }


    /**
     * Tulostaa setiss‰ olevat kappaleet tekstialueeseen
     * @param text alue johon tulostetaan
     */
    public void tulostaSetti(TextArea text) {
        settiKohdalla = comboSets.getSelectedObject();
        if (settiKohdalla == null) return;
        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(text)) {
            List<Relaatio> relaatiolista = music.annaRelaatiot(settiKohdalla.getTunnusNro()); 
            tulosta(os, settiKohdalla.getNimi(), relaatiolista);                                  
        }
    }


    /**
     * Tulostaa kappaleen tiedot
     * @param os tietovirta johon tulostetaan
     * @param setinNimi nimi
     * @param reLista tulostettava relaatiolista
     */
    public void tulosta(PrintStream os, String setinNimi, List<Relaatio> reLista) {
        os.println(setinNimi);
        os.println("----------------------------------------------");
        for (Relaatio rel : reLista) {
            music.kappaleTunnus(rel.getKNro()).tulosta1(os);
        }
        os.println("----------------------------------------------");
    }    


    /**
     * Asetetaan k‰ytett‰v‰ music
     * @param music jota k‰ytet‰‰n
     */
    public void setMusic(Music music) {
        this.music = music;
    }
}
