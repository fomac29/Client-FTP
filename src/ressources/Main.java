package ressources;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;


/**
 * Classe servant a lancer l'IHM
 * @author Clement Stoliaroff
 */
public class Main extends Application {
	
	/**
	 * Charge l'IHM
	 */
	@Override
	public void start(Stage primaryStage)
	{
		try
		{
			// charge le fichier FXML
			Parent root = FXMLLoader.load(getClass().getResource("Client_FTP.fxml"));
			
			// Modifie le nom de l'application
			primaryStage.setTitle("Mon client FTP");
			
			// Specifie la scene a utiliser
			primaryStage.setScene(new Scene(root));
			
			// Change l'icone de l'application
			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("1465589.png")));
			
			// Affiche l'IHM
			primaryStage.show();
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * @param args arguments
	 */
	public static void main(String[] args)
	{
		launch(args);
	}
}
