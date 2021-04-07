package music;

import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.ohj2.Mjonot;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import music2.Kappale;
import music2.Music;
import music2.SailoException;

/**
 * @author laura
 * @version 7.4.2021
 *
 */
public class AddNewTrackController implements ModalControllerInterface<Kappale>, Initializable {
    @FXML private Label labelVirhe;
    @FXML private Button buttonSulje;
    @FXML private GridPane gridKappale;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        alusta();
        
    }

    @Override
    public Kappale getResult() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void handleShown() {
        kentta = Math.max(uusikappale.ekaKentta(), Math.min(kentta, uusikappale.getKenttia()-1));
        edits[kentta].requestFocus();        
    }

    @Override
    public void setDefault(Kappale oletus) {
        uusikappale = oletus;
        EditTrackController.naytaKappale(edits, uusikappale);
        
    }
    
    @FXML private void handleSaveNew() {
        try {
            music.tallenna();
        } catch (SailoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    @FXML private void handleCancel() {
        ModalController.closeStage(buttonSulje);;
   }
    

    private static Kappale uusikappale = new Kappale();
    private TextField edits[];
    private int kentta = 0;
    private Music music;
    
    
    /**
    * Tyhjent‰‰n tekstikent‰t 
    * @param edits tyhjennett‰v‰t kent‰t
    */
    public static void tyhjenna(TextField[] edits) {
        for (TextField edit: edits) 
            if ( edit != null ) edit.setText(""); 
    }
    
    
    /**
     * Palautetaan komponentin id:st‰ saatava luku
     * @param obj tutkittava komponentti
     * @param oletus mik‰ arvo jos id ei ole kunnollinen
     * @return komponentin id lukuna 
     */
     public static int getFieldId(Object obj, int oletus) {
         if ( !( obj instanceof Node)) return oletus;
         Node node = (Node)obj;
         return Mjonot.erotaInt(node.getId().substring(1),oletus);
     }
     






    @SuppressWarnings("unused")
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
     * Tekee tarvittavat muut alustukset.
     */
    protected void alusta() {
      //  edits = luoKentat(gridKappale);
     //   for (TextField edit : edits)
           // if ( edit != null )
               // edit.setOnKeyReleased( e -> kasitteleMuutosKappaleeseen((TextField)(e.getSource())));        
    }
    
    
    private void setKentta(int kentta) {
        this.kentta = kentta;
    }
    

    
    /**
     * Luodaan kappaleen kysymisdialogi ja palautetaan sama tietue muutettuna tai null
     * @param modalityStage mille ollaan modaalisia, null = sovellukselle
     * @param oletus mit‰ dataan n‰ytet‰‰n oletuksena
     * @param kentta mik‰ kentt‰ saa fokuksen kun n‰ytet‰‰n 
     * @param music music
     * @return null jos painetaan Cancel, muuten t‰ytetty tietue
     */
    public static Kappale kysyKappale(Stage modalityStage, Kappale oletus, int kentta, Music music) {
        //ModalController.showModal(resurssi, "Track Info", null, "");
        return ModalController.<Kappale, AddNewTrackController>showModal(
                AddNewTrackController.class.getResource("AddNewTrackView.fxml"),
                "Track Info",
                modalityStage, oletus,
                ctrl -> { ctrl.setKentta(kentta); ctrl.setMusic(music); }
                );
    }
    
    private void setMusic(Music music) {
        this.music = music;
    }

}
