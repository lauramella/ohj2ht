package music;

import fi.jyu.mit.fxgui.*;
import javafx.fxml.FXML;

/**
 * @author laura
 * @version 1.2.2021
 *
 */
public class MusicGUIController {
    @FXML private void handlePrint() {
        PrintController.tulosta("Juujuu");
        }
   
    @FXML private void handleAddTrack() {
        Dialogs.showMessageDialog("Ei osata vielä lisätä kappaletta settiin");
   }
    @FXML private void handleDeleteTrack() {
        Dialogs.showMessageDialog("Ei osata vielä poistaa setistä");
   } 
    @FXML private void handleNewTrack() {
        Dialogs.showMessageDialog("Ei osata vielä lisätä uutta kappaletta");
   }
    @FXML private void handleEdit() {
        var resurssi = MusicGUIController.class.getResource("EditTrackView.fxml");
        ModalController.showModal(resurssi, "Track Info", null, "");
   }
}
