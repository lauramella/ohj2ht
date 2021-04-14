package music;

import java.net.URL;
import javafx.scene.Node;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.ohj2.Mjonot;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import music2.Kappale;
import music2.Music;

/**
 * Muokkausikkunan tapahtumat
 * @author laura
 * @version 10.2.2021
 *
 */
public class EditTrackController implements ModalControllerInterface<Kappale>, Initializable {
    
    @FXML private GridPane gridKappale;
    @FXML private Button buttonSulje;
    @FXML private Label labelVirhe;
    
    @FXML private void handleCancel() {
        ModalController.closeStage(buttonSulje);
   }
    
    @FXML private void handleDelete() {
        poistaKappale();
   }
   
   private void poistaKappale() {
        if ( !Dialogs.showQuestionDialog("Delete track", "Do you want to delete this track?", "Yes", "No") )
           return;
        if (kappaleKohdalla == null) return;
        Kappale kap = kappaleKohdalla;
        music.poistaKappale(kap);
       kappaleKohdalla = null;
        ModalController.closeStage(buttonSulje);
    }
   
  
    

    @FXML private void handleSave() {
        if ( kappaleKohdalla != null && kappaleKohdalla.anna(kappaleKohdalla.ekaKentta()).trim().equals("") ) {
            naytaVirhe("Ei saa olla tyhj‰");
            return;
        }
        ModalController.closeStage(labelVirhe);
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
        kentta = Math.max(apukappale.ekaKentta(), Math.min(kentta, apukappale.getKenttia()-1));
        edits[kentta].requestFocus();      
    }
    
    
    /**
     * @param oletus oletus Kappale
     */
    @Override
    public void setDefault(Kappale oletus) {
        kappaleKohdalla = oletus;
        naytaKappale(edits, kappaleKohdalla);
        
    }
    
    
    //========================================================================================================
    private Kappale kappaleKohdalla;
    private static Kappale apukappale = new Kappale();
    private TextField edits[];
    private int kentta = 0;
    private Music music;
    
    
    /**
    * Luodaan GridPaneen kappaleen tiedot
    * @param gridKappale mihin tiedot luodaan
    * @return luodut tekstikent‰t
    */
    public static TextField[] luoKentat(GridPane gridKappale) {
        gridKappale.getChildren().clear();
        TextField[] fedits = new TextField[apukappale.getKenttia()];
           
        for (int i=0, k = apukappale.ekaKentta(); k < apukappale.getKenttia(); k++, i++) {
            Label label = new Label(apukappale.getKysymys(k));
            gridKappale.add(label, 0, i);
            TextField edit = new TextField();
            fedits[k] = edit;
            edit.setId("e"+k);
            gridKappale.add(edit, 1, i);
        }
        return fedits;
    }
    
    
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
    
    
    /**
     * Tekee tarvittavat muut alustukset.
     */
    protected void alusta() {
        edits = luoKentat(gridKappale);
        for (TextField edit : edits)
            if ( edit != null )
                edit.setOnKeyReleased( e -> kasitteleMuutosKappaleeseen((TextField)(e.getSource())));        
    }
    
    
    private void setKentta(int kentta) {
        this.kentta = kentta;
    }
    
    
    /**
     * K‰sitell‰‰n kappaleeseen tullut muutos
     * @param edit muuttunut kentt‰
     */
    private void kasitteleMuutosKappaleeseen(TextField edit) {
        if (kappaleKohdalla == null) return;
        int k = getFieldId(edit,apukappale.ekaKentta());
        String s = edit.getText();
        String virhe = null;
        virhe = kappaleKohdalla.aseta(k,s); 
        if (virhe == null) {
            Dialogs.setToolTipText(edit,"");
            edit.getStyleClass().removeAll("virhe");
            naytaVirhe(virhe);
        } else {
            Dialogs.setToolTipText(edit,virhe);
            edit.getStyleClass().add("virhe");
            naytaVirhe(virhe);
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
     * N‰ytet‰‰n kappaleen tiedot TextField komponentteihin
     * @param edits taulukko jossa tekstikentti‰
     * @param kappale n‰ytett‰v‰ kappale
     */
    public static void naytaKappale(TextField[] edits, Kappale kappale) {
        if (kappale == null) return;
        for (int k = kappale.ekaKentta(); k < kappale.getKenttia(); k++) {
            edits[k].setText(kappale.anna(k));
        }
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
        return ModalController.<Kappale, EditTrackController>showModal(
                EditTrackController.class.getResource("EditTrackView.fxml"),
                "Track Info",
                modalityStage, oletus,
                ctrl -> { ctrl.setKentta(kentta); ctrl.setMusic(music);  }
                );
    }
     private void setMusic(Music music) {
               this.music = music;
        }

 
    
 


}
