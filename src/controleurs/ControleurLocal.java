package controleurs;

import java.io.FileNotFoundException;

/**
 * Interface decrivant les controlleurs de l'IHM cote local
 * @author Clement Stoliaroff
 */
public interface ControleurLocal
{
	/**
	 * Methode destinee a etre heritee par les controlleurs 
	 * @param controller le controlleur principal de l'application
	 * @throws FileNotFoundException 
	 */
	public abstract void exec(ControleurPrincipal controller) throws FileNotFoundException;
}
