package ressources;

import java.io.File;
import java.text.SimpleDateFormat;

/**
 * Classe decrivant un fichier local pour l'affichage dans le tableau de fichiers locaux
 * @author Clement Stoliaroff
 */
/**
 * @author stobr
 *
 */
public class Fichier_Local
{
	/**
	 * Le nom du fichier
	 */
	private String nom;
	
	/**
	 * La taille du fichier
	 */
	private long taille;
	
	/**
	 * Le type de fichier (dossier ou fichier)
	 */
	private String type;
	
	/**
	 * La date de derniere modification du fichier
	 */
	private String dateDerniereModification;
	
	/**
	 * La classe File representant le fichier
	 */
	private File file;
	
	/**
	 * @param nom Le nom du fichier
	 * @param taille La taille du fichier
	 * @param type Le type de fichier (dossier ou fichier)
	 * @param dateDerniereModification La date de derniere modification du fichier
	 */
	public Fichier_Local(String nom, long taille, String type, String dateDerniereModification, File file)
	{
		this.nom = nom;
		this.taille = taille;
		this.type = type;
		this.dateDerniereModification = dateDerniereModification;
		this.file = file;
	}
	
	public Fichier_Local(File file)
	{
		this.taille = file.length();
		
		// On determine le status du fichier
		String type = file.isDirectory() ? "Dossier de fichiers" : "Fichier";

		this.type = type;
		
		// Instanciation d'un objet SimpleDateFormat pour mettre la date en forme
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		this.dateDerniereModification = sdf.format(file.lastModified());
		
		this.file = file;
		
		// On recupere le nom et le chemin du dossier
		String s = file.toString();
		String n = file.getName();
		
		if(n.startsWith("::{") || n.equalsIgnoreCase(""))
			this.nom = s;
		else
			this.nom = n;
	}
	
	/**
	 * @return
	 */
	public String getNom()
	{
		return nom;
	}
	
	/**
	 * @return
	 */
	public long getTaille()
	{
		return taille;
	}
	
	/**
	 * @return
	 */
	public String getType()
	{
		return type;
	}
	
	/**
	 * @return
	 */
	public String getDateDerniereModification()
	{
		return dateDerniereModification;
	}
	
	
	/**
	 * @return
	 */
	public File getFile() {
		return file;
	}
	
	
}