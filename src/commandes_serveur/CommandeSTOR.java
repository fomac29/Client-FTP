package commandes_serveur;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

import controleurs.ControleurPrincipal;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * Classe representant la commande STOR
 * @author Clement Stoliaroff
 */
public class CommandeSTOR extends Commande implements Runnable {
	
	private volatile String[] reponses;
	
	private volatile String fileName;
	
	/**
	 * Constructeur de la commande STOR
	 * @param controller controlleur de l'IHM
	 * @param commande La commande a envoyer au serveur
	 */
	public CommandeSTOR(ControleurPrincipal controller, String commande) {
		super(controller, commande);
		this.reponses = new String[3];
		
		for(int i = 0; i < reponses.length; i++)
		{
			this.reponses[i] = "";
		}
		
		this.fileName = "";
	}
	
	/**
	 * Cree un thread permettant de transferer un fichier vers le serveur
	 */
	public void send()
	{
		// On lance un thread afin de pouvoir continuer à lancer des commandes pendant le transfert de fichier
		Thread t = new Thread(this);
		t.start();
	}

	/**
	 * Envoi de la commande STOR au serveur, ainsi que du fichier passe en argument
	 */
	@Override
	public void run()
	{
		try
		{
			// On divise la commande afin de recuperer le nom du fichier a transferer
			String [] tabCommande = commande.split(" ");
			
			// On recupère le nom du fichier a telecharger
			fileName = tabCommande[1];
			
			// Si le nom du fichier contiens des espaces, on recupère son nom complet
			for(int i = 2; i < tabCommande.length; i++)
			{
				fileName += " " + tabCommande[i];
			}
			
			Platform.runLater(new Runnable()
			{
	            @Override public void run()
	            {
	    			controller.getFlow().getChildren().add(new Text(fileName + "\n"));
	            }
	        });
			
			File file = new File(fileName);
			
			// On verifie que le fichier existe bien
			if(file.exists())
			{
				// On envoie la commande au serveur
				controller.getPs().println(commande);
				
				// On lis la reponse du serveur
				reponses[0] = controller.getBr().readLine();
				
				// Si la reponse n'est pas une erreur
				if(reponses[0].charAt(0) == '1')
				{
					Platform.runLater(new Runnable()
					{
			            @Override public void run()
			            {
			            	controller.getFlow().getChildren().add(new Text(reponses[0] + "\n"));
			            }
			        });
					
					// On recupère le port indique par le serveur
					reponses[1] = controller.getBr().readLine();
					
					Platform.runLater(new Runnable()
					{
			            @Override public void run()
			            {
			            	controller.getFlow().getChildren().add(new Text(reponses[1] + "\n"));
			            }
			        });
					
					// On transforme le port en integer
					int port = Integer.parseInt(reponses[1].split(" ")[2]);
					
					Socket s = new Socket("localhost", port);
					
					FileInputStream fis = new FileInputStream(file);
			        
			        // On reçoit les flots
			        OutputStream os = s.getOutputStream();
			        BufferedReader br2 = new BufferedReader(new InputStreamReader(s.getInputStream()));
			        
			        int lus;
			        
			        // On instancie un tableau d'octet
			        byte [] buf = new byte[1024];
			        
			        // On remplis le buffer d'autant d'octet du fichier que possible
			        while((lus = fis.read(buf)) != -1)
			        {
			        	// On ecris les octets lus dans le buffer
			        	os.write(buf, 0, lus);
			        }
			        
			        // On ferme le flot d'ecriture d'envois du serveur afin que le serveur detecte la fin du fichier
			        os.close();
			        s.close();

			        reponses[2] = controller.getBr().readLine();
			        
					Platform.runLater(new Runnable()
					{
			            @Override public void run()
			            {
					        // On lis la reponse finale du serveur
							controller.getFlow().getChildren().add(new Text(reponses[2] + "\n"));
			            }
			        });
					
					Platform.runLater(new Runnable()
					{
			            @Override public void run()
			            {
					        // On lis la reponse finale du serveur
							CommandSender.sendCommande(controller, "LS");
			            }
			        });
			        
					// On ferme les flots restant
					br2.close();
			        fis.close();
				}
				
				else
				{
					Platform.runLater(new Runnable()
					{
			            @Override public void run()
			            {
							// On lis la reponse du serveur
							Text erreur = new Text(reponses[2] + "\n");
							erreur.setFill(Color.RED);
							controller.getFlow().getChildren().add(erreur);
			            }
			        });
				}
			}
			
			// On affiche une erreur si le fichier n'existe pas
			else
			{
				Platform.runLater(new Runnable()
				{
		            @Override public void run()
		            {
						Text erreur = new Text("Le fichier n'existe pas\n");
						erreur.setFill(Color.RED);
						controller.getFlow().getChildren().add(erreur);
		            }
		        });
			}
		}
		
		// On affiche une erreur si le serveur se deconnecte
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
		
		// On detecte les erreurs de lecture et d'ecriture
		catch (IOException e)
		{
			Platform.runLater(new Runnable()
			{
	            @Override public void run()
	            {
	            	Text erreur = new Text("Erreur de transfert de fichier\n");
	    			erreur.setFill(Color.RED);
	    			controller.getFlow().getChildren().add(erreur);
	            }
	        });
			
		}
	}

}
