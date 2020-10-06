package controleurs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import commandes_serveur.CommandSender;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * Classe representant la connexion au serveur FTP
 * @author Clément Stoliaroff
 */
public class Connexion implements ControleurLocal
{
	/**
	 * Methode permettant d'effectuer la connexion au serveur FTP
	 */
	@Override
	public void exec(ControleurPrincipal controller)
	{
    	try
    	{
    		// On récupère les informations des différents champs
    		int port = Integer.parseInt(controller.getPort().getText());
    		controller.setSocket(new Socket(controller.getHote().getText(), port));
			controller.setBr(new BufferedReader(new InputStreamReader(controller.getSocket().getInputStream())));
			controller.setPs(new PrintStream(controller.getSocket().getOutputStream()));
			
			String reponse;
			
			controller.getFlow().getChildren().clear();
			
			// On récupère les messages d'accueil et on les affiches dans le textflow
			while((reponse = controller.getBr().readLine()).charAt(0) == '1')
			{
				controller.getFlow().getChildren().add(new Text(reponse + "\n"));
			}
			
			// On affiche le dernier message 
			controller.getFlow().getChildren().add(new Text(reponse + "\n"));
			
			if(reponse.charAt(0) == '0')
			{
				// On envoie le nom d'utilisateur et le mot de passe au serveur
				CommandSender.sendCommande(controller, "USER " + controller.getIdentifiant().getText());
				CommandSender.sendCommande(controller, "PASS " + controller.getPassword().getText());
				
				// On récupère les fichiers et dossiers et le chemin courant courant
				CommandSender.sendCommande(controller, "LS");
				CommandSender.sendCommande(controller, "PWD");
			}
		}
    	
    	// On affiche une erreur en cas 
    	catch(NumberFormatException e)
    	{
    		e.printStackTrace();
    		Text erreur = new Text("Erreur de connexion\n");
    		erreur.setFill(Color.RED);
    		controller.getFlow().getChildren().add(erreur);
    	}
    	
    	// 
    	catch (IOException e)
    	{
    		e.printStackTrace();
    		Text erreur = new Text("Erreur de connexion\n");
    		erreur.setFill(Color.RED);
			controller.getFlow().getChildren().add(erreur);
		}
	}
}
