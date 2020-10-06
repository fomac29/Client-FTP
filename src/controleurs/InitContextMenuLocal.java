package controleurs;

import commandes_serveur.CommandSender;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import ressources.Fichier_Local;

/**
 * Classe servant a initialiser le ContextMenu local
 * @author stobr
 *
 */
public class InitContextMenuLocal implements ControleurLocal {

	@Override
	public void exec(ControleurPrincipal controller)
	{
		 // On instancie le ContextMenu
        controller.setContextMenuLocal(new ContextMenu());
 
        // On instancie un nouvel item dans le menu
        MenuItem item1 = new MenuItem("Envoyer");
        
        // On cree la methode s"activant en cliquant sur l'item
        item1.setOnAction(new EventHandler<ActionEvent>()
        {        	 
            @Override
            public void handle(ActionEvent event)
            {
            	// On recupere l'element du tableau selectionne
        		Fichier_Local fichier = controller.getLocal_files().getSelectionModel().getSelectedItem();
        		
        		if(fichier != null && !(fichier.getNom().equals("..")))
        		{
        			CommandSender.sendCommande(controller, "STOR " + fichier.getFile().toString());
        		}
            }
        });
        
        controller.getContextMenuLocal().getItems().add(item1);
	}

}
