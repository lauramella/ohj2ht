package music;

import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import music2.Kappale;

/**
 * Muokkausikkunan tapahtumat
 * @author laura
 * @version 10.2.2021
 *
 */
public class EditTrackController implements ModalControllerInterface<Kappale>, Initializable {
    

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
    @FXML private TextArea editInfo;
    @FXML private Button buttonSulje;
    
    @FXML private void handleCancel() {
        ModalController.closeStage(buttonSulje);;
   }
    
    @FXML private void handleDelete() {
        Dialogs.showQuestionDialog("Delete track", "Do you want to delete this track?", "Yes", "No");
   }
    
    @FXML private void handleSave() {
        Dialogs.showMessageDialog("Ei toimi tallennus");
   }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        alusta();       
    }
    
    @Override
    public Kappale getResult() {
        return kappaleKohdalla;
    }
    
    @Override
    public void handleShown() {
        editName.requestFocus();      
    }
    
    
    /**
     * @param oletus oletus Kappale
     */
    @Override
    public void setDefault(Kappale oletus) {
        kappaleKohdalla = oletus;
        naytaKappale(kappaleKohdalla);
        
    }
    
    
    //========================================================================================================
    private Kappale kappaleKohdalla;
    
    /**
     * Tekee tarvittavat muut alustukset.
     */
    protected void alusta() {
        //
    }
    
    
    /**
     * N‰ytet‰‰n kappaleen tiedot TextField komponentteihin
     * @param kappale n‰ytett‰v‰ kappale
     */
    public void naytaKappale(Kappale kappale) {
        if (kappale == null) return;
        editArtist.setText(kappale.getArtist());
        editName.setText(kappale.getName());
        editFormat.setText(kappale.getFormat());
        editLabel.setText(kappale.getLabel());
        editBpm.setText(kappale.getBpm());
        editLength.setText(kappale.getLength());
        editGenre.setText(kappale.getGenre());
        editStyle.setText(kappale.getStyle());
        editReleased.setText(kappale.getReleased());
        editCountry.setText(kappale.getCountry());
        editInfo.setText(kappale.getInfo());
    }
    
    
    /**
     * Luodaan kappaleen kysymisdialogi ja palautetaan sama tietue muutettuna tai null
     * @param modalityStage mille ollaan modaalisia, null = sovellukselle
     * @param oletus mit‰ dataan n‰ytet‰‰n oletuksena
     * @return null jos painetaan Cancel, muuten t‰ytetty tietue
     */
    public static Kappale kysyKappale(Stage modalityStage, Kappale oletus) {
        //ModalController.showModal(resurssi, "Track Info", null, "");
        return ModalController.showModal(
                EditTrackController.class.getResource("EditTrackView.fxml"),
                "Track Info",
                modalityStage, oletus, null 
                );
    }


}
