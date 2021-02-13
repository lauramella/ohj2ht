package music;

import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * @author laura
 * @version 1.2.2021
 *
 */
public class MusicGUIController implements Initializable {
    
    
    
         @FXML private TextField hakuehto;
         @FXML private ComboBoxChooser<String> comboTracks;
         @FXML private ComboBoxChooser<String> comboSets;
       
         

    
    
    private String username = "User1";
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
        Dialogs.showMessageDialog("Ei osata viel‰ lis‰t‰ uutta kappaletta");
   }
    @FXML private void handleEdit() {
        var resurssi = MusicGUIController.class.getResource("EditTrackView.fxml");
        ModalController.showModal(resurssi, "Track Info", null, "");
   }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        
    }
}
