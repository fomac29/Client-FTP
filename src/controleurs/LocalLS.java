package controleurs;

import java.io.File;

import javax.swing.filechooser.FileSystemView;

import ressources.Fichier_Local;

public class LocalLS implements ControleurLocal {

	@Override
	public void exec(ControleurPrincipal controller)
	{
		// On Cree une nouvelle liste de Fichier_Local
		controller.getLocal_files().getItems().clear();
		
		// On ajoute le dossier ".." pour revenir en arriere
		controller.getLocal_files().getItems().add(new Fichier_Local("..", 0, "Dossier de fichiers", "", new File("..")));
		
		// Taille total des fichiers et dossiers
		long totalSize = 0;
		
		// Nombre de fichiers dans le dossier courant
		int nbFile = 0;
		
		// Nombre de dossiers dans le dossier courant
		int nbDirectory = 0;
		
		File[] files = new File(controller.getSite_local().getValue()).listFiles();
		
		// Si on la liste de fichiers n'existe pas, c'est qu'on est a la racine
		if(files == null)
		{
			files = FileSystemView.getFileSystemView().getRoots()[0].listFiles();
		}
		
		// On affiche chaque fichier
		for(File file : files)
		{
			// Sauf s'il est cense etre cache
			if(!file.isHidden())
			{
				// On ajoute le fichier
				controller.getLocal_files().getItems().add(new Fichier_Local(file));
				
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
		}
		
		// On met a jour le label
		controller.getDescription_local_files().setText(nbFile + " fichiers et " + nbDirectory + " Dossiers. Taille totale : " + totalSize + " octets.");

	}

}
