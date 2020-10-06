package controleurs;

import java.io.File;

import javax.swing.filechooser.FileSystemView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Classe initialisant le dossier courant du client
 * @author Clement Stoliaroff
 */
public class InitCurrentDirectory implements ControleurLocal
{
	/**
	 * Initialise la ComboBox contenant le dossier courant du client
	 */
	@Override
	public void exec(ControleurPrincipal controller)
	{
		// On creer une liste permettant d'ajouter un historique a la ComboBox
    	ObservableList<String> list = FXCollections.observableArrayList();
    	
    	// On Initialise le dossier courant avec le dossier root du systeme
    	File directory = FileSystemView.getFileSystemView().getRoots()[0];
    	
    	// On initialise la valeur de la ComboBox avec le chemin du dossier root
    	controller.getSite_local().setValue(directory.toString());
    	
    	// On l'ajoute a la liste, qu'on insere ensuite dans la ComboBox
    	list.add(directory.toString());
    	controller.getSite_local().setItems(list);
	}

}
