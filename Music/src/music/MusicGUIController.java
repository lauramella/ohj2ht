package music;

import java.io.PrintStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import music2.Kappale;
import music2.Music;
import music2.Relaatio;
import music2.Relaatiot;
import music2.SailoException;
import music2.Setti;

/**
 * @author laura
 * @version 1.2.2021
 *
 */
public class MusicGUIController implements Initializable {

    @FXML private TextField hakuehto;
    @FXML private ComboBoxChooser<String> comboTracks;
    @FXML private ComboBoxChooser<Setti> comboSets;
    @FXML private ListChooser<Kappale> chooserKappaleet;
    @FXML private ListChooser<Kappale> chooserbiisiLista; 
    @FXML private ScrollPane panelKappale;
    @FXML private ScrollPane panelSetti;

   private String username = "musa";
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        alusta();
    }


    /**
     * @return Kysyt‰‰n tiedoston nimi ja luetaan se
     */
    public boolean avaa() {
        String uusinimi = LoginController.kysyNimi(null, username);
        if (uusinimi == null) return true;
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
       // var resurssi2 = MusicGUIController.class.getResource("NewSetView.fxml");
      //  ModalController.showModal(resurssi2, "New set", null, "");
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
       // Dialogs.showMessageDialog("Ei osata viel‰ lis‰t‰ kappaletta settiin");
        kappaleSettiin();
    }
    
    
    @FXML private void handleDeleteTrack() {
        Dialogs.showMessageDialog("Ei osata viel‰ poistaa setist‰");
    } 
    
    
    @FXML private void handleNewTrack() {
       // Dialogs.showMessageDialog("Ei osata viel‰ lis‰t‰ uutta kappaletta");
        uusiKappale();
    }
    
    
    @FXML private void handleEdit() {
        var resurssi = MusicGUIController.class.getResource("EditTrackView.fxml");
        ModalController.showModal(resurssi, "Track Info", null, "");
    }
    
//==================================================
    
    private Music music;    
    private TextArea areaKappale = new TextArea();
    private TextArea areaSetti = new TextArea();
    private Kappale kappaleKohdalla;
    private Setti settiKohdalla;
    
    /**
     * Alustus
     */
    private void alusta() {
        panelKappale.setContent(areaKappale);
        areaKappale.setFont(new Font("Courier New", 12));
        panelKappale.setFitToHeight(true);
        panelSetti.setContent(areaSetti);
        areaSetti.setFont(new Font("Courier New", 12));
        panelSetti.setFitToHeight(true);
        chooserKappaleet.clear();
        chooserKappaleet.addSelectionListener(e -> naytaKappale());
        chooserbiisiLista.clear();
        comboSets.addSelectionListener(e -> naytaSetti());
    } 
    
    
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
     * TODO
     * @param nimi kayttajan nimi
     * @return palauttaa null muuten antaa ilmoituksen virhe
     */
    protected String lueTiedosto(String nimi) {
        username = nimi;
        // setTitle("Kerho - " + username);
        try {
            music.lueTiedostosta(nimi);
            haeKappaleTiedot(0);
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
        areaSetti.setText("");
        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(areaSetti)){
           tulosta(os, relaatioLista);
        }
    }
    
    
    /**
     * Haetaan kappale ja laitetaan se valituksi
     * @param knro kappaleen nro, joka aktivoidaan haun j‰lkeen
     */ 
    private void haeSetti(int snro) {
        //comboSets.clear();  silmukassa kysyt‰‰n tietokannasta kaikki setit  
        List<Relaatio> relaatioLista = music.annaRelaatiot(snro);
        for (Relaatio rel : relaatioLista) {
            rel.tulosta(System.out);
       }
        comboSets.setSelectedIndex(snro); //t‰st‰ tulee muutosviesti
    } 
    
    
    /**
     * Lis‰t‰‰n uusi setti
     */
    private void uusiSetti() {
        Setti setti = new Setti();
        setti.rekisteroi();
        setti.taytaSettiTiedoilla(); //Korvaa dialogilla
        music.lisaa(setti);
        comboSets.add(setti);
        haeSetti(setti.getTunnusNro());
    }
    
    
    /**
     * Haetaan kappale ja laitetaan se valituksi
     * @param knro kappaleen nro, joka aktivoidaan haun j‰lkeen
     */ 
    private void haeKappaleTiedot(int knro) {
        chooserKappaleet.clear();        
        int index = 0;
        for (int i = 0; i < music.getKappaleet(); i++) {
            Kappale kappale = music.annaKappale(i);
            if (kappale.getTunnusNro() == knro) index = i;
            chooserKappaleet.add(kappale.getNimi(), kappale);
        }
        chooserKappaleet.setSelectedIndex(index); //t‰st‰ tulee muutosviesti
    } 
    
           
    /**
     * Lis‰t‰‰n uusi kappale
     */
    private void uusiKappale() {
        Kappale kappale = new Kappale();
        kappale.rekisteroi();
        kappale.taytaKappaleTiedoilla(); //Korvaa dialogilla
        try {
            music.lisaa(kappale);
        } catch (SailoException e) {
            Dialogs.showMessageDialog("Ongelmia kappaleen lis‰‰misess‰");
            return;
        }
        haeKappaleTiedot(kappale.getTunnusNro());
    }

    
    /**
     * Tulostaa kappaleen tiedot
     * @param os tietovirta johon tulostetaan
     * @param kappale joka tulostetaan
     */
    private void tulosta(PrintStream os, final Kappale kappale) {
        os.println("---------------------------------");
        kappale.tulosta(os);
        os.println("---------------------------------");
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
    private void naytaKappale() {
        kappaleKohdalla = chooserKappaleet.getSelectedObject();
        
        if (kappaleKohdalla == null) return;
        
        areaKappale.setText("");
        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(areaKappale)){
           tulosta(os, kappaleKohdalla);
        }
    }
    
    
    /**
     * Asetetaan k‰ytett‰v‰ music
     * @param music jota k‰ytet‰‰n
     */
    public void setMusic(Music music) {
        this.music = music;
    }
}
