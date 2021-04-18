package music;


import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import music2.SailoException;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import music2.Kappale;
import music2.Music;
import music2.Setti;

/**
 * @author laura
 * @version 10.2.2021
 *
 */
public class NewSetController implements ModalControllerInterface<String> {
    @FXML private Button buttonSulje;
    @FXML private TextField tekstiNimi;
    private static String setinNimi;
    
    @FXML private void handleCancel() {
        setinNimi = null;
        ModalController.closeStage(buttonSulje);
   }
    @FXML private void handleSave() {
        setinNimi = tekstiNimi.getText();
        ModalController.closeStage(buttonSulje); 
   } 
 
    @Override
    public String getResult() {
        return setinNimi;
    }

    @Override
    public void handleShown() {
        tekstiNimi.requestFocus();       
    }

    @Override
    public void setDefault(String arg0) {
        // TODO Auto-generated method stub
        
    }
    
    public static String getSetinNimi() {
        String palautus = setinNimi;
        return palautus;
    }

    public static String setinNimi() {
        return getSetinNimi();
    }
    
   
 
}
