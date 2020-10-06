package controleurs;

import java.io.File;

import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import ressources.Dossier;

/**
 * Classe Servant a changer de repertoire local en cliquant sur les éléments du Treeview
 * @author Clement Stoliaroff
 */
public class LocalTreeCD implements ControleurLocal
{
	
	/**
	 * Change le repertoire local en cliquant sur les éléments du Treeview
	 */
	@Override
	public void exec(ControleurPrincipal controller)
	{
		// recupere l'element selectionne dans le treeview
		TreeItem<Dossier> item = controller.getLocal_tree().getSelectionModel().getSelectedItem();
    	
		// Si celui-ci n'est pas nul, on effectue le changement de repertoire
    	if(item != null)
    	{
    		// On recupere le l'historique de la combobox
	    	ObservableList<String> o = controller.getSite_local().getItems();
	    	
	    	// On recupere le dossier de l'element selectionne dans le treeview
	    	File d = item.getValue().getFile();
	    	
	    	// On recupere le chemin de ce dossier
	    	String s = d.toString();
	    	
	    	// On l'ajoute a l'historique de la comboBox
	    	o.add(s);
	    	
	    	// On insere la liste modifiee reprensantant l'historique dans la ComboBox
	    	controller.getSite_local().setItems(o);
	    	
	    	// On modifie la valeur de la ComboBox avec le chemin du dossier
	    	controller.getSite_local().setValue(s);
	    	
	    	// On met a jour le tableau de fichiers locaux
	    	new LocalLS().exec(controller);
    	}
	}
	
}
