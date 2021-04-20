package music;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.print.PrinterJob;
import javafx.scene.control.TextArea;
import javafx.scene.web.WebEngine;

/**
 * @author laura
 * @version 20.4.2021
 *
 */
public class PrintController implements ModalControllerInterface<String>{
    @FXML TextArea tulostusAlue;

    @FXML private void handlePrint() {
        PrinterJob job = PrinterJob.createPrinterJob();
        if ( job != null && job.showPrintDialog(null) ) {
            WebEngine webEngine = new WebEngine();
            webEngine.loadContent("<pre>" + tulostusAlue.getText() + "</pre>");
            webEngine.print(job);
            job.endJob();
        }
    }


    @Override
    public String getResult() {
        //
        return null;
    }

    @Override
    /**
     * Mit‰ tehd‰‰n kun dialogi on n‰ytetty
     */

    public void handleShown() {
        //      
    }

    @Override
    public void setDefault(String oletus) {
        if (oletus == null) return;
        tulostusAlue.setText(oletus);      
    }

    /**
     * N‰ytt‰‰ tulostusalueessa tekstin
     * @param tulostus tulostettava teksti
     * @return kontrolleri, jolta voidaan pyyt‰‰ lis‰‰ tietoa
     */
    public static PrintController tulosta(String tulostus) {
        PrintController tulostusCtrl =
                ModalController.showModeless(PrintController.class.getResource("PrintView.fxml"),
                        "Tulostus", tulostus);
        return tulostusCtrl;
    }


    /**
     * @return textarea
     */
    public TextArea getTextArea() {
        return tulostusAlue;
    }

}