package music;


import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import music2.SailoException;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import music2.Kappale;
import music2.Music;
import music2.Setti;

/**
 * @author laura
 * @version 10.2.2021
 *
 */
public class NewSetController implements ModalControllerInterface<String> {
    private Music music;
   // @FXML private ListChooser<Setti> chooserSetit;
    @FXML private Button buttonSulje;
    @FXML private void handleCancel() {
        ModalController.closeStage(buttonSulje);
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
    
    
    /**
     * Asetetaan k‰ytett‰v‰ music
     * @param music jota k‰ytet‰‰n
     */
    public void setMusic(Music music) {
        this.music = music;
    }
    
    
    
    
//  /**
//   * lis‰t‰‰n uusi setti
//   */
//  public void uusiSetti() {
//      Setti setti = new Setti();
//      setti.rekisteroi();
//      setti.taytaSettiTiedoilla();
//      music.lisaa(setti);
//  }  

}
