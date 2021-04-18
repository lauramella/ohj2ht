package music;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


/**
 * @author laura
 * @version 10.2.2021
 *
 */
public class RenameSetController implements ModalControllerInterface<String> {
    @FXML private Button buttonSulje;

    @FXML
    private TextField tekstiUusiNimi;
    private static String uusiNimi;
    @FXML private void handleCancel() {
        uusiNimi = null;
        ModalController.closeStage(buttonSulje);
   } 
    
    @FXML private void handleSave() {
        uusiNimi = tekstiUusiNimi.getText();
        ModalController.closeStage(tekstiUusiNimi);
    }
     
 
    @Override
    public String getResult() {
        return uusiNimi;
    }
    
    

    @Override
    public void handleShown() {
        tekstiUusiNimi.requestFocus();
        
    }

    @Override
    public void setDefault(String arg0) {
        // TODO Auto-generated method stub
        
    }
    
    private static String getUusiNimi() {
        String palautus = uusiNimi;
        return palautus;
    }

    public static String getNimi() {
        return getUusiNimi();
    }

}
