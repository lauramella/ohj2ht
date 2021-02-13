package music;
	
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
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
		        
		        primaryStage.show();
		        if ( !musicCtrl.avaa() ) Platform.exit();
		          } catch(Exception e) {
		        e.printStackTrace();
		          }}
	


		    
		    
		    
		    
		    
		/*/BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("MusicGUIView.fxml"));
			Scene scene = new Scene(root,800,500);
			scene.getStylesheets().add(getClass().getResource("music.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	/**
	 * @param args ei käytössä
	 */
	/**
	 * @param args ei käytössä
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
