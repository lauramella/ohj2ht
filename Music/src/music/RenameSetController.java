package music;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


/**
 * Nime‰‰ setin uudestaan
 * 
 * @author laura
 * @version 20.4.2021
 */
public class RenameSetController implements ModalControllerInterface<String> {
    @FXML private Button buttonSulje;
    @FXML    private TextField tekstiUusiNimi;

    @FXML private void handleCancel() {
        uusiNimi = null;
        ModalController.closeStage(buttonSulje);
    } 

    @FXML private void handleSave() {
        uusiNimi = tekstiUusiNimi.getText();
        ModalController.closeStage(tekstiUusiNimi);
    }

    private static String uusiNimi;


    @Override
    public String getResult() {
        return uusiNimi;
    }


    @Override
    /**
     * Mit‰ tehd‰‰n kun dialogi on n‰ytetty
     */
    public void handleShown() {
        tekstiUusiNimi.requestFocus();

    }

    @Override
    public void setDefault(String arg0) {
        //       
    }


    /**
     * Palauttaa setin uuden nimen
     * @return setin uusi nimi
     */
    private static String getUusiNimi() {
        String palautus = uusiNimi;
        return palautus;
    }


    /**
     * Palauttaa setin uuden nimen kutsumalla aliohjelmaa getUusiNimi()
     * @return setin uusi nimi
     */
    public static String getNimi() {
        return getUusiNimi();
    }
}