package controleurs;


import java.io.File;

import javax.swing.filechooser.FileSystemView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ressources.Dossier;

/**
 * Classe servant a initialiser le treeview du client
 * @author Clement Stoliaroff
 */
public class InitLocalTree implements ControleurLocal
{
	/**
	 * Initialise le treeview du client
	 */
	@Override
	public void exec(ControleurPrincipal controller)
	{
		// Instancie un objet Dossier avec le dossier root (celui-ci etant en anglais sur windows, on cree un alias)
		Dossier dossier = new Dossier(FileSystemView.getFileSystemView().getRoots()[0]);
		
		// On ajoute l'icone du bureau
		Node rootIcon = new ImageView(new Image(getClass().getResourceAsStream("/ressources/desktop.PNG")));
		
		// Cree l'arbre a partir du dossier root
        TreeItem<Dossier> root = createNode(dossier, rootIcon);
        
        // On construit l'arbre a partir de la racine de l'arbre
        controller.getLocal_tree().setRoot(root);
	}
	
    /**
     * Methode initialisant l'arborescence locale trouvee sur le site d'Oracle a l'adresse :
     * https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/TreeItem.html
     * @param f Le dossier racine de l'arbre
     * @return L'arbre a inserer dans le treeview
     */
    private TreeItem<Dossier> createNode(final Dossier d, final Node i) {
        return new TreeItem<Dossier>(d, i) {

            private boolean isLeaf;

            private boolean isFirstTimeChildren = true;
            private boolean isFirstTimeLeaf = true;
             
            @Override public ObservableList<TreeItem<Dossier>> getChildren() {
                if (isFirstTimeChildren)
                {
                    isFirstTimeChildren = false;
                    super.getChildren().setAll(buildChildren(this));
                }
                
                return super.getChildren();
            }

            @Override public boolean isLeaf() {
                if (isFirstTimeLeaf)
                {
                    isFirstTimeLeaf = false;
                    File f = (File) getValue().getFile();
                    isLeaf = f.isFile();
                }

                return isLeaf;
            }
   
            private ObservableList<TreeItem<Dossier>> buildChildren(TreeItem<Dossier> TreeItem) {
                File f = TreeItem.getValue().getFile();
                if (f != null && f.isDirectory() && !f.isHidden()) {
                    File[] files = f.listFiles();
                    if (files != null) {
                        ObservableList<TreeItem<Dossier>> children = FXCollections.observableArrayList();
                        for (File childFile : files) {
                        	if(childFile.isDirectory())
                        	{
                        		Node Icon = new ImageView(new Image(getClass().getResourceAsStream("/ressources/folder.PNG")));
                        		children.add(createNode(new Dossier(childFile), Icon));
                        	}
                        }

                        return children;
                    }
                }

                return FXCollections.emptyObservableList();
            }
        };
    }

}
