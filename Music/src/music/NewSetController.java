package music;


import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * @author laura
 * @version 10.2.2021
 *
 */
public class NewSetController implements ModalControllerInterface<String> {
    @FXML private Button buttonSulje2;
    @FXML private void handleCancel2() {
        ModalController.closeStage(buttonSulje2);
   }
    @FXML private void handleSave2() {
        Dialogs.showMessageDialog("Ei toimi tallennus");
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
//
}