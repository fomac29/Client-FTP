package commandes_serveur;

import java.io.IOException;
import java.net.SocketException;

import controleurs.ControleurPrincipal;
import javafx.scene.text.Text;
import ressources.Fichier_Serveur;

/**
 * Classe representant la commande LS
 * @author Clement Stoliaroff
 */
public class CommandeLS extends Commande {
	
	/**
	 * Constructeur de la commande LS
	 * @param controller controlleur de l'IHM
	 * @param commande La commande a envoyer au serveur
	 */
	public CommandeLS(ControleurPrincipal controller, String commande) {
		super(controller, commande);
	}

	/**
	 * Envoi de la commande LS au serveur, qui permet de lister les fichiers
	 * et dossiers du repertoire courant du serveur et lecture de la reponse
	 */
	public void send() throws IOException, SocketException
	{
		// Envoi de la commande
		controller.getPs().println(commande);
		
		String reponse;
		
		// On vide le tableau de fichiers distants
		controller.getServer_files().getItems().clear();
		
		// On recupere les fichiers dans le tableau, ainsi que dans le flow
		while((reponse = controller.getBr().readLine()).charAt(0) == '1' )
		{
			System.out.println(reponse);
			controller.getFlow().getChildren().add(new Text(reponse + "\n"));
			controller.getServer_files().getItems().add(new Fichier_Serveur(reponse));
		}
	
		// On recupere la derniere reponse
		controller.getFlow().getChildren().add(new Text(reponse + "\n"));
		System.out.println(reponse);
		
		// On recupere les nombres de fichiers, de dossiers et d'octets presents dans le dossier courant
		if(reponse.charAt(0) == '0')
		{
			String[] donnees = reponse.split(" ");
			controller.getDescription_serveur_files().setText(donnees[1] + " fichiers et " + donnees[2] + " Dossiers. Taille totale : " + donnees[3] + " octets.");
		}
	}

}
