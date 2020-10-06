package commandes_serveur;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.SocketException;

import controleurs.ControleurPrincipal;
import controleurs.LocalLS;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * Classe representant la commande GET
 * @author Clement Stoliaroff
 */
public class CommandeGET extends Commande implements Runnable {
	
	private volatile String[] reponses;
	
	/**
	 * Constructeur de la commande GET
	 * @param controller Le controlleur de l'IHM
	 * @param commande La commande a envoyer au serveur
	 */
	public CommandeGET(ControleurPrincipal controller, String commande)
	{
		super(controller, commande);
		this.reponses = new String[3];
		
		for(int i = 0; i < reponses.length; i++)
		{
			this.reponses[i] = "";
		}
	}

	/**
	 * Cree un thread afin de telecharger un fichier depuis le serveur
	 */
	public void send()
	{
		new Thread(this).start();
	}

	/**
	 * envoi de la commande GET au serveur et reception du fichier
	 */
	@Override
	public void run()
	{
		try
		{
			// On divise la commande afin de recuperer le nom du fichier a telecharger
			String [] tabCommand = commande.split(" ");
			
			// On recupère le nom du fichier a telecharger
			String fileNameCommand = tabCommand[1];
			
			// Si le nom du fichier contiens des espaces, on recupère son nom complet
			for(int i = 2; i < tabCommand.length; i++)
			{
				fileNameCommand += " " + tabCommand[i];
			}
			
			File file;
			
			// Si le fichier contient une extension
			if(fileNameCommand.contains("."))
			{
				file = new File(controller.getSite_local().getValue() + "\\" + fileNameCommand);
				
				// Recuperation de l'extension
				String extension = fileNameCommand.substring(fileNameCommand.lastIndexOf('.'));
				
				// Recuperation du nom du fichier (a l'exception de l'extension
				String name = fileNameCommand.substring(0, fileNameCommand.lastIndexOf('.'));
				
				int i = 2;
				
				// On ajoute un identifiant au fichier, et on change celui-ci tant que le fichier existe
				while(file.exists() == true)
				{
					file = new File(controller.getSite_local().getValue() + "\\" + name + " (" + i + ")" + extension);
					i++;
				}
			}
			
			// Si le fichier ne contient pas d'extension
			else
			{
				String fileName = fileNameCommand;
				file = new File(controller.getSite_local().getValue() + "\\" + fileNameCommand);
				
				int i = 2;
				
				// On ajoute un identifiant au fichier, et on change celui-ci tant que le fichier existe
				while(file.exists() == true)
				{
					file = new File(controller.getSite_local().getValue() + "\\" + fileName + " (" + i + ")");
					i++;
				}
			}

			// Envoi de la commande au serveur
			controller.getPs().println(commande);
			
			// On lit la reponse du serveur
			reponses[0] = controller.getBr().readLine();
			
			// Si la reponse n'est pas une erreur
			if(reponses[0].charAt(0) == '1')
			{
				System.out.println(reponses[0]);
				
				Platform.runLater(new Runnable()
				{
		            @Override public void run()
		            {
						// On affiche la reponse
		            	controller.getFlow().getChildren().add(new Text(reponses[0] + "\n"));
		            }
				});
				
				// On recupère le port indique par le serveur
				reponses[1] = controller.getBr().readLine();
				
				Platform.runLater(new Runnable()
				{
		            @Override public void run()
		            {
						// On affiche la reponse
		            	controller.getFlow().getChildren().add(new Text(reponses[1] + "\n"));
		            }
				});
				
				// On cree le fichier
				file.createNewFile();
				
				// On met a jour le tableau de fichiers locaux
				Platform.runLater(new Runnable()
				{
		            @Override public void run()
		            {
						// On met a jour le tableau de fichiers locaux
		            	new LocalLS().exec(controller);
		            }
				});
				
				// On transforme le port en integer
				int port = Integer.parseInt(reponses[1].split(" ")[2]);
				
				// On etablis la connexion au serveur sur le nouveau port
				Socket s = new Socket("localhost", port);
				
				// On recupère les flots
				InputStream is = s.getInputStream();
				
				// On initialise un flot 
				FileOutputStream fos = new FileOutputStream(file);
				
				// On instancie un buffer pour lire le flot plus rapidement
				byte [] buf = new byte[1024];
				int lus;
				
				// On lis autant d'octets que possible
				while((lus = is.read(buf)) != -1)
				{
					// On ecris les octets lus dans le fichier
					fos.write(buf, 0, lus);
				}
				
				reponses[2] = controller.getBr().readLine();
				
				Platform.runLater(new Runnable()
				{
		            @Override public void run()
		            {
						// On affiche la reponse
		            	controller.getFlow().getChildren().add(new Text(reponses[2] + "\n"));
		            }
				});
				
				// On ferme les flots du serveur et du fichier, ainsi que la socket
				fos.close();
				is.close();
				s.close();
			}
			
			else
			{
				Platform.runLater(new Runnable()
				{
		            @Override public void run()
		            {
						Text erreur = new Text(reponses[2] + "\n");
						erreur.setFill(Color.RED);
						controller.getFlow().getChildren().add(erreur);
		            }
				});
			}
		}
		
		// On detecte la deconnexion du serveur
		catch (SocketException e)
		{
			Platform.runLater(new Runnable()
			{
	            @Override public void run()
	            {
	    			Text erreur = new Text("Le serveur c'est arrêté subitement\n");
	    			erreur.setFill(Color.RED);
	    			controller.getFlow().getChildren().add(erreur);
	            }
			});
		}

		// On detecte les erreurs de lecture et d'ecriture dans les flots
		catch (IOException e)
		{
			Platform.runLater(new Runnable()
			{
	            @Override public void run()
	            {
	    			Text erreur = new Text("Erreur de téléchargement\n");
	    			erreur.setFill(Color.RED);
	    			controller.getFlow().getChildren().add(erreur);
	            }
			});
		}
	}

}
