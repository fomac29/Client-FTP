package controleurs;

import java.io.File;

import javax.swing.filechooser.FileSystemView;

import javafx.collections.ObservableList;
import javafx.scene.input.MouseEvent;
import ressources.Fichier_Local;

/**
 * Classe Servant a changer de repertoire local en double-cliquant sur le tableau 
 * des fichiers locaux
 * @author Clement Stoliaroff
 */
public class TabLocalCD implements ControleurLocalMouse
{
	/**
	* changer le repertoire local en double-cliquant sur le tableau 
	* des fichiers locaux
	 * @param controller
	 * @param event
	 */
	public void exec(ControleurPrincipal controller, MouseEvent event)
	{
		// On ferme le ContextMenu des fichiers locaux
		controller.getContextMenuLocal().hide();
		
		// On recupere l'element du tableau selectionne
		Fichier_Local item = controller.getLocal_files().getSelectionModel().getSelectedItem();
		
		// Si celui-ci n'est pas nul, on effectue le changement de repertoire
    	if(event.getClickCount() == 2 && item != null && item.getFile().isDirectory())
    	{    		
    		// On recupere le l'historique de la combobox
	    	ObservableList<String> o = controller.getSite_local().getItems();
	    	
	    	// Si le dossier n'est pas le dossier ".."
	    	if(!item.getNom().contains(".."))
	    	{
		    	// On l'ajoute a l'historique de la comboBox
		    	o.add(item.getFile().toString());
		    	
		    	// On insere la liste modifiee reprensantant l'historique dans la ComboBox
		    	controller.getSite_local().setItems(o);
		    	
		    	// On modifie la valeur de la ComboBox avec le chemin du dossier
		    	controller.getSite_local().setValue(item.getFile().toString());
	    	}
	    	
	    	// Sinon
	    	else
	    	{	    		
	    		File parent = FileSystemView.getFileSystemView().getParentDirectory(new File(controller.getSite_local().getValue()));
	    		
	    		if(parent == null)
	    		{
	    			parent = FileSystemView.getFileSystemView().getRoots()[0];
	    		}

		    	// On ajoute dossier parent a l'historique de la comboBox
		    	o.add(parent.toString());
		    	
		    	// On insere la liste modifiee reprensantant l'historique dans la ComboBox
		    	controller.getSite_local().setItems(o);
		    	
		    	// On modifie la valeur de la ComboBox avec le chemin du dossier parent
		    	controller.getSite_local().setValue(parent.toString());
	    	}
	    	
	    	// On met a jour le tableau de fichiers locaux
	    	new LocalLS().exec(controller);
    	}
	}

}
