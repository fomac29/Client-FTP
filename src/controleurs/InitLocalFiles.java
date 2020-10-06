package controleurs;

import java.io.File;

import javax.swing.filechooser.FileSystemView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import ressources.Fichier_Local;

/**
 * Classe Initialisant le tableau de fichiers locaux et le label decrivant le nombre de fichier,
 * dossier et leur taille totale
 * @author Clement Stoliaroff
 */
public class InitLocalFiles implements ControleurLocal
{
	/**
	 * Initialisant le tableau de fichiers locaux
	 */
	@Override
	public void exec(ControleurPrincipal controller)
	{
		// On initialise les colonnes du tableau pour qu'elles sachent quelles sont les valeurs de la classe a afficher
        controller.getFilename_client().setCellValueFactory(new PropertyValueFactory<>("nom"));
        controller.getFilesize_client().setCellValueFactory(new PropertyValueFactory<>("taille"));
        controller.getFiletype_client().setCellValueFactory(new PropertyValueFactory<>("type"));
        controller.getLastmodified_client().setCellValueFactory(new PropertyValueFactory<>("dateDerniereModification"));
		
        
        
        // Recupere le dossier root du systeme
		File folder = FileSystemView.getFileSystemView().getRoots()[0];
		
		// Recupere la liste des fichiers et dossiers du repertoire
		File [] files = folder.listFiles();
		
		// Cree la liste des elements a inserer dans le tableau
		ObservableList<Fichier_Local> list = FXCollections.observableArrayList();
		
		// On ajoute le dossier ".." pour revenir en arrière
		list.add(new Fichier_Local("..", 0, "Dossier de fichiers", "", new File("..")));
		
		// Taille total des fichiers et dossiers
		long totalSize = 0;
		
		// Nombre de fichiers dans le dossier courant
		int nbFile = 0;
		
		// Nombre de dossiers dans le dossier courant
		int nbDirectory = 0;
		
		// On affiche chaque fichier
		for(File file : files)
		{
			
			// On ajoute le fichier
			list.add(new Fichier_Local(file));
			
			// On ajoute la taille du fichier a la taille totale
			totalSize += file.length();
			
			// On incremente le nombre de dossier si le fichier est un dossier
			if(file.isDirectory())
			{
				nbDirectory++;
			}
			
			// Sinon on incremente le nombre de dossier
			else
			{
				nbFile++;
			}
		}
		
		// On insere la liste dans le tableau
		controller.getLocal_files().setItems(list);
		
		// On met a jour le label
		controller.getDescription_local_files().setText(nbFile + " fichiers et " + nbDirectory + " Dossiers. Taille totale : " + totalSize + " octets.");
	}

}
