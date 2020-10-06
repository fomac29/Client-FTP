package commandes_serveur;

import java.io.IOException;
import java.net.SocketException;

import controleurs.ControleurPrincipal;
import javafx.scene.text.Text;

/**
 * Classe representant la commande PWD
 * @author Clement Stoliaroff
 */
public class CommandeUSER extends Commande {

	/**
	 * Constructeur de la commande USER
	 * @param controller controlleur de l'IHM
	 * @param commande La commande a envoyer au serveur
	 */
	public CommandeUSER(ControleurPrincipal controller, String commande) {
		super(controller, commande);
	}

	/**
	 * Envoi la commande USER au serveur
	 */
	public void send() throws IOException, SocketException
	{
		// Envoi de la commande au serveur
		controller.getPs().println(commande);
		String reponse;
		
		while((reponse = controller.getBr().readLine()).charAt(0) == '1' )
		{
			controller.getFlow().getChildren().add(new Text(reponse + "\n"));
			System.out.println(reponse);
		}
		
		// Reception de la reponse du serveur
		controller.getFlow().getChildren().add(new Text(reponse + "\n"));
		System.out.println(reponse);
	}

}
