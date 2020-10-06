package commandes_serveur;

import java.io.IOException;
import java.net.SocketException;

import controleurs.ControleurPrincipal;

/**
 * Commande servant a obtenir l'arborescence du serveur (non implemente)
 * @author stobr
 */
public class CommandeTREE extends Commande {

	public CommandeTREE(ControleurPrincipal controller, String commande)
	{
		super(controller, commande);
	}

	@Override
	public void send() throws IOException, SocketException
	{
		
	}

}
