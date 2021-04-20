package music;

import java.net.URL;
import java.util.ResourceBundle;
import fi.jyu.mit.fxgui.ComboBoxChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Kysyy uuden k�ytt�j�n, jonka tiedoston avataan. Voi lis�t� uuden k�ytt�j�n.
 * 
 * @author laura
 * @version 10.2.2021
 *
 */
public class LoginController implements ModalControllerInterface<String>, Initializable {
    @FXML private ComboBoxChooser<String> textVastaus1;
    @FXML private Button buttonOk;       

    @FXML private void handleOk() {
        vastaus = textVastaus1.getSelectedText();
        ModalController.closeStage(textVastaus1);        
    }

    @FXML private void handleNewUser() {
        String uusiKayttaja = "";
        var resurssi = LoginController.class.getResource("NewUserView.fxml");
        ModalController.showModal(resurssi, "New user", null, "");
        uusiKayttaja = NewUserController.getUusiKayttaja();
        if (uusiKayttaja != null) textVastaus1.addExample(uusiKayttaja);         
    }

    private static String vastaus = "";

    @Override
    public String getResult() {
        return vastaus;
    }


    @Override
    /**
     * Mit� tehd��n kun dialogi on n�ytetty
     */
    public void handleShown() {
        textVastaus1.requestFocus();           
    }


    @Override
    public void setDefault(String oletus) {
        vastaus = oletus;          
    }


    /**
     * Luodaan nimenkysymisdialogi ja palautetaan valittu nimi tai null
     * @param modalityStage mille ollaan modaalisia, null = sovellukselle
     * @param oletus mit� nime� n�ytet��n oletuksena
     * @return null jos painetaan Cancel, muuten kirjoitettu nimi
     */
    public static String kysyNimi(Stage modalityStage, String oletus) {
        return ModalController.showModal(
                LoginController.class.getResource("LoginView.fxml"),
                "Music",
                modalityStage, oletus);
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        //           
    }


    /**
     * Palauttaa uuden k�ytt�j�n nimen
     * @return uuden k�ytt�j�n nimen
     */
    private static String getUusiNimi() {
        String palautus = vastaus;
        return palautus;
    }


    /**
     * Palauttaa k�ytt�j�n nimen hy�dynt�m�ll� aliohjelmaa getUusiNimi()
     * @return k�ytt�j�n nimi
     */
    public static String getNimi() {
        return getUusiNimi();
    }       
}