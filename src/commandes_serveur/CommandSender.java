package commandes_serveur;

import java.io.IOException;
import java.net.SocketException;

import controleurs.ControleurPrincipal;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * Classe destinee a envoyer les commandes au serveur et a lire les reponses
 * @author Clement Stoliaroff
 */
public class CommandSender
{

	/**
	 * Determine la commande a envoyer au serveur
	 * @param controller controlleur de l'IHM
	 * @param commande La commande a envoyer au serveur
	 */
	public static void sendCommande(ControleurPrincipal controller, String commande)
	{
		controller.getFlow().getChildren().add(new Text(">> Commande " + commande + "\n"));
		
		String cmd = commande.split(" ")[0];
		
		try
		{
			// Changer de repertoire. Un (..) permet de revenir au repertoire superieur
			if(cmd.equalsIgnoreCase("cd")) (new CommandeCD(controller, commande)).send();
	
			// Telecharger un fichier
			else if(cmd.equalsIgnoreCase("get")) (new CommandeGET(controller, commande)).send();
			
			// Afficher la liste des fichiers et des dossiers du repertoire courant
			else if(cmd.equalsIgnoreCase("ls")) (new CommandeLS(controller, commande)).send();
	
			// Afficher le repertoire courant
			else if(cmd.equalsIgnoreCase("pwd")) (new CommandePWD(controller, commande)).send();
			
			// Envoyer (uploader) un fichier
			else if(cmd.equalsIgnoreCase("stor")) (new CommandeSTOR(controller, commande)).send();
			
			// Le mot de passe pour l'authentification
			else if(cmd.equalsIgnoreCase("pass")) (new CommandePASS(controller, commande)).send();
	
			// Le login pour l'authentification
			else if(cmd.equalsIgnoreCase("user")) (new CommandeUSER(controller, commande)).send();
			
			// Le login pour l'authentification
			else if(cmd.equalsIgnoreCase("tree")) (new CommandeTREE(controller, commande)).send();
			
			// Affichage d'un message d'erreur en cas de commande invalide
			else
			{
				Text erreur = new Text("Commande invalide\n");
				erreur.setFill(Color.RED);
				controller.getFlow().getChildren().add(erreur);
			}
			
			
		}
		
		// Affichage d'un message d'erreur en cas de deconnexion subite du serveur
		catch(SocketException e)
		{
			Text erreur = new Text("Le serveur s'est déconnecté\n");
			erreur.setFill(Color.RED);
			controller.getFlow().getChildren().add(erreur);
			controller.getConnexion().setDisable(false);
			controller.getDeconnexion().setDisable(true);
		}
		
		// Affichage d'un message d'erreur d'erreur d'envoi au serveur
		catch(IOException e)
		{
			Text erreur = new Text("Erreur d'envoi de la commande " + cmd + "\n");
			erreur.setFill(Color.RED);
			controller.getFlow().getChildren().add(erreur);
		}
		
		controller.getScrollpane().vvalueProperty().bind(controller.getFlow().heightProperty());
	}
	
}
