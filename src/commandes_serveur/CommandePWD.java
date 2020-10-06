package commandes_serveur;

import java.io.IOException;
import java.net.SocketException;

import controleurs.ControleurPrincipal;
import javafx.scene.text.Text;

/**
 * Classe representant la commande PWD
 * @author Clement Stoliaroff
 */
public class CommandePWD extends Commande {

	/**
	 * Constructeur de la commande PWD
	 * @param controller controlleur de l'IHM
	 * @param commande La commande a envoyer au serveur
	 */
	public CommandePWD(ControleurPrincipal controller, String commande) {
		super(controller, commande);
	}

	/**
	 * Envoi de la commande PWD au serveur, qui permet d'afficher le chemin absolu du
	 * dossier courant du serveur et reception de la reponse
	 */
	public void send() throws IOException, SocketException
	{
		// On envoie la commande au serveur
		controller.getPs().println(commande);
		
		String reponse;
		
		// On récupère les messages d'accueil et on les affiches dans le textflow
		while((reponse = controller.getBr().readLine()).charAt(0) == '1')
		{
			controller.getFlow().getChildren().add(new Text(reponse + "\n"));
			System.out.println(reponse);
		}
		
		// On affiche la reponse dans le flow
		controller.getFlow().getChildren().add(new Text(reponse + "\n"));
		System.out.println(reponse);
		
		//Si la reponse n'est pas une erreur
		if(reponse.charAt(0) == '0')
		{
			// On divise la reponse afin de na pas tenir compte du code de reponse
			String[] path = reponse.split(" ");
			String res = path[1];
			
			// Si le chemin contient des espaces, on le reconstitue
			for(int i = 2; i < path.length; i++)
			{
				res += " " + path[i];
			}
			
			// On affiche le chemin dans la combobox
			controller.getSite_distant().setValue(res);
			
			controller.getSite_distant().getItems().add(res);
		}
	}

}
