package music;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import music2.Music;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/**
 * @author laura
 * @version 1.2.2021
 *
 */
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {

            final FXMLLoader ldr = new FXMLLoader(getClass().getResource("MusicGUIView.fxml"));
            final Pane root = (Pane)ldr.load();
            final MusicGUIController musicCtrl = (MusicGUIController)ldr.getController();

            final Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("music.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Music");
            primaryStage.setOnCloseRequest((event) -> {
                if ( !musicCtrl.voikoSulkea() ) event.consume();
            });

            Music music = new Music();
            musicCtrl.setMusic(music);

            primaryStage.show();
            // if ( !musicCtrl.avaa() ) Platform.exit();

            Application.Parameters params = getParameters(); 
            if ( params.getRaw().size() > 0 ) 
                musicCtrl.lueTiedosto(params.getRaw().get(0));  
            else
                if ( !musicCtrl.avaa() ) Platform.exit();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        launch(args);
    }
}
