package music;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * jsj
 * @author laura
 * @version 11.2.2021
 *
 */
public class NewUserController implements ModalControllerInterface<String> {
    @FXML private Button buttonSulje;
    @FXML private void handleCancel() {
        ModalController.closeStage(buttonSulje);
   }
    @FXML private void handleAddUser() {
        Dialogs.showMessageDialog("Ei toimi lisääminen");
   } 
 
    @Override
    public String getResult() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setDefault(String arg0) {
        // TODO Auto-generated method stub
        
    }
    

}
