package music;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * jsj
 * @author laura
 * @version 11.2.2021
 *
 */
public class NewUserController implements ModalControllerInterface<String> {
    @FXML private Button buttonSulje;
    @FXML private TextField tekstiUusiKayttaja;
    private static String uusiKayttaja = "";
    
    
    @FXML private void handleCancel() {
        uusiKayttaja = null;
        ModalController.closeStage(buttonSulje);
   }
    
    @FXML private void handleAddUser() {
        uusiKayttaja = tekstiUusiKayttaja.getText();
        ModalController.closeStage(buttonSulje);
   } 
 
    @Override
    public String getResult() {
        return uusiKayttaja;
    }

    @Override
    public void handleShown() {
        tekstiUusiKayttaja.requestFocus();       
    }

    @Override
    public void setDefault(String oletus) {
        uusiKayttaja = oletus;       
    }
    
    
    public static String getUusiKayttaja() {
        String palautus = uusiKayttaja;
        return palautus;
    }

    public static String getNimi() {
        return getUusiKayttaja();
    }
    

}
