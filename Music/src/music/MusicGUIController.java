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
import music2.SailoException;

/**
 * @author laura
 * @version 1.2.2021
 *
 */
public class MusicGUIController implements Initializable {

    @FXML private TextField hakuehto;
    @FXML private ComboBoxChooser<String> comboTracks;
    @FXML private ComboBoxChooser<String> comboSets;
    @FXML private ListChooser<Kappale> chooserKappaleet;
    @FXML private ScrollPane panelKappale;

   private String username = "User1";
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        alusta();
    }
    
    
    /**
     * @param nimi tiedosto josta kerhon tiedot luetaan
     */
    protected void lueTiedosto(String nimi) {
        username = nimi;
        // setTitle("Kerho - " + username);
        String virhe = "Ei osata lukea viel‰";  // TODO: t‰h‰n oikea tiedoston lukeminen
        // if (virhe != null) 
        Dialogs.showMessageDialog(virhe);
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
    

    private void tallenna() {
        Dialogs.showMessageDialog("Suljetaan sovellus.");
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
        var resurssi2 = MusicGUIController.class.getResource("NewSetView.fxml");
        ModalController.showModal(resurssi2, "New set", null, "");
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
        Dialogs.showMessageDialog("Ei osata viel‰ lis‰t‰ kappaletta settiin");
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
    
    /**
     * Alustus
     */
    private void alusta() {
        panelKappale.setContent(areaKappale);
        areaKappale.setFont(new Font("Courier New", 12));
        panelKappale.setFitToHeight(true);
        chooserKappaleet.clear();
        chooserKappaleet.addSelectionListener(e -> naytaKappale());
    }
    
    
    /**
     * Asetetaan k‰ytett‰v‰ music
     * @param music jota k‰ytet‰‰n
     */
    public void setMusic(Music music) {
        this.music = music;
    }
    
    
    /**
     * N‰ytet‰‰n kappale
     */
    private void naytaKappale() {
        Kappale kappaleKohdalla = chooserKappaleet.getSelectedObject();
        
        if (kappaleKohdalla == null) return;
        
        areaKappale.setText("");
        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(areaKappale)){
           tulosta(os, kappaleKohdalla);
        }
    }
    
    private void tulosta(PrintStream os, Kappale kappale) {
        os.println("---------------------------------");
        kappale.tulosta(os);
        os.println("---------------------------------");
      //  List<Kappale> biisiLista = music.annaKappaleet(setti);
    }
    
    
    
    
    /**
     * Haetaan kappale
     */ 
    private void hae(int knro) {
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
        hae(kappale.getTunnusNro());
    }
}
