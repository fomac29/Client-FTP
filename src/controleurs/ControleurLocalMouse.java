package controleurs;

import javafx.scene.input.MouseEvent;

public interface ControleurLocalMouse 
{
	/**
	 * Methode destinee a etre heritee par les controlleurs ayant besoin d'un clique sur la souris
	 * @param controller le controlleur principal de l'application
	 * @param event l'evenement se produisant sur la souris
	 */
	public abstract void exec(ControleurPrincipal controller, MouseEvent event);
}
