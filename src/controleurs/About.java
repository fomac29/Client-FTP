package controleurs;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * Affichage de boite de dialoge A propos
 * @author Clement Stoliaroff
 *
 */
public class About implements ControleurLocal {

	/**
	 * Affichage de boite de dialoge A propos
	 */
	@Override
	public void exec(ControleurPrincipal controller)
	{
		// On instancie la boite de dialogue
		Alert alert = new Alert(AlertType.INFORMATION);
		
		// On recupere l'icone personnalisee que l'on doit afficher a droite de la fenetre
        ImageView icone = new ImageView(new Image(getClass().getResourceAsStream("/ressources/about.png")));
        
        // On redimensionne l'icone
        icone.setFitHeight(70);
        icone.setFitWidth(70);
        
        // On affiche l'icone
        alert.getDialogPane().setGraphic(icone);
        
        // On recupere la fenetre de l'application
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        
        // On place l'icone de l'application dans la denetre
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/ressources/1465589.png")));
        
        // On modifie le nom de la boite de dialogue
        alert.setTitle("A Propos");
        
        // On affiche le texte
        alert.setHeaderText("Mon client FTP");
        String s ="Version de l'application : En cours de développement...\nDéveloppeur : Clément Stoliaroff";
        alert.setContentText(s);
        
        //On affiche la boite de dialogue
        alert.show();
	}

}
