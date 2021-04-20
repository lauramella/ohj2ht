package music;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Tekee uuden setin ja nime‰‰ sen
 * @author laura
 * @version 10.2.2021
 *
 */
public class NewSetController implements ModalControllerInterface<String> {
    @FXML private Button buttonSulje;
    @FXML private TextField tekstiNimi;

    @FXML private void handleCancel() {
        setinNimi = null;
        ModalController.closeStage(buttonSulje);
    }

    @FXML private void handleSave() {
        setinNimi = tekstiNimi.getText();
        ModalController.closeStage(buttonSulje); 
    } 

    private static String setinNimi;

    @Override
    public String getResult() {
        return setinNimi;
    }


    @Override
    /**
     * Mit‰ tehd‰‰n kun dialogi on n‰ytetty
     */
    public void handleShown() {
        tekstiNimi.requestFocus();       
    }


    @Override
    public void setDefault(String arg0) {
        //       
    }


    /**
     * Palauttaa uuden setin nimen
     * @return uuden setin nimen
     */
    public static String getSetinNimi() {
        String palautus = setinNimi;
        return palautus;
    }


    /**
     * Palauttaa k‰ytt‰j‰n lis‰‰m‰n uuden setin nimen hyˆdynt‰m‰ll‰ aliohjelmaa getSetinNimi()
     * @return uuden setin nimi
     */
    public static String setinNimi() {
        return getSetinNimi();
    }
}