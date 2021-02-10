package music;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Muokkausikkunan tapahtumat
 * @author laura
 * @version 10.2.2021
 *
 */
public class EditTrackController implements ModalControllerInterface<String> {
    @FXML private Button buttonSulje;
    @FXML private void handleCancel() {
        ModalController.closeStage(buttonSulje);;
   }
    @FXML private void handleDelete() {
        Dialogs.showMessageDialog("Ei toimi poisto");
   }
    @FXML private void handleSave() {
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

}
