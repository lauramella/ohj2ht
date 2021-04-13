package music;

import java.io.PrintStream;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
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
   // @FXML private ComboBoxChooser<String> cbKentat;
    @FXML private ComboBoxChooser<Kappale> comboTracks;
    @FXML private ComboBoxChooser<Setti> comboSets;
    @FXML private ListChooser<Kappale> chooserKappaleet;
    @FXML private ListChooser<Relaatio> chooserbiisiLista; 
    @FXML private GridPane gridKappale;
    @FXML private ScrollPane panelKappale;
    @FXML private ScrollPane panelSetti;   
    @FXML private TextField editArtist;
    @FXML private TextField editName;
    @FXML private TextField editFormat;
    @FXML private TextField editLabel;
    @FXML private TextField editBpm;
    @FXML private TextField editLength;
    @FXML private TextField editGenre;
    @FXML private TextField editStyle;
    @FXML private TextField editReleased;
    @FXML private TextField editCountry;
    @FXML private TextField editInfo;

    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        alusta();
    }


    /**
     * @return Kysyt‰‰n tiedoston nimi ja luetaan se
     */
    public boolean avaa() {
        String uusinimi = LoginController.kysyNimi(null, username);
        if (uusinimi == null) return false;
        music.setHakemisto(uusinimi);
        lueTiedosto(uusinimi);
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


    @FXML private void handlePrint() {
        PrintController.tulosta("Juujuu");
    }
    
    
    @FXML private void handleExit() {
        Dialogs.showMessageDialog("Ei toimi");
    }  
    
    
    @FXML private void handleNewSet() {
        uusiSetti();
    }
    

    @FXML private void handleRenameSet() {
        var resurssi3 = MusicGUIController.class.getResource("RenameSetView.fxml");
        ModalController.showModal(resurssi3, "Rename thisset", null, "");
    }


    @FXML private void handleDeleteSet() {
        Dialogs.showQuestionDialog("Delete set", "Do you want to delete this set?", "Yes", "No");
    }
    
    
    @FXML private void handleHelp() {
        Dialogs.showMessageDialog("Ei toimi");
    }
    

    @FXML private void handleAddTrack() {
        kappaleSettiin();
    }
    
    
    @FXML private void handleDeleteTrack() {
        Dialogs.showMessageDialog("Ei osata viel‰ poistaa setist‰");
    } 
    
    
    @FXML private void handleNewTrack() {
        uusiKappale();
    }
    
    
    @FXML private void handleEdit() {
        muokkaa(1);
    }
    
//==================================================
    
    private Music music;    
    private TextArea areaSetti = new TextArea();
    private Kappale kappaleKohdalla;
    private Setti settiKohdalla;
    private String username = "musa";
    private TextField edits[];
    private int kentta = 0; 
    
    /**
     * Tekee tarvittavat muut alustukset, nyt vaihdetaan GridPanen tilalle
     * yksi iso tekstikentt‰, johon voidaan tulostaa kappaleiden tiedot.
     * Alustetaan myˆs kappalelistan kuuntelija
     */
    private void alusta() {
        chooserKappaleet.clear();
        chooserKappaleet.addSelectionListener(e -> naytaKappale());
        chooserbiisiLista.clear();
        //comboSets.clear();
        comboSets.addSelectionListener(e -> naytaSetti());
        edits = EditTrackController.luoKentat(gridKappale); 
        for (TextField edit: edits)  
            if ( edit != null ) {  
                edit.setEditable(false);  
                edit.setOnMouseClicked(e -> { if ( e.getClickCount() > 1 ) muokkaa(getFieldId(e.getSource(),0)); });  
                edit.focusedProperty().addListener((a,o,n) -> kentta = getFieldId(edit,kentta));  
            }
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
    * @return null jos onnistuu, muuten virhe tekstin‰
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
    
    
    private void muokkaa(int k) {
        if (kappaleKohdalla == null) return;
        try {
            Kappale kappale;
            kappale = EditTrackController.kysyKappale(null, kappaleKohdalla.clone(), k);
            if (kappale == null) return;
            music.korvaaTaiLisaa(kappale);
            haeKappaleTiedot(kappale.getTunnusNro());
            EditTrackController.naytaKappale(edits, kappale);
        } catch (CloneNotSupportedException e) {  
            // 
        } catch (SailoException e) { 
            Dialogs.showMessageDialog(e.getMessage()); 
        }
    }
    
   
    
    /**
     * TODO
     * @param nimi kayttajan nimi
     * @return palauttaa null muuten antaa ilmoituksen virhe
     */
    protected String lueTiedosto(String nimi) {
        username = nimi;
        // setTitle("Kerho - " + username);
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
     * Lis‰t‰‰n tietty kappale settiin
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
     * N‰ytet‰‰n setti
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
        chooserbiisiLista.setSelectedIndex(index); //t‰st‰ tulee muutosviesti


    }
    
    
    ///**
     //* Haetaan kappale ja laitetaan se valituksi
     //* @param knro kappaleen nro, joka aktivoidaan haun j‰lkeen
     //*/ 
    //private void haeSetti(int snro) { 
      //  List<Relaatio> relaatioLista = music.annaRelaatiot(snro);
        //for (Relaatio rel : relaatioLista) {
         //   rel.tulosta(System.out);
      // }
        //comboSets.setSelectedIndex(snro); //t‰st‰ tulee muutosviesti
    //} 
    
    
    /**
     * Lis‰t‰‰n uusi setti
     */
    private void uusiSetti() {
        Setti setti = new Setti();
        setti.rekisteroi();
        music.lisaa(setti);
        haeSetit();
       // haeSetti(setti.getTunnusNro());
        naytaSetti();
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
     * @param knro kappaleen nro, joka aktivoidaan haun j‰lkeen
     */ 
    private void haeKappaleTiedot(int knro) {
        int k = comboTracks.getSelectionModel().getSelectedIndex();
        String ehto = hakuehto.getText(); 
        if (k > 0 || ehto.length() > 0)
        naytaVirhe(String.format("Ei osata hakea (kentt‰: %d, ehto: %s)", k, ehto));
        else
            naytaVirhe(null);
        
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
            Dialogs.showMessageDialog("Kappaleen hakemisessa ongelmia! " + ex.getMessage());
        }       
        chooserKappaleet.setSelectedIndex(index); //t‰st‰ tulee muutosviesti
    }
       
           
    /**
     * Lis‰t‰‰n uusi kappale
     */
    private void uusiKappale() {
        try {
            Kappale kappale = new Kappale();
            kappale = AddNewTrackController.kysyKappale(null, kappale, 1);
            if ( kappale == null ) return;
            tallenna();
            kappale.rekisteroi();
            music.lisaa(kappale);
            haeKappaleTiedot(kappale.getTunnusNro());
            EditTrackController.naytaKappale(edits, kappale);
        } catch (SailoException e) {
            Dialogs.showMessageDialog("Ongelmia kappaleen lis‰‰misess‰");
            return;
        }
    }
    
    
    /**
     * Tulostaa setin kappaleet
     * @param os tietovirta johon tulostetaan
     * @param reLista setiss‰ olevat relaatiot
     */
    private void tulosta(PrintStream os, List<Relaatio> reLista) {
        os.println("---------------------------------");
        for (Relaatio rel : reLista) {
           music.kappaleTunnus(rel.getKNro()).tulosta1(os);
        }
        os.println("---------------------------------");
    }
    
    
    /**
     * N‰ytet‰‰n kappale
     */
    protected void naytaKappale() {
        kappaleKohdalla = chooserKappaleet.getSelectedObject();       
        if (kappaleKohdalla == null) return;
        
        EditTrackController.naytaKappale(edits, kappaleKohdalla);
    }
    
    
    /**
     * Asetetaan k‰ytett‰v‰ music
     * @param music jota k‰ytet‰‰n
     */
    public void setMusic(Music music) {
        this.music = music;
    }
}