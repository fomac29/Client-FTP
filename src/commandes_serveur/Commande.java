package commandes_serveur;

import java.io.IOException;
import java.net.SocketException;

import controleurs.ControleurPrincipal;

/**
 * Classe abstraite destinee a etre heritee par des classes envoyant des commandes au serveur
 * @author Clement Stoliaroff
 */
public abstract class Commande
{
	/**
	 * Le controlleur de l'IHM
	 */
	protected ControleurPrincipal controller;
	
	/**
	 * La commande a envoyer au serveur
	 */
	protected String commande;
	
	/**
	 * Constructeur par defaut des commandes
	 * @param controller Le controlleur de l'IHM
	 * @param commande La commande a envoyer au serveur
	 */
	public Commande(ControleurPrincipal controller, String commande)
	{
		this.controller = controller;
		this.commande = commande;
	}
	
	/**
	 * Methode commune aux classes filles permettant d'envoyer la commande au serveur et de receptionner
	 * la reponse
	 * @throws IOException Erreur d'envoi de la commande ou de reception de la reponse du serveur
	 * @throws SocketException Erreur due a la deconnexion du serveur
	 */
	public abstract void send() throws IOException, SocketException;
}
