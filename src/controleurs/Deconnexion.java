package controleurs;

import java.io.IOException;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * Classe representant la deconnexion du serveur FTP
 * @author Clement Stoliaroff
 */
public class Deconnexion implements ControleurLocal
{
	/**
	 * Methode permettant d'effectuer la deconnexion du serveur FTP
	 */
	@Override
	public void exec(ControleurPrincipal controller)
	{
		// Envoi de la commande QUIT au serveur
		controller.getPs().println("QUIT");
    	
    	try
    	{
    		// Fermeture de la socket et des flots
    		controller.getPs().close();
    		controller.getBr().close();
    		controller.getSocket().close();
			
    		// On desactive le bouton de deconnexion et on actve celui de connexion
			controller.getConnexion().setDisable(false);
			controller.getDeconnexion().setDisable(true);
			
			// On affiche la commande
			controller.getFlow().getChildren().add(new Text("QUIT\n"));
			
			// On efface le chemin distant
			controller.getSite_distant().setValue("");
			
			// On supprime l'historique du chemin distant
			controller.getSite_distant().getItems().clear();
			
			// On efface le tableau de fichiers distants
			controller.getServer_files().getItems().clear();
			
			// On modifie le label
			controller.getDescription_serveur_files().setText("Deconnecté");
		}
    	
    	// On affiche une erreur si une erreur 
    	catch (IOException e)
    	{
    		Text erreur = new Text("Erreur de déconnexion\n");
    		erreur.setFill(Color.RED);
    		controller.getFlow().getChildren().add(erreur);
		}
	}

	

}
