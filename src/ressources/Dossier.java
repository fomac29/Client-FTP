package ressources;

import java.io.File;

/**
 * Classe decrivant un dossier servant a la construction du treeview
 * @author Clement Stoliaroff
 */
public class Dossier
{
	/**
	 * La classe File representant le dossier
	 */
	private File file;
	
	/**
	 * Nom du fichier
	 */
	private String name;
	
	/**
	 * Constructeur de la classe Dossier
	 * @param file La classe File representant le dossier
	 */
	public Dossier(File file) {
		this.file = file;
		
		// On recupere le nom et le chemin du dossier
		String s = file.toString();
		String n = file.getName();
		
		if(n.startsWith("::{") || n.equals(""))
			this.name = s;
		else
			this.name = n;
	}

	/**
	 * @return
	 */
	public File getFile() {
		return file;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * methode toString retournant le nom du dossier pour le treeview
	 */
	@Override
	public String toString() {
		return name;
	}
	
	
	
}
