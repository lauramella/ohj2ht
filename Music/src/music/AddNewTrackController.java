package music;

import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
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
        return uusikappale;
    }

    @Override
    public void handleShown() {
        kentta = Math.max(uusikappale.ekaKentta(), Math.min(kentta, uusikappale.getKenttia()-1));
        edits[kentta].requestFocus();        
    }

    @Override
    public void setDefault(Kappale oletus) {
        uusikappale = oletus;
        naytaKappale(edits, uusikappale);
        
    }
    
    @FXML private void handleSaveNew() {
        ModalController.closeStage(labelVirhe);
    }
    
    
    @FXML private void handleCancel() {
        uusikappale = null;
        ModalController.closeStage(buttonSulje);
   }
    

    private static Kappale uusikappale = new Kappale();
    private TextField edits[];
    private int kentta = 0;
    
    /**
    * Luodaan GridPaneen kappaleen tiedot
    * @param gridKappale mihin tiedot luodaan
    * @return luodut tekstikent‰t
    */
    public static TextField[] luoKentat(GridPane gridKappale) {
        gridKappale.getChildren().clear();
        TextField[] fedits = new TextField[uusikappale.getKenttia()];
           
        for (int i=0, k = uusikappale.ekaKentta(); k < uusikappale.getKenttia(); k++, i++) {
            Label label = new Label(uusikappale.getKysymys(k));
            gridKappale.add(label, 0, i);
            TextField edit = new TextField();
            fedits[k] = edit;
            edit.setId("e"+k);
            gridKappale.add(edit, 1, i);
        }
        return fedits;
    }
    
        /**
        * N‰ytet‰‰n tietueen tiedot TextField komponentteihin
         * @param edits taulukko TextFieldeist‰ johon n‰ytet‰‰n
         * @param kappale n‰ytett‰v‰ tietue
         */
        public static void naytaKappale(TextField[] edits, Kappale kappale) {
            if (kappale == null) return;
            for (int k = kappale.ekaKentta(); k < kappale.getKenttia(); k++) {
                edits[k].setText(kappale.anna(k));
            }
       }

    
    /**
     * K‰sitell‰‰n kappaleeseen tullut muutos
     * @param edit muuttunut kentt‰
     */
    private void kasitteleMuutosKappaleeseen(TextField edit) {
        if (uusikappale == null) return;
        int k = getFieldId(edit,uusikappale.ekaKentta());
        String s = edit.getText();
        String virhe = null;
        virhe = uusikappale.aseta(k,s); 
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
     


    private void naytaVirhe(String virhe) {
        if ( virhe == null || virhe.isEmpty() ) {
            labelVirhe.setText("");
            labelVirhe.getStyleClass().removeAll("virhe");
            return;
        }
        labelVirhe.setText(virhe);
        labelVirhe.getStyleClass().add("virhe");
    }
    
    
    private void setKentta(int kentta) {
        this.kentta = kentta;
    }
    

    
    /**
     * Luodaan kappaleen kysymisdialogi ja palautetaan sama tietue muutettuna tai null
     * @param modalityStage mille ollaan modaalisia, null = sovellukselle
     * @param oletus mit‰ dataan n‰ytet‰‰n oletuksena
     * @param kentta mik‰ kentt‰ saa fokuksen kun n‰ytet‰‰n c
     * @return null jos painetaan Cancel, muuten t‰ytetty tietue
     */
    public static Kappale kysyKappale(Stage modalityStage, Kappale oletus, int kentta) {
        return ModalController.<Kappale, AddNewTrackController>showModal(
                AddNewTrackController.class.getResource("AddNewTrackView.fxml"),
                "Track Info",
                modalityStage, oletus,
                ctrl -> { ctrl.setKentta(kentta); }
                );
    }
    

}
