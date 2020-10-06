package controleurs;

import commandes_serveur.CommandSender;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

/**
 * Classe servant a initialiser le ContextMenu du serveur
 * @author stobr
 *
 */
public class InitContextMenuDistant implements ControleurLocal {

	@Override
	public void exec(ControleurPrincipal controller)
	{
		 // On instancie le ContextMenu
        controller.setContextMenuDistant(new ContextMenu());
 
        // On instancie un nouvel item dans le menu
        MenuItem item1 = new MenuItem("Télécharger");
        
        // On cree la methode s"activant en cliquant sur l'item
        item1.setOnAction(new EventHandler<ActionEvent>()
        {        	 
            @Override
            public void handle(ActionEvent event)
            {
            	// On recupere l'element du tableau selectionne
        		String fichier = controller.getServer_files().getSelectionModel().getSelectedItem().getNom();
        		
        		if(fichier != null && !(fichier.equals("..")))
        		{
        			CommandSender.sendCommande(controller, "GET " + fichier);
        		}
            }
        });
        
        controller.getContextMenuDistant().getItems().add(item1);
	}

}
