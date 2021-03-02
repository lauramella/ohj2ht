package music;

import fi.jyu.mit.fxgui.ComboBoxChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * @author laura
 * @version 10.2.2021
 *
 */
public class LoginController implements ModalControllerInterface<String> {
        @FXML private ComboBoxChooser<String> textVastaus1;
        private String vastaus = null;
        
        
        @FXML private Button buttonOk;
        @FXML private void handleOk() {
           ModalController.closeStage(textVastaus1);
       }

                
        @FXML private void handleNewUser() {
            var resurssi4 = LoginController.class.getResource("NewUserView.fxml");
            ModalController.showModal(resurssi4, "New user", null, "");
       }
        
        @Override
        public String getResult() {
            // TODO Auto-generated method stub
            return vastaus;
        }

        @Override
        public void handleShown() {
            textVastaus1.requestFocus();           
        }

        @Override
        public void setDefault(String oletus) {
            //
            
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
         
    }
