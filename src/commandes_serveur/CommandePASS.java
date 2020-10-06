package commandes_serveur;

import java.io.IOException;
import java.net.SocketException;

import controleurs.ControleurPrincipal;
import javafx.scene.text.Text;

/**
 * Classe representant la commande PASS
 * @author Clement Stoliaroff
 */
public class CommandePASS extends Commande
{
	
	/**
	 * Constructeur de la commande PASS
	 * @param controller controlleur de l'IHM
	 * @param commande La commande a envoyer au serveur
	 */
	public CommandePASS(ControleurPrincipal controller, String commande) {
		super(controller, commande);
	}

	/**
	 * Envoi de la commande PASS au serveur, qui permet d'envoyer le mot de passe du client et reception de la reponse
	 */
	public void send() throws IOException, SocketException
	{
		// Envoi de la commande PASS au serveur
		controller.getPs().println(commande);
		
		String reponse;
		
		// On affiche les reponses dans le flow
		while((reponse = controller.getBr().readLine()).charAt(0) == '1' )
		{
			controller.getFlow().getChildren().add(new Text(reponse + "\n"));
			System.out.println(reponse);
		}
		
		controller.getFlow().getChildren().add(new Text(reponse + "\n"));
		System.out.println(reponse);
		
		
		if(reponse.charAt(0) == '0')
		{
			// On active le bouton de déconnexion et on désactive celui de connexion
			controller.getConnexion().setDisable(true);
			controller.getDeconnexion().setDisable(false);
		}
		
	}
}
