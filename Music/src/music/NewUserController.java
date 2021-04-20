package music;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Lis‰t‰‰n uusi k‰ytt‰j‰, palauttaa k‰ytt‰j‰n antaman k‰ytt‰j‰nimen
 * 
 * @author laura
 * @version 11.2.2021
 *
 */
public class NewUserController implements ModalControllerInterface<String> {
    @FXML private Button buttonSulje;
    @FXML private TextField tekstiUusiKayttaja;
       
    @FXML private void handleCancel() {
        uusiKayttaja = null;
        ModalController.closeStage(buttonSulje);
   }
    
    @FXML private void handleAddUser() {
        uusiKayttaja = tekstiUusiKayttaja.getText();
        ModalController.closeStage(buttonSulje);
   } 
    
    private static String uusiKayttaja = "";
 
    @Override
    public String getResult() {
        return uusiKayttaja;
    }
    

    @Override
    /**
     * Mit‰ tehd‰‰n kun dialogi on n‰ytetty
     */
    public void handleShown() {
        tekstiUusiKayttaja.requestFocus();       
    }
    

    @Override
    public void setDefault(String oletus) {
        uusiKayttaja = oletus;       
    }
    
    
    /**
     * Palauttaa uuden k‰ytt‰j‰n nimen
     * @return uuden k‰ytt‰j‰n nimen
     */
    public static String getUusiKayttaja() {
        String palautus = uusiKayttaja;
        return palautus;
    }
    

    /**
     * Palauttaa k‰ytt‰j‰n lis‰‰m‰n uuden k‰ytt‰j‰n nimen hyˆdynt‰m‰ll‰ aliohjelmaa getUusiKayttaja()
     * @return uusi k‰ytt‰j‰
     */
    public static String getNimi() {
        return getUusiKayttaja();
    }
}
