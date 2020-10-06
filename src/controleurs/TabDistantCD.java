package controleurs;

import commandes_serveur.CommandSender;
import javafx.scene.input.MouseEvent;
import ressources.Fichier_Serveur;

/**
 * Classe Servant a changer de repertoire local en double-cliquant sur le tableau 
 * des fichiers distants
 * @author stobr
 */
public class TabDistantCD implements ControleurLocalMouse
{
	/**
	* changer le repertoire local en double-cliquant sur le tableau 
	* des fichiers distants
	 * @param controller
	 * @param event
	 */
	public void exec(ControleurPrincipal controller, MouseEvent event)
	{
		// On ferme le ContextMenu des fichiers serveur
		controller.getContextMenuDistant().hide();
		
		// On recupere l'element du tableau selectionne
		Fichier_Serveur item = controller.getServer_files().getSelectionModel().getSelectedItem();
		
		// Si celui-ci n'est pas nul, on effectue le changement de repertoire et on recupere les fichiers et le chemin courants
    	if(event.getClickCount() == 2 && item != null)
    	{
    		CommandSender.sendCommande(controller, "CD " + item.getNom());
    		CommandSender.sendCommande(controller, "LS");
    		CommandSender.sendCommande(controller, "PWD");
    	}
	}

}
