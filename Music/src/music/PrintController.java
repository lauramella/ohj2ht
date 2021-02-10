package music;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

/**
 * @author laura
 * @version 10.2.2021
 *
 */
public class PrintController implements ModalControllerInterface<String>{
    @FXML TextArea tulostusAlue;
    
    @FXML private void handleOK() {
    ModalController.closeStage(tulostusAlue);
    }
    @FXML private void handlePrint() {
      Dialogs.showMessageDialog("Ei osata viel‰ tulostaa");
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
    public void setDefault(String oletus) {
       if (oletus == null) return;
       tulostusAlue.setText(oletus);      
    }
    
        /**
         * N‰ytt‰‰ tulostusalueessa tekstin
         * @param tulostus tulostettava teksti
         */
        public static void tulosta(String tulostus) {
                ModalController.showModeless(PrintController.class.getResource("PrintView.fxml"),
                         "Tulostus", tulostus);
             }
//
}
