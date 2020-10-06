package controleurs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import ressources.Fichier_Serveur;

public class InitServerFiles implements ControleurLocal
{
	/**
	 * Initialise les differentes colonnes du tableau de fichiers du serveur
	 */
	@Override
	public void exec(ControleurPrincipal controller)
	{
		// On cree une liste permettant de remplir le tableau
        ObservableList<Fichier_Serveur> list = FXCollections.observableArrayList();
        
		// On initialise les colonnes du tableau pour qu'elles sachent quelles sont les valeurs de la classe a afficher
		controller.getFilename_server().setCellValueFactory(new PropertyValueFactory<>("nom"));
        controller.getFilesize_server().setCellValueFactory(new PropertyValueFactory<>("taille"));
        controller.getFilestypes_server().setCellValueFactory(new PropertyValueFactory<>("type"));
        controller.getLastmodified_server().setCellValueFactory(new PropertyValueFactory<>("dateDerniereModification"));
        controller.getFilesaccess_server().setCellValueFactory(new PropertyValueFactory<>("droitsAcces"));
        controller.getOwner_server().setCellValueFactory(new PropertyValueFactory<>("proprietaire"));
        
        controller.getServer_files().setItems(list);
	}

}
