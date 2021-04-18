package music;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.ComboBoxChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import music2.Music;
import music2.Relaatio;

/**
 * @author laura
 * @version 10.2.2021
 *
 */
public class LoginController implements ModalControllerInterface<String>, Initializable {
        @FXML private ComboBoxChooser<String> textVastaus1;
        private static String vastaus = "";
        
            
        
        
        @FXML private Button buttonOk;
        @FXML private void handleOk() {
           vastaus = textVastaus1.getSelectedText();
           System.out.println(vastaus);
           ModalController.closeStage(textVastaus1);
           
       }
                
        @FXML private void handleNewUser() {
            String uusiKayttaja = "";
            var resurssi = LoginController.class.getResource("NewUserView.fxml");
            ModalController.showModal(resurssi, "New user", null, "");
            uusiKayttaja = NewUserController.getUusiKayttaja();
            if (uusiKayttaja != null) textVastaus1.addExample(uusiKayttaja);
            
       }       
      
        
        @Override
        public String getResult() {
            return vastaus;
        }

        @Override
        public void handleShown() {
            textVastaus1.requestFocus();           
        }

        @Override
        public void setDefault(String oletus) {
            vastaus = oletus;          
        }
        
        /**
         * @param modalityStage l
         * @param oletus ö
         * @return ö
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
       

        private static String getUusiNimi() {
            String palautus = vastaus;
            return palautus;
        }

        public static String getNimi() {
            return getUusiNimi();
        }
         
    }
