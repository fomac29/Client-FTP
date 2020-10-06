package commandes_serveur;

import java.io.IOException;
import java.net.SocketException;

import controleurs.ControleurPrincipal;
import javafx.scene.text.Text;

/**
 * Classe representant la commande CD
 * @author Clement Stoliaroff
 */
public class CommandeCD extends Commande {
	

	/**
	 * Constructeur de la commande CD
	 * @param controller controlleur de l'IHM
	 * @param commande La commande a envoyer au serveur
	 */
	public CommandeCD(ControleurPrincipal controller, String commande) {
		super(controller, commande);
	}
	

	/**
	 * Envoi de la commande CD au serveur et lecture de la reponse
	 */
	public void send() throws IOException, SocketException
	{
		// Envoi de la commande au serveur
		controller.getPs().println(commande);
		
		// Lecture de la reponse du serveur
		controller.getFlow().getChildren().add(new Text(controller.getBr().readLine() + "\n"));
	}
}
