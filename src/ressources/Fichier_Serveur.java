package ressources;

/**
 * Classe decrivant un fichier du serveur pour l'affichage dans le tableau de fichiers distants
 * @author Clement Stoliaroff
 */
public class Fichier_Serveur
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
	 * Droits d'acces du fichier
	 */
	private String droitsAcces;
	
	/**
	 * Proprietaire du fichier
	 */
	private String proprietaire;
	
	/**
	 * Constructeur permettant d'instancier la classse a partir du retours du LS distant
	 * @param description
	 */
	public Fichier_Serveur(String description)
	{
		// on divise la description a l'aide du separateur ","
		String [] fichier = description.split(",");

		// On initialise les attribus a l'aide des cases du tableau
		this.nom = fichier[1];
		this.taille = Long.parseLong(fichier[2]);
		this.type = fichier[3];
		this.dateDerniereModification = fichier[4];
		this.droitsAcces = fichier[5];
		this.proprietaire = fichier[6];
	}

	/**
	 * @return
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @return
	 */
	public long getTaille() {
		return taille;
	}

	/**
	 * @return
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return
	 */
	public String getDateDerniereModification() {
		return dateDerniereModification;
	}

	/**
	 * @return
	 */
	public String getDroitsAcces() {
		return droitsAcces;
	}

	/**
	 * @return
	 */
	public String getProprietaire() {
		return proprietaire;
	}	
}